package posPD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The representation of a tax rate
 */
public class TaxRate implements Comparable<TaxRate>
{

	/**
	 * The BigDecimal value of the tax rate
	 */
	private BigDecimal taxRate;
	
	/**
	 * The day the tax rate becomes effective
	 */
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate()
	{
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate)
	{
		this.taxRate = taxRate.setScale(2,RoundingMode.HALF_UP);
	}
	
	public void setTaxRate(String taxRate)
	{
		this.taxRate = new BigDecimal(taxRate).setScale(2,RoundingMode.HALF_UP);
	}

	public LocalDate getEffectiveDate()
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
	
	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = LocalDate.parse(effectiveDate,DateTimeFormatter.ofPattern("yyyy-M-d"));
	}

	/**
	 * The default constructor
	 */
	public TaxRate()
	{
		
	}

	/**
	 * The constructor that sets the effective date and the rate with inputed data
	 * @param effectiveDate The day the tax rate becomes effective
	 * @param rate The tax rate represented as a BigDecimal
	 */
	public TaxRate(LocalDate effectiveDate, BigDecimal rate)
	{
		this();
		setEffectiveDate(effectiveDate);
		setTaxRate(rate);
	}

	/**
	 * Returns if the tax rate is effective with the given date
	 * @param date The date being used to check if this tax rate is effective
	 * @return Returns true if inputed day matches the effective date. Returns false if  it does not match.
	 */
	public boolean isEffective(LocalDate date)
	{
		return date.isAfter(effectiveDate);
	}

	/**
	 * Compares two tax rates to each other
	 * @param taxRate The second tax rate to be compared to
	 * @return Returns the comparison of two tax rates
	 */
	public int compareTo(TaxRate taxRate)
	{
		return getEffectiveDate().compareTo(taxRate.getEffectiveDate());
	}

	/**
	 * Returns a string representation of tax rate
	 * @return The string representation of tax rate
	 */
	public String toString()
	{
		return taxRate.toString();
	}

}