//References : http://www.mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/      ............................................................................

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// to Modify the the xml file when user want to save news
public class ModifyXMLFile  {
	private String titlen,linkn;
	
	public ModifyXMLFile(String t,String l){
		titlen = t;
		linkn = l;
		try{
			//taking the path  news xml file  and create a document
			String filepath ="E:/javaproject/news.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			//root element
			Node rootElement = doc.getElementsByTagName("News").item(0);
				
			//append a new node  to the ite node which contains news link and title that we want to save
			Element item =doc.createElement("item");
			rootElement.appendChild(item);
			Element title=doc.createElement("title");
			title.appendChild(doc.createTextNode(titlen));
			item.appendChild(title);
			Element link=doc.createElement("link");
			link.appendChild(doc.createTextNode(linkn));
			item.appendChild(link);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);	
				
			System.out.println("Done");	
			
		} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
		
	}
}