package rekognition;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceRecord;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.IndexFacesRequest;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
import com.amazonaws.services.rekognition.model.SearchFacesByImageRequest;
import com.amazonaws.services.rekognition.model.SearchFacesByImageResult;
import com.amazonaws.services.rekognition.model.SearchFacesRequest;
import com.amazonaws.services.rekognition.model.SearchFacesResult;

public class MyRekognition {
	private AmazonRekognition rekognition;
	
	public MyRekognition(String idKey , String secretKey) {
		// TODO Auto-generated constructor stub
		AWSCredentials credentials = new AWSCredentials() {
			
			@Override
			public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return idKey;
			}
			
			@Override
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return secretKey;
			}
		};
		rekognition = AmazonRekognitionClientBuilder.standard()
				.withRegion(Regions.US_WEST_2)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
	}
	
	public DetectFacesResult detectFaces(ByteBuffer image){
		DetectFacesRequest request = new DetectFacesRequest().withImage(new Image().withBytes(image));
		try{
			DetectFacesResult result = rekognition.detectFaces(request);
			return result;
		}catch ( AmazonRekognitionException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public CreateCollectionResult createCollection(String idCollection){
		CreateCollectionRequest request = new CreateCollectionRequest().withCollectionId(idCollection);
		try{
			CreateCollectionResult result = rekognition.createCollection(request);
			return result;
		}catch (AmazonRekognitionException e) {
			e.printStackTrace();
			return null;
		}
	}
	public IndexFacesResult indexFaces(ByteBuffer image, String collectionId){
		IndexFacesRequest request = new IndexFacesRequest().withCollectionId(collectionId).withImage(new Image().withBytes(image));
		try{
			IndexFacesResult result = rekognition.indexFaces(request);
			return result;
		}catch (AmazonRekognitionException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public SearchFacesResult searchFaces(String idFace, String idCollection){
		SearchFacesRequest request = new SearchFacesRequest().withFaceId(idFace).withCollectionId(idCollection);
		try{
			SearchFacesResult result = rekognition.searchFaces(request);
			return result;
		}catch (AmazonRekognitionException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SearchFacesResult> indexAll (ByteBuffer image, String collectionId, String collectionCache){
		IndexFacesResult indexFacesResult = indexFaces(image,collectionCache);
		List <FaceRecord> list = indexFacesResult.getFaceRecords();
		ArrayList<SearchFacesResult> listResult = new ArrayList<SearchFacesResult>();
		int length = list.size();
		for(int i =0 ;i<length;i++){
			SearchFacesResult result = searchFaces(list.get(i).getFace().getFaceId(), collectionId);
			listResult.add(result);
			
		}
		return listResult;
	}
}
