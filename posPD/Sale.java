package posPD;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This is a representation of a sale with a customer
 */
public class Sale
{

	/**
	 * The collection of payments used by customer
	 */
	private ArrayList<Payment> payments;
	/**
	 * The collection of items sold
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * The time of the sale
	 */
	private LocalDateTime dateTime;
	/**
	 * Designates if the sale is tax free
	 */
	private Boolean taxFree;

	public ArrayList<Payment> getPayments()
	{
		return this.payments;
	}

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
	}

	public LocalDateTime getDateTime()
	{
		return this.dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public Boolean getTaxFree()
	{
		return this.taxFree;
	}

	public void setTaxFree(String taxFreeStr)
	{
		if(taxFreeStr.contentEquals("Y"))
			taxFree = true;
		else
			taxFree = false;
	}

	/**
	 * The default constructor
	 */
	public Sale()
	{
		saleLineItems = new ArrayList<SaleLineItem>();
		payments = new ArrayList<Payment>();
		setDateTime(LocalDateTime.now());
		setTaxFree("N");
	}

	/**
	 * The constructor that sets the tax free boolean
	 * @param taxFree What to set the tax free boolean to
	 */
	public Sale(String taxFree)
	{
		this();
		setTaxFree(taxFree);
	}

	/**
	 * Add a payment to the collection
	 * @param payment The payment to be added
	 */
	public void addPayment(Payment payment)
	{
		payments.add(payment);
	}

	/**
	 * Remove a payment from the collection
	 * @param payment The payment to be removed
	 */
	public void removePayment(Payment payment)
	{
		payments.remove(payment);
	}

	/**
	 * Add a sale line item to the collection
	 * @param sli The sale line item to be added
	 */
	public void addSaleLineItem(SaleLineItem sli)
	{
		saleLineItems.add(sli);
	}

	/**
	 * Remove a sale line item from the collection
	 * @param sli The sale line item to be removed
	 */
	public void removeSaleLineItem(SaleLineItem sli)
	{
		saleLineItems.remove(sli);
	}

	/**
	 * Calculate the total cost of sale
	 * @return The cost of the sale
	 */
	public BigDecimal calcTotal()
	{
		BigDecimal total = calcSubTotal().setScale(2,RoundingMode.HALF_UP);

		total = total.add(calcTax());
		
		return total;
	}

	/**
	 * Returns the total cost without the tax amount
	 * @return The total of all the prices
	 */
	public BigDecimal calcSubTotal()
	{
		BigDecimal total = new BigDecimal("0").setScale(2,RoundingMode.HALF_UP);
		for(SaleLineItem sli : saleLineItems)
		{
			total = total.add(sli.calcSubTotal());
		}
		return total;
	}

	/**
	 * The total tax amount of the sale
	 * @return The amount of tax
	 */
	public BigDecimal calcTax()
	{
		BigDecimal total = new BigDecimal("0").setScale(2,RoundingMode.HALF_UP);
		if(!getTaxFree())
		{
			for(SaleLineItem sli : saleLineItems)
			{
				total = total.add(sli.calcTax());
			}
		}
		return total;
	}

	/**
	 * Returns the amount the customer paid
	 * @return The amount the customer paid
	 */
	public BigDecimal getTotalPayments()
	{
		BigDecimal total = new BigDecimal("0").setScale(2,RoundingMode.HALF_UP);
		
		for(Payment payment:getPayments())
		{
			total = total.add(payment.getAmount());
		}
		
		return total;
	}

	/**
	 * Returns if customer's payment was enough
	 * @return If payment covered full cost of sale
	 */
	public Boolean isPaymentEnough()
	{
		Boolean result = false;
		
		if(this.calcAmtTendered().compareTo(this.calcTotal()) >= 0)
			result = true;
		
		return result;
	}

	/**
	 * Calculates how much customer paid
	 * @param amtTendered The amount user paid
	 */
	public void calcAmountForPayment(BigDecimal amtTendered)
	{
		// TODO - implement Sale.calcAmountForPayment
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the change to be given back
	 * @return The change to be returned
	 */
	public BigDecimal calcChange()
	{
		BigDecimal total = calcAmtTendered();
		
		total = total.subtract(calcTotal());
		
		return total;
	}

	/**
	 * Calculate the amount user paid
	 * @return The amount user paid
	 */
	public BigDecimal calcAmtTendered()
	{
		BigDecimal total = new BigDecimal("0").setScale(2,RoundingMode.HALF_UP);
		for(Payment payment:getPayments())
		{
			total = total.add(payment.getAmtTendered());
		}		
		return total;
	}

	/**
	 * Returns the string representation of the sale
	 * @return The representation of the sale
	 */
	public String toString()
	{
		String tempStr= "Sale: SubTotal = "+calcSubTotal()+" Tax = "+calcTax()+" Total = "+calcTotal()+" Payment = "+getTotalPayments().toString()+" Change = "+calcChange().toString();
		
		for(SaleLineItem sli:getSaleLineItems())
		{
			tempStr += "\n     "+sli.toString();
		}
		
		return tempStr;
	}

}