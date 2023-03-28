package posPD;

import java.math.BigDecimal;

/**
 * An abstract class for different kinds of payments
 */
public abstract class Payment
{

	/**
	 * The amount being charged to the customer
	 */
	private BigDecimal amount;
	/**
	 * The amount customer paid
	 */
	private BigDecimal amtTendered;

	public BigDecimal getAmount()
	{
		return this.amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}
	
	public void setAmount(String amount)
	{
		this.amount = new BigDecimal(amount);
	}

	public BigDecimal getAmtTendered()
	{
		return this.amtTendered;
	}
	
	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}

	public void setAmtTendered(String amtTendered)
	{
		this.amtTendered = new BigDecimal(amtTendered);
	}

	/**
	 * Returns if payment was cash
	 * @return If the payment was cash
	 */
	public Boolean countsAsCash()
	{
		// TODO - implement Payment.countsAsCash
		throw new UnsupportedOperationException();
	}

}