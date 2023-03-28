package posPD;
/**
 * This is a representation of a person
 */
public class Person
{

	/**
	 * The person's name
	 */
	private String name;
	
	/**
	 * The person's address
	 */
	private String address;
	
	/**
	 * The city the person lives in
	 */
	private String city;
	
	/**
	 * The state the person lives in
	 */
	private String state;
	
	/**
	 * The zip code the person lives in
	 */
	private String zip;
	
	/**
	 * The person's phone number
	 */
	private String phone;
	
	/**
	 * The person's social security number
	 */
	private String SSN;
	
	/**
	 * The collection of cashier
	 */
	private Cashier cashier;

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return this.city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return this.zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getSSN()
	{
		return this.SSN;
	}

	public void setSSN(String SSN)
	{
		this.SSN = SSN;
	}

	public Cashier getCashier()
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}

	/**
	 * The default constructor
	 */
	public Person()
	{
		
	}

	/**
	 * The constructor that sets the name, address, city, state, zip, phone, SSN, and the collection of cashier.
	 * @param name The person's name
	 * @param address The person's address
	 * @param city The person's city
	 * @param state The person's state
	 * @param zip The person's zip code
	 * @param phone The person's phone number
	 * @param SSN The person's SSN
	 * @param cashier The collection of cashier
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String SSN, Cashier cashier)
	{
		this();
		setName(name);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
		setPhone(phone);
		setSSN(SSN);
		cashier.setPerson(this);
	}
	
	public Person(String name, String address, String city, String state, String zip, String phone, String SSN)
	{
		this();
		setName(name);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
		setPhone(phone);
		setSSN(SSN);
	}

	/**
	 * Returns a string representation of the person
	 * @return The representation of the person
	 */
	public String toString()
	{
		return "name:"+name+" address:"+address+" city:"+city+" state:"+state+" zip:"+zip+" phone:"+phone+" SSN:"+SSN;
	}

}