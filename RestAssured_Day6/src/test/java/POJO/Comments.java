package POJO;

public class Comments {
	
	private int postId;
	
	private String name;
	
	private String email;
	
	private String body;
	
	public void setPostId(int postId)
	{
		this.postId = postId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	
	public int getPostId()
	{
		return this.postId;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getBody()
	{
		return this.body;
	}
}
