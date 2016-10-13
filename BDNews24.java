//References :MD. EZAZUL ISLAM  Dept. of cs (AIUB)............................................. 
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
    import java.awt.*;
    import javax.swing.*;
    import javax.swing.JTable;
    import javax.swing.table.*;
    import javax.swing.JScrollPane;
    import javax.swing.JFrame;
//parseing RSS feed of BdNews24  ........................................

    public class BDNews24 extends Parse{
	private String sectionName;
	private XPath xPath;
	private XPathExpression expression;
	private NodeList nl;
	private Categories category;
	private String title;
	private String link;
    private Window window;
	ArrayList <Categories>categoryList = new ArrayList<Categories>();
	public BDNews24(Window w, String sectionName,String link,String item){
		super(link,item);
		window = w;
		this.sectionName = sectionName;
	}

	 ////Override  abstract method  from parse class 
	@Override
	public void xmlParse() {

            try {
		nl = getNodeList();
		expression = getExprssion();
		xPath = getXPath();
	
                //System.out.println("Found " + nl.getLength() + " categories of news from bdnews...");
		    
		//parseing only news title and link from xml according to its section name and store into an arrayList .....................................................
                for (int index = 0; index < nl.getLength(); index++) {
                    Node node = nl.item(index);
                    expression = xPath.compile("sectionname");
                    Node child = (Node) expression.evaluate(node, XPathConstants.NODE);
		    if(child.getTextContent().equals(sectionName)){
			expression = xPath.compile("description");
			child = (Node) expression.evaluate(node, XPathConstants.NODE);
			//System.out.println(child.getTextContent());
			title = child.getTextContent();
			expression = xPath.compile("link");
			child = (Node) expression.evaluate(node, XPathConstants.NODE);
			link = child.getTextContent();
			category = new Categories(title,link);
			categoryList.add(category);
		    }
                }

            } catch (XPathExpressionException ex) {
                ex.printStackTrace();
            }
	    
	//reoving existing component and adding new coponent on the ain frame.........................................
	window.removePanel();
        window.changePanel(categoryList);



    }

}
