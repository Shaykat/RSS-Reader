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

// To store Offline/ saved News only
class SavedNewsStorage extends JPanel {
	JTable table;
	ArrayList ar;
	Window wd;
	public SavedNewsStorage(ArrayList a){
		ar = a;
		setLayout(new BorderLayout());
		
		String[] colomName = {"Select News Title"};
		
		// take the number of news from ArrayList
		int rowSize=ar.size();
		//JTable Dimension
		Object[][] data = new Object[rowSize][1];
		Categories ct;
		String n;
		// Assigniing news Title in JTable
		for(int i = 0; i < ar.size(); i++){
		ct=(Categories)ar.get(i);
		n = (String)ct.getNewsTitle();
		data[i][0] = n;
		}

		table = new JTable(data,colomName);
		// Fixinig JTable Dimension, Colom Width, Font
		table.setPreferredScrollableViewportSize(new Dimension(600,500));
		table.getColumnModel().getColumn(0).setPreferredWidth(480);
		table.setFillsViewportHeight(true);
		table.setRowHeight(40);
		table.setBackground(Color.white);
		table.setForeground(Color.darkGray);
		Font font = new Font("",1,12);
		table.setFont(font);
		JScrollPane scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollpane,BorderLayout.CENTER);
		
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
					
					if(col == 0){
						// Open Browser According to the File name of the saved news
						LaunchBrowser  lb=new LaunchBrowser(link);
					}
					// For further features
					/*else if(col == 1){
						fileName = "F:/javaproject/savednews/" + sdate+ ".html"; 
						System.out.println("link 1: " + fileName);
						
						GetWebpageContent page= new   GetWebpageContent(fileName,link);
						ModifyXMLFile mx = new ModifyXMLFile(title,fileName);
						System.out.println(row);
						System.out.println(col);
					}*/
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		} );
	
	}


}
