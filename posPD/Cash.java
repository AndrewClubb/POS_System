package posPD;

import java.math.BigDecimal;

/**
 * Represents if payment was cash
 */
public class Cash extends Payment
{

	/**
	 * The default constructor
	 */
	public Cash()
	{
		
	}

	/**
	 * The constructor that sets the amount and payment amount
	 * @param amount The amount being charged
	 * @param amtTendered The amount paid
	 */
	public Cash(String amount, BigDecimal amtTendered)
	{
		this();
		this.setAmount(amount);
		this.setAmtTendered(amtTendered);
	}
	
	public Cash(String amount, String amtTendered)
	{
		this();
		this.setAmount(amount);
		this.setAmtTendered(amtTendered);
	}

	/**
	 * Returns true if customer used cash
	 * @return If the payment was cash
	 */
	public Boolean countsAsCash()
	{
		return true;
	}

	/**
	 * A string representation of cash
	 * @return The representation of cash
	 */
	public String toString()
	{
		// TODO - implement Cash.toString
		throw new UnsupportedOperationException();
	}

}