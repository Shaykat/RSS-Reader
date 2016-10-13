import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class SaveNews extends Parse{
	private XPath xPath;
	private XPathExpression expression;
	private NodeList nl;
	private Categories category;
	private String title;
	private String link;
	private Window window;
	private ArrayList <Categories>   categoryList = new ArrayList <Categories> ();
	 
	 public SaveNews(Window w,String link,String item){
		super(link,item);
		 window = w;
	 }
	 
	 //Override abstract method of Parse class
	@Override
	public void xmlParse() {
		try {
			nl = getNodeList();
			expression = getExprssion();
			xPath = getXPath();
			System.out.println("Found " + nl.getLength() + " categories of news from bbbcnews...");
			for (int index = 0; index < nl.getLength(); index++) {
				String title,link;
				//Font banglaFont=new Font("Arial Unicode MS Regular", Font.BOLD,15);
				Node node = nl.item(index);
				expression = xPath.compile("title");
				Node child = (Node) expression.evaluate(node, XPathConstants.NODE);
				//System.out.println(child.getTextContent());
				title = child.getTextContent();
				//title.setFont(banglaFont);
				expression = xPath.compile("link");
				child = (Node) expression.evaluate(node, XPathConstants.NODE);
				link = child.getTextContent();
				
				category = new Categories(title,link);
				categoryList.add(category);
				//System.out.println();
			}

		}
		 catch (XPathExpressionException ex) {
		    ex.printStackTrace();
		}
		// removing existing component of the Window
		window.removePanel();
		//Pass the categoryList to NewsStorage
		window.saveChangePanel(categoryList);
	}
}