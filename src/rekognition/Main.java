package rekognition;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.FaceRecord;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.IndexFacesRequest;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public class Main {

   public static void main(String[] args) throws Exception {
	   MyRekognition rekognition = new MyRekognition("AKIAJULX3UQTFY2PM2TQ", "cLHVppJJuGCWdc7E5i0wl7SBaeLO8oaOo7qVjML4");
	   
		 FileInputStream fIn;
		 FileChannel fChan;
		 long fSize;
		 ByteBuffer mBuf;
		 try {
			 fIn = new FileInputStream("C:\\Users\\haixo\\Desktop\\a.jpg");
			 fChan = fIn.getChannel();
		     fSize = fChan.size();
		     mBuf = ByteBuffer.allocate((int) fSize);
		     fChan.read(mBuf);
		     mBuf.rewind();
		     for (int i = 0; i < fSize; i++)
		      // System.out.print((char) mBuf.get());
		     fChan.close(); 
		     fIn.close(); 
		     System.out.println(mBuf);
	    } catch (IOException exc) {
		      System.out.println(exc);
		      System.exit(1);
	    }
   }
}