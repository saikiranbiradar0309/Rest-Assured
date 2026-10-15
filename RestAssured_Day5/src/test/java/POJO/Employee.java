package POJO;

public class Employee {
	
	String firstName;
	String lastName;
	String mobileNumber;
	String country;
	String email;
	
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getCountry() {
		return country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
}
