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

// ........................................ Super Class of all the parse related class......................................
public abstract class Parse{
	private DocumentBuilderFactory factory;
	private Document doc;
	private Element root;

	// Create a xPath instance
	private XPath xPath;
	private XPathExpression expression;
	private NodeList nl;
	
	public Parse(String link,String item){
		try {
			// build docoent builder factory
			factory = DocumentBuilderFactory.newInstance();
			doc = factory.newDocumentBuilder().parse(link);
			root = doc.getDocumentElement();

			// Create a xPath instance
			xPath = XPathFactory.newInstance().newXPath();
			expression = xPath.compile(item);
			nl = (NodeList) expression.evaluate(root, XPathConstants.NODESET);
	        }
		catch (IOException | ParserConfigurationException | SAXException exp) {
		    exp.printStackTrace();
		} 
		catch (XPathExpressionException ex) {
		    ex.printStackTrace();
		}
		
	}
	//reutrn the number of nodelist in xml file
	public NodeList getNodeList(){
		return nl;
	}
	
	//return a Xpath which will search an item
	public XPath getXPath(){
		return xPath;
	}
	public XPathExpression getExprssion(){
		return expression;
	}
	//abstract method
	public abstract void xmlParse();
}