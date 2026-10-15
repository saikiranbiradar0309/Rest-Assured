package POJO;

public class Posts {
	
	private int userId;
	private String title;
	private String body;
	
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	
	public int getUserId()
	{
		return this.userId;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getBody()
	{
		return this.body;
	}
}
