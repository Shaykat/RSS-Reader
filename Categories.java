//...........................................................Type of Arraylist  ......................................................

public class Categories{
	private String newsTitle;
	private String newsLink;
	
	public Categories(String title,String link){
		this.newsTitle = title;
		this.newsLink = link;
	}
	
	public void setNewTitle(String title){
		this.newsTitle = title;
	}
	public void setNewsLink(String link){
		this.newsLink = link;
	}
	public String getNewsTitle(){
		return newsTitle;
	}
	public String getNewsLink()
	{
		return  newsLink;
	}
	
}