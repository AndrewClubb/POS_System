package posPD;

import java.math.BigDecimal;

/**
 * This represents a cash drawer inside a register
 */
public class CashDrawer
{

	/**
	 * The amount of cash within the drawer
	 */
	private BigDecimal cashAmount;
	/**
	 * Designates if drawer is open or closed
	 */
	private int position;

	public BigDecimal getCashAmount()
	{
		return this.cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount)
	{
		this.cashAmount = cashAmount;
	}

	public int getPosition()
	{
		return this.position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	/**
	 * The default constructor
	 */
	public CashDrawer()
	{
		cashAmount = new BigDecimal("0");
	}

	/**
	 * Adds the inputed amount of cash into the cash amount
	 * @param cash The cash amount to be added
	 */
	public void addCash(BigDecimal cash)
	{
		// TODO - implement CashDrawer.addCash
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes the inputed amount of cash from the cash amount
	 * @param cash The cash amount to be removed
	 */
	public void removeCash(BigDecimal cash)
	{
		// TODO - implement CashDrawer.removeCash
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a string representation of the cash drawer
	 * @return The representation of the cash drawer
	 */
	public String toString()
	{
		return "Cash Amount:"+cashAmount.toString()+" Position:"+position;
	}

}