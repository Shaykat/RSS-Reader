import java.awt.Desktop;
import java.net.URI;
//for opening browser according to the selected news link
public class LaunchBrowser{
   public  LaunchBrowser(String link){
	
        try {
	    String url=link;
            URI uri=URI.create(url);
            Desktop.getDesktop().browse(uri);
       }
       catch (Exception e) {
           System.out.println("Launch browser : "+e.getMessage());
       }
   }
   
}