package posPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A representation of a price
 */
public class Price implements Comparable<Price>
{

	/**
	 * The price
	 */
	private BigDecimal price;
	
	/**
	 * The day the price becomes effective
	 */
	private LocalDate effectiveDate;
	
	/**
	 * The item
	 */
	private Item item;

	public BigDecimal getPrice()
	{
		return this.price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public void setPrice(String price)
	{
		this.price = new BigDecimal(price);
	}

	public LocalDate getEffectiveDate()
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("yyyy-M-d"));
	}

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * The default constructor
	 */
	public Price()
	{
		
	}

	/**
	 * The constructor that sets the price and effective date
	 * @param price The price
	 * @param effectiveDate The date the price becomes effective
	 */
	public Price(String price, String effectiveDate, Item item)
	{
		this();
		setPrice(new BigDecimal(price));
		setEffectiveDate(effectiveDate);
		setItem(item);
		item.addPrice(this);		
	}
	
	public Price(String price, String effectiveDate)
	{
		this();
		setPrice(new BigDecimal(price));
		setEffectiveDate(effectiveDate);		
	}

	/**
	 * Returns if the price is effective compared to given date
	 * @param date The date of sale
	 * @return If the price is effective
	 */
	public Boolean isEffective(LocalDate date)
	{
		Boolean result = false;
		if (date.isAfter(effectiveDate))
			result = true;
		return result;
	}

	/**
	 * Returns the cost with given number of items
	 * @param quantity The number of items
	 * @return The price for quantity
	 */
	public BigDecimal calcAmountForQty(int quantity)
	{
		BigDecimal total = getPrice();
		Integer intQuantity = quantity;
		BigDecimal quantityDecimal = new BigDecimal(intQuantity.toString());
		
		total = total.multiply(quantityDecimal);
		
		return total;
	}

	/**
	 * Returns how the two prices compare
	 * @param price The second price being compared
	 * @return How the prices compare
	 */
	public int compareTo(Price price)
	{
		return getEffectiveDate().compareTo(price.getEffectiveDate());
	}

	/**
	 * Returns a string representation of price
	 * @return The representation of price
	 */
	public String toString()
	{
		return price.toString();		
	}

	public boolean isOkayToDelete() {
		return true;
	}

}