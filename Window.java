//*************************Related Pacakages*****************************************
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class Window  extends JFrame{
	
	//menubar items
	private JMenuBar menubar;
	private JMenu     BDNews24;
	private JMenuItem sport,politics,cricket,bangladesh,world;
	private JMenu     BBCNews,readOffline;
	private JMenuItem allNews;
	private JMenu     ESPNCrickInfo;
	private JMenu     ESPNFootballInfo;
	//Buttons
	private JButton button1,button2,button3,button4;     
	private JButton buttonOffline;
	
	//basic jpanel for main frame
	private JPanel panel,west,center; 
	//work as a Jpanel component
	private NewsStorage ns;
	private Window self;
	private SavedNewsStorage sns;
	 //for saving currnt jtable  component
	private JPanel prevNs; 
	//for table
	private JTable table;
	private ArrayList ar;

	// window constructor 
	public   Window()  {
		super("RSS NEWSPAPER READER");
		self = this;
		setLayout(new BorderLayout());
		//................................................ JPanel...........................................
		panel = new JPanel();
		west = new JPanel();
		//center = new JPanel();

		// ........................................title border add...........................
		//main
		TitledBorder panelBorder = BorderFactory.createTitledBorder("");
		panelBorder.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(panelBorder);
		//west
		TitledBorder westBorder = BorderFactory.createTitledBorder("NewsPapers");
		westBorder.setTitleJustification(TitledBorder.CENTER);
		west.setBorder(westBorder);
		// end of border.......................................................................////////////////////////

		button1 = new JButton("BBC NEWS");
		button2 = new JButton("ESPN FOOTBALL");
		button3 = new JButton("ESPN CRICINFO");
		button4 = new JButton("BDNEWS24");
		buttonOffline = new JButton("READ OFFLINE");
		// ...................................................................................................................

		//////// add button to west panel..............................

		west.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//1st row
		gbc.insets = new Insets(5,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		button1.setToolTipText("53 News Avaiable");
		west.add(button1,gbc);

		//2nd row
		//gbc.insets = new Insets(5,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		button2.setToolTipText("20 News Avaiable");
		west.add(button2,gbc);

		//3rd row
		//gbc.insets = new Insets(5,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		button3.setToolTipText("20 News Avaiable");
		west.add(button3,gbc);

		//4th row
		gbc.insets = new Insets(5,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		button4.setToolTipText("5 Categories Avaiable");
		west.add(button4,gbc);

		//5th row
		gbc.insets = new Insets(40,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttonOffline.setToolTipText("Read Saved News on Offline");
		west.add(buttonOffline,gbc);
		// end of west panel ................................................

		// Action Event ..........................................................................
		Event e = new Event(this);

		button1.addActionListener(e);
		button2.addActionListener(e);
		button3.addActionListener(e);
		button4.addActionListener(e);
		buttonOffline.addActionListener(e);
		// end of add button listener .....................................

		// Jmanu bar ........................................................
		menubar=new JMenuBar();
		setJMenuBar(menubar);
		//adding BDnews24 into menubar
		BDNews24 =  new JMenu("BDNews24");
		menubar.add(BDNews24);

		//adding jmenuitem
		bangladesh  = new JMenuItem("Bangladesh");
		BDNews24.add(bangladesh);
		bangladesh.addActionListener(e);

		sport  = new JMenuItem("Sport");
		BDNews24.add(sport);
		sport.addActionListener(e);

		world  = new JMenuItem("World");
		BDNews24.add(world);
		world.addActionListener(e);

		politics  = new JMenuItem("Politics");
		BDNews24.add(politics);
		politics.addActionListener(e);

		cricket  = new JMenuItem("Cricket");
		BDNews24.add(cricket);
		cricket.addActionListener(e);

		BBCNews =  new JMenu("BBCNews");
		menubar.add(BBCNews);
		allNews  = new JMenuItem("allNewsBBC");
		BBCNews.add(allNews);
		allNews.addActionListener(e);

		ESPNCrickInfo=  new JMenu("ESPNCrickInfo");
		allNews  = new JMenuItem("allNewsESPNC");
		menubar.add(ESPNCrickInfo);
		ESPNCrickInfo.add(allNews);
		allNews.addActionListener(e);

		ESPNFootballInfo=  new JMenu("ESPNFootBallInfo");
		menubar.add(ESPNFootballInfo);
		allNews  = new JMenuItem("allNewsESPNF");
		ESPNFootballInfo.add(allNews);
		allNews.addActionListener(e);

		readOffline = new JMenu("ReadOffline");
		menubar.add(readOffline);
		allNews  = new JMenuItem("Saved News");
		readOffline.add(allNews);
		allNews.addActionListener(e);
		// end of menu bar ....................................................///////

		// add panels ..........................................................................
		setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		panel.add(west,BorderLayout.WEST);
		//panel.add(center,BorderLayout.CENTER);
		add(panel,BorderLayout.WEST);

	}
	
	/// EVENT CLASS.......................................................................
	class Event implements ActionListener{
		private Window wd;
		public Event(Window w){
			wd = w;
		}
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("allNewsBBC") || e.getActionCommand().equals("BBC NEWS")){
				try{
				BBCNews  bbn = new BBCNews(self,"http://feeds.bbci.co.uk/news/rss.xml","//item");
			        bbn.xmlParse();
				}
				catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(wd,"Sorry!!! please check your Internet Connection. ");
				}
			}
			else if(e.getActionCommand().equals("Bangladesh") || e.getActionCommand().equals("Sport") || e.getActionCommand().equals("Politics") || e.getActionCommand().equals("Cricket") || e.getActionCommand().equals("World")){
				try{
					String selectedCetagory = (String)e.getActionCommand();
					BDNews24 bdn = new BDNews24(self, selectedCetagory,"http://bdnews24.com/?widgetName=rssfeed&widgetId=1150&getXmlFeed=true","//item");
					bdn.xmlParse();
				}
			       catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(wd,"Sorry!!! please check your Internet Connection. ");
				}
				//w.setVisible(false);
			}
			else if(e.getActionCommand().equals("BDNEWS24")){
				int messageType = JOptionPane.QUESTION_MESSAGE;
				String[] cetagories = {"Bangladesh","Politics","Sport","Cricket","World"};
				int index = JOptionPane.showOptionDialog(wd,"Which Cetagories Do You Prefer?", "News Cetagories",0,messageType,null,cetagories,0);
				System.out.println("index: " + index);
				try{
					BDNews24 bdn = new BDNews24(self, cetagories[index],"http://bdnews24.com/?widgetName=rssfeed&widgetId=1150&getXmlFeed=true","//item");
					bdn.xmlParse();
				}
				catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(wd,"Sorry!!! please check your Internet Connection. ");
				}
			}

			else if(e.getActionCommand().equals("allNewsESPNC") || e.getActionCommand().equals("ESPN CRICINFO")){
				try{
					EspnCricInfo  espc = new EspnCricInfo(self,"http://www.espncricinfo.com/rss/content/story/feeds/25.xml","//item");
					espc.xmlParse();
				}
				catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(wd,"Sorry!!! please check your Internet Connection. ");
				}
			}
			else if(e.getActionCommand().equals("allNewsESPNF") || e.getActionCommand().equals("ESPN FOOTBALL")){
				try{
					ESPNFootBallInfo  espnf = new ESPNFootBallInfo(self,"http://www.espnfc.com/rss","//item");
					espnf.xmlParse();
				}
				catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(wd,"Sorry!!! please check your Internet Connection. ");
				}
			}
			else if(e.getActionCommand().equals("Saved News") || e.getActionCommand().equals("READ OFFLINE")){
				SaveNews sn = new SaveNews(self,"E://javaproject/news.xml","//item");
			        sn.xmlParse();
				
			}
		}
	}
	
	// removing existing JTable component
	public void removePanel()
	{
		if(panel.getComponentCount()>1){
			panel.remove(prevNs);
			revalidate();
			repaint();
		}
	}
	
	//Adding current JTable Component
	public void changePanel(ArrayList a){
		ar = a;
		NewsStorage ns= new NewsStorage(ar);
		panel.add(ns,BorderLayout.CENTER);
		prevNs=ns;
		revalidate();
		repaint();
	}
	//for saved news JPanel
	public void saveChangePanel(ArrayList a){
		ar = a;
		SavedNewsStorage sns= new SavedNewsStorage(ar);
		panel.add(sns,BorderLayout.CENTER);
		prevNs=sns;
		revalidate();
		repaint();
	}

	public static void main(String args[]){
		Window  gui = new Window();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(800,700);
		gui.setVisible(true);
	}

}
