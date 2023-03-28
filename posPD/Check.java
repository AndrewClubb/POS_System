package posPD;
/**
 * A representation of a check
 */
public class Check extends AuthorizedPayment
{

	/**
	 * The routing number of the check
	 */
	private String routingNumber;
	/**
	 * The account number of the check
	 */
	private String accountNumber;
	/**
	 * The check number of the check
	 */
	private String checkNumber;

	public String getRoutingNumber()
	{
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber)
	{
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber()
	{
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber()
	{
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber)
	{
		this.checkNumber = checkNumber;
	}

	/**
	 * The default constructor
	 */
	public Check()
	{
		
	}

	/**
	 * The constructor that sets the amount, account number, and check number
	 * @param amount The amount to be paid
	 * @param accoutNumber The account number
	 * @param checkNumber The check number
	 */
	public Check(String amount, String accountNumber, String checkNumber, String routingNumber)
	{
		this();
		setAmount(amount);
		setAccountNumber(accountNumber);
		setCheckNumber(checkNumber);
		setRoutingNumber(routingNumber);
	}
	
	public Check(String amount, String amtTendered, String routingNumber, String accountNumber, String checkNumber)
	{
		this();
		setAmount(amount);
		setAmtTendered(amtTendered);
		setRoutingNumber(routingNumber);
		setAccountNumber(accountNumber);
		setCheckNumber(checkNumber);
	}

	/**
	 * Returns if the check is authorized
	 * @return If the check is authorized
	 */
	public Boolean isAuthorized()
	{
		// TODO - implement Check.isAuthorized
		throw new UnsupportedOperationException();
	}
	
	public Boolean countsAsCash()
	{
		return false;
	}

	/**
	 * A string representation of the check
	 * @return The representation of the check
	 */
	public String toString()
	{
		// TODO - implement Check.toString
		throw new UnsupportedOperationException();
	}

}