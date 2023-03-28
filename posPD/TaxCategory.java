package posPD;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This is a representation of a tax category
 */
public class TaxCategory
{

	/**
	 * The name of the category
	 */
	private String category;
	
	/**
	 * The collection of tax rates
	 */
	private TreeSet<TaxRate> taxRates;

	public String getCategory()
	{
		return this.category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public TreeSet<TaxRate> getTaxRates()
	{
		return this.taxRates;
	}

	/**
	 * The default constructor
	 */
	public TaxCategory()
	{
		taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * The constructor setting the category's name
	 * @param category The name of the category
	 */
	public TaxCategory(String category, String effectiveDate, String taxRate)
	{
		this();
		setCategory(category);
		addTaxRate(new TaxRate(LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yyyy")), new BigDecimal(taxRate)));
	}

	/**
	 * adds the inputed tax rate to the collection
	 * @param taxRate The tax rate to be added
	 */
	public void addTaxRate(TaxRate taxRate)
	{
		taxRates.add(taxRate);
	}

	/**
	 * Removes the tax rate given from the collection
	 * @param taxRate The tax rate to be removed
	 */
	public void removeTaxRate(TaxRate taxRate)
	{
		taxRates.remove(taxRate);
	}

	/**
	 * Returns the tax rate for the inputed date
	 * @param date The date being used to search with
	 * @return The Big Decimal representation of the tax rate
	 */
	public BigDecimal getTaxRateforDate(LocalDate date)
	{
		BigDecimal effectiveRate = new BigDecimal("0").setScale(2,RoundingMode.HALF_UP);
		
		for(TaxRate taxRate : taxRates)
		{
			if(taxRate.isEffective(date))
				effectiveRate = taxRate.getTaxRate();
		}
		
		return effectiveRate;
	}

	/**
	 * Returns a string representation of tax category
	 * @return The representation of the tax category
	 */
	public String toString()
	{
		return category;
	}

}