import java.awt.*;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.util.Date;
import java.net.URLEncoder;
import java.lang.*;
import javax.swing.JPanel;
import java.awt.Desktop;
import java.net.URI;

// for show the news title
class NewsStorage extends JPanel {
	JTable table;
	ArrayList ar;
	Window wd;
	//int co=0;
	public NewsStorage(ArrayList a){
		ar = a;
		setLayout(new BorderLayout());
		
		String[] colomName = {"Select News Title","Save News"};
		// take the nuber of news from ArrayList
		int rowSize=ar.size();
		// Jtable dimension
		Object[][] data = new Object[rowSize][2];
		String n,s="    Save";
		Categories ct;
		for(int i = 0; i < ar.size(); i++){
		ct=(Categories)ar.get(i);
		n = (String)ct.getNewsTitle();
		data[i][0] = n;
		data[i][1] = s;
			
		}

		table = new JTable(data,colomName);
		// Fixing the JTable dimension, font, colom width & add scrollpane
		table.setPreferredScrollableViewportSize(new Dimension(600,500));
		table.getColumnModel().getColumn(0).setPreferredWidth(480);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.setFillsViewportHeight(true);
		table.setRowHeight(40);
		table.setBackground(Color.white);
		table.setForeground(Color.darkGray);
		Font font = new Font("",1,12);
		table.setFont(font);
		JScrollPane scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollpane,BorderLayout.CENTER);
		//Window wd = new Window();
		
		// moise click option
		
		table.addMouseListener (new java.awt.event.MouseAdapter(){
		@Override
			public void mouseClicked(java.awt.event.MouseEvent  evt){
				try{
					//Date date = new Date();
					String sdate;
					Categories  c;
					String link,title,fileName;
					int row=table.rowAtPoint(evt.getPoint());
					int col=table.columnAtPoint(evt.getPoint());
					c=(Categories)ar.get(row);
					link = (String)c.getNewsLink();
					title = (String)c.getNewsTitle();
					//System.out.println(title);
					System.out.println(link);
					//testing chang hbe for saved nes er jnno
					long date = System.currentTimeMillis();
					sdate = String.valueOf(date);
					sdate = sdate.replace(" ","%20");
					System.out.println("date : " + sdate);
					
					// To open selected news from jTable
					if(col == 0){
						LaunchBrowser  lb=new LaunchBrowser(link);
					}
					// for saving news
					else if(col == 1){
						fileName = "E:/javaproject/savednews/" + sdate+ ".html"; 
						System.out.println("link 1: " + fileName);
						// sent the file name where the news save & the link of the news
						GetWebpageContent page= new   GetWebpageContent(fileName,link);
						// Modify news Xml file
						ModifyXMLFile mx = new ModifyXMLFile(title,fileName);
						JOptionPane.showMessageDialog(wd,"News saved!! check Oflline News");
						System.out.println(row);
						System.out.println(col);
					}
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		} );
	
	}


}
