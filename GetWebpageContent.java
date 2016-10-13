//Refenerences : http://stackoverflow.com/questions/17440236/getting-java-to-save-a-html-file

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLConnection;  

//for saving the news we want to save as a htl forat
public class GetWebpageContent {  
	String fileName;
	URL url;
	public GetWebpageContent(String fn,String link){  
		this.fileName = fn;
		
		try {  
			// enter any url to get its content  
			url = new URL(link); 
			URLConnection conn = url.openConnection();  
			// open the stream and put it into BufferedReader  
			BufferedReader br = new BufferedReader(new InputStreamReader(  
			conn.getInputStream()));  
			String inputLine;  
			
			System.out.println(fileName);
			File file = new File(fileName);  
			if (!file.exists()) {  
				file.createNewFile();  
			}  
			  
			FileWriter fw = new FileWriter(file.getAbsoluteFile());  
			BufferedWriter bw = new BufferedWriter(fw);  
			  
			while ((inputLine = br.readLine()) != null) {  
				bw.write(inputLine);  
			}  
			  
			bw.close();  
			br.close();  
			  
			System.out.println("Your file is saved in " + fileName  + " location.");  
		  
		} catch (MalformedURLException e) {  
			e.printStackTrace();  
		}catch (IOException e){ 
			e.printStackTrace();  
		}  
	  
	 }  
}