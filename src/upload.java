import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.UploadResult;


public class upload {
    public static String vaultName = "jeremiahtest1";
    public static String archiveToUpload = "/home/jjenkins/VirtualBox-4.2-4.2.4_81684_fedora16-1.x86_64.rpm";
    
    public static AmazonGlacierClient client;
    
    public static void main(String[] args) throws IOException {
        
        AWSCredentials credentials = new PropertiesCredentials(
                upload.class.getResourceAsStream("AwsCredentials.properties"));
        client = new AmazonGlacierClient(credentials);
        client.setEndpoint("https://glacier.us-east-1.amazonaws.com/");

        try {
            ArchiveTransferManager atm = new ArchiveTransferManager(client, credentials);
            
            UploadResult result = atm.upload(vaultName, "my archive " + (new Date()), new File(archiveToUpload));
            System.out.println("Archive ID: " + result.getArchiveId());
            
        } catch (Exception e)
        {
            System.err.println(e);
        }
    }

}
