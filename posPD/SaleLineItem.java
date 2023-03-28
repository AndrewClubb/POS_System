package posPD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * A representation of a line of sale
 */
public class SaleLineItem
{

	/**
	 * The item being sold
	 */
	private Item item;
	/**
	 * The quantity of the item
	 */
	private int quantity;
	/**
	 * The sale this line is apart of
	 */
	private Sale sale;

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getQuantity()
	{
		return this.quantity;
	}

	public void setQuantity(String quantity)
	{
		
		this.quantity = Integer.parseInt(quantity);
	}

	public Sale getSale()
	{
		return this.sale;
	}

	public void setSale(Sale sale)
	{
		this.sale = sale;
	}

	/**
	 * The default constructor
	 */
	public SaleLineItem()
	{
		
	}

	/**
	 * The constructor that sets the sale, item, and quantity values
	 * @param sale The sale the line is apart of
	 * @param item The item being sold
	 * @param quantity The quantity of the item
	 */
	public SaleLineItem(Sale sale, Item item, String quantity)
	{
		this();
		this.setSale(sale);
		this.setItem(item);
		this.setQuantity(quantity);
	}

	/**
	 * Returns the total, without the tax, of the line
	 * @return The price of items without tax
	 */
	public BigDecimal calcSubTotal()
	{
		return item.calcAmountForDataQty(sale.getDateTime().toLocalDate(), quantity).setScale(2,RoundingMode.HALF_UP);
	}

	/**
	 * Returns the tax of the line
	 * @return The tax amount
	 */
	public BigDecimal calcTax()
	{
		return calcSubTotal().multiply(item.getTaxRateForDate(sale.getDateTime().toLocalDate())).setScale(2,RoundingMode.HALF_UP);
	}

	/**
	 * Returns a string representation of the sale line
	 * @return The representation of sale line
	 */
	public String toString()
	{
		return getItem().getNumber()+" "+getItem().getDescription()+" "+getQuantity()+" "+calcSubTotal()+" "+calcTax();
	}

}