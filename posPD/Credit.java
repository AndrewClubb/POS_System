package posPD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A representation of a credit card
 */
public class Credit extends AuthorizedPayment
{

	/**
	 * The card's type
	 */
	private String cardType;
	/**
	 * The account number of the card
	 */
	private String acctNumber;
	/**
	 * The expiration date of the card
	 */
	private LocalDate expireDate;

	public String getCardType()
	{
		return this.cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}

	public String getAcctNumber()
	{
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber)
	{
		this.acctNumber = acctNumber;
	}

	public LocalDate getExpireDate()
	{
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate)
	{
		this.expireDate = expireDate;
	}
	
	public void setExpireDate(String expireDate)
	{
		this.expireDate = LocalDate.parse(expireDate, DateTimeFormatter.ofPattern("MM/d/yyyy"));
	}

	/**
	 * The default constructor
	 */
	public Credit()
	{
		
	}

	/**
	 * The constructor that sets the card type, account number, and expiration date
	 * @param cardType The type of the card
	 * @param acctNumber The account number
	 * @param expireDate The expiration date
	 */
	public Credit(String cardType, String acctNumber, String expireDate)
	{
		this();
		setCardType(cardType);
		setAcctNumber(acctNumber);
		setExpireDate(expireDate);
	}
	
	public Credit(String amount, String amtTendered, String cardType, String acctNumber, String expireDate)
	{
		this();
		setAmount(amount);
		setAmtTendered(amtTendered);
		setCardType(cardType);
		setAcctNumber(acctNumber);
		setExpireDate(expireDate);
	}

	/**
	 * Returns if the card was authorized
	 * @return If the card was authorized
	 */
	public Boolean isAuthorized()
	{
		// TODO - implement Credit.isAuthorized
		throw new UnsupportedOperationException();
	}
	
	public Boolean countsAsCash()
	{
		return false;
	}

	/**
	 * A string representation of the card
	 * @return The representation of the card
	 */
	public String toString()
	{
		// TODO - implement Credit.toString
		throw new UnsupportedOperationException();
	}

}