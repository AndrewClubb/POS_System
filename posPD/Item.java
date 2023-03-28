package posPD;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

/**
 * A representation of an item
 */
public class Item
{

	/**
	 * The item's number
	 */
	private String number;
	
	/**
	 * The item's description
	 */
	private String description;
	
	/**
	 * The line the item is within
	 */
	private SaleLineItem saleLineItem;
	
	/**
	 * The collection of upcs
	 */
	private TreeMap<String, UPC> uPCs;
	
	/**
	 * The collection of prices
	 */
	private TreeSet<Price> prices;
	
	/**
	 * The tax category for the item
	 */
	private TaxCategory taxCategory;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public SaleLineItem getSaleLineItem()
	{
		return this.saleLineItem;
	}

	public void setSaleLineItem(SaleLineItem saleLineItem)
	{
		this.saleLineItem = saleLineItem;
	}

	public TreeMap<String, UPC> getUPCs()
	{
		return this.uPCs;
	}

	public TreeSet<Price> getPrices()
	{
		return this.prices;
	}

	public TaxCategory getTaxCategory()
	{
		return this.taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategory = taxCategory;
	}

	/**
	 * The default constructor
	 */
	public Item()
	{
		uPCs = new TreeMap<String, UPC>();
		prices = new TreeSet<Price>();
	}

	/**
	 * The constructor that sets the number and description
	 * @param number The item's number
	 * @param description The item's description
	 */
	public Item(Store store, String number, String description, String taxCategory)
	{
		this();
		setNumber(number);
		setDescription(description);
		setTaxCategory(store.findTaxCategoryByName(taxCategory));
		store.addItem(this);
	}

	/**
	 * Adds a price to the collection
	 * @param price The price to be added
	 */
	public void addPrice(Price price)
	{
		prices.add(price);
	}
	
	public void addPromoPrice(PromoPrice promoPrice)
	{
		prices.add(promoPrice);
	}

	/**
	 * Removes a price from the collection
	 * @param price The price to be removed
	 */
	public void removePrice(Price price)
	{
		prices.remove(price);
	}

	/**
	 * Adds a upc to the collection
	 * @param upc The upc to be added
	 */
	public void addUPC(UPC upc)
	{
		uPCs.put(upc.getUPC(), upc);
	}

	/**
	 * Removes a upc from the collection
	 * @param upc The upc to be removed
	 */
	public void removeUPC(UPC upc)
	{
		uPCs.remove(upc.getUPC());
	}

	/**
	 * Returns the price linked with the given date
	 * @param date The day used for the search
	 * @return The price linked with the date
	 */
	public Price getPriceForDate(LocalDate date)
	{
		Price effectivePrice = new Price();
		for (Price price : getPrices())
		{
			if(price.isEffective(date))
				effectivePrice = price;
		}
		return effectivePrice;
	}

	/**
	 * The tax rate linked with the given date
	 * @param date The day to be searched with
	 * @return The tax rate linked with the date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{
		return getTaxCategory().getTaxRateforDate(date).setScale(2,RoundingMode.HALF_UP);
	}

	/**
	 * Returns the price with given date and quantity
	 * @param date The date of sale
	 * @param quantity The number of items in sale
	 * @return The price of items
	 */
	public BigDecimal calcAmountForDataQty(LocalDate date, int quantity)
	{
		Price effectivePrice = new Price();
		
		for(Price price : prices)
		{
			if (price.isEffective(date))
				effectivePrice = price;
		}
		
		return effectivePrice.calcAmountForQty(quantity).setScale(2,RoundingMode.HALF_UP);
	}

	/**
	 * Returns a string representation of the item
	 * @return The representation of the item
	 */
	public String toString()
	{
		return number+" "+description+" Price:"+getPriceForDate(LocalDate.now()).toString()+" Tax Rate:"+getTaxRateForDate(LocalDate.now()).toString();
	}

}