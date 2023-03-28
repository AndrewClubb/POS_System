package posPD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A representation of promotional price
 */
public class PromoPrice extends Price
{

	/**
	 * The date the promotional price ends
	 */
	private LocalDate endDate;

	public LocalDate getEndDate()
	{
		return this.endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-M-d"));
	}

	/**
	 * The default constructor
	 */
	public PromoPrice()
	{
		
	}

	/**
	 * The constructor that sets the price, effective date, and end date
	 * @param price
	 * @param effectiveDate
	 * @param endDate
	 */
	public PromoPrice(String price, String effectiveDate, String endDate)
	{
		this();
		this.setPrice(price);
		this.setEffectiveDate(effectiveDate);
		this.setEndDate(endDate);
	}

	/**
	 * Returns if the promotional price is effective with given date
	 * @param date The date of sale
	 * @return If the price is effective
	 */
	public Boolean isEffective(LocalDate date)
	{
		Boolean result = false;
		
		if(date.isAfter(this.getEffectiveDate()) && date.isBefore(endDate))
			result = true;
		
		return result;
	}

	/**
	 * Returns how the prices compare
	 * @param price The second price to be compared
	 * @return How the prices compare
	 */
	public int compareTo(Price price)
	{
		return this.getEffectiveDate().compareTo(price.getEffectiveDate());
	}

	/**
	 * Returns a string representation of promo price
	 * @return The representation of promo price
	 */
	public String toString()
	{
		return getPrice().toString();
	}

}