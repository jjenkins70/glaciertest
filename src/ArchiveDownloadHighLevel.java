import java.io.File;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;


public class ArchiveDownloadHighLevel {
    public static String vaultName = "jeremiahtest1";
    public static String archiveId = "Y577n8dprkXOOCPUI5rLwl9-jmbTHABJaCZJJsgW6Ao8LltmI3H4aPs_Nj-y7G0qfxtlOZ7qWXOU5NMZyhlFNp8HeIJNH51Yz2q_xx2jamB5WeL7Dz4UFhwcMYTruN_Qja9Nd3mtkQ";
    public static String downloadFilePath  = "/tmp/glacierdownload/";
    
    public static AmazonGlacierClient client;
    
    public static void main(String[] args) throws IOException {
        
        AWSCredentials credentials = new PropertiesCredentials(
                ArchiveDownloadHighLevel.class.getResourceAsStream("AwsCredentials.properties"));
        client = new AmazonGlacierClient(credentials);
        client.setEndpoint("https://glacier.us-east-1.amazonaws.com/");

        try {
            ArchiveTransferManager atm = new ArchiveTransferManager(client, credentials);
            
            atm.download(vaultName, archiveId, new File(downloadFilePath));
            
        } catch (Exception e)
        {
            System.err.println(e);
        }
    }

}