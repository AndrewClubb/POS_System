package posPD;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The representation of a session
 */
public class Session
{

	/**
	 * The cashier owning the session
	 */
	private Cashier cashier;
	/**
	 * The register linked with the session
	 */
	private Register register;
	/**
	 * The collection of sales within the session
	 */
	private ArrayList<Sale> sales;
	/**
	 * The session's start time
	 */
	private LocalDateTime startDateTime;
	/**
	 * The session's end time
	 */
	private LocalDateTime endDateTime;
	
	private BigDecimal amountDiff;

	public Cashier getCashier()
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}

	public Register getRegister()
	{
		return this.register;
	}

	public void setRegister(Register register)
	{
		this.register = register;
	}

	public Collection<Sale> getSales()
	{
		return this.sales;
	}

	public LocalDateTime getStartDateTime()
	{
		return this.startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime()
	{
		return this.endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime)
	{
		this.endDateTime = endDateTime;
	}

	public BigDecimal getAmountDiff() {
		return amountDiff;
	}

	public void setAmountDiff(BigDecimal amountDiff) {
		this.amountDiff = amountDiff;
	}

	/**
	 * The default constructor
	 */
	public Session()
	{
		sales = new ArrayList<Sale>();
		this.setStartDateTime(LocalDateTime.now());
		amountDiff = new BigDecimal("0");
	}

	/**
	 * The constructor setting the cashier and register
	 * @param cashier The cashier that runs the session
	 * @param register The register used within the session
	 */
	public Session(Cashier cashier, Register register)
	{
		this();
		setCashier(cashier);
		cashier.addSession(this);
		setRegister(register);
		register.addSession(this);
	}

	/**
	 * Adds a sale to the collection
	 * @param sale The sale to be added
	 */
	public void addSale(Sale sale)
	{
		sales.add(sale);
	}

	/**
	 * Remove a sale from the session
	 * @param sale The sale to be removed
	 */
	public void removeSale(Sale sale)
	{
		sales.remove(sale);
	}

	/**
	 * Returns the difference between the starting cash amount to the ending amount
	 * @param cash The session's starting amount of cash
	 * @return The difference from the session's starting amount to the end amount
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cash)
	{
		BigDecimal totalCash = this.getRegister().getCashDrawer().getCashAmount();
		
		for(Sale sale : this.getSales())
		{
			for(Payment payment : sale.getPayments())
			{
				if(payment.countsAsCash())
					totalCash = totalCash.add(payment.getAmount());
			}
		}
		
		totalCash = cash.subtract(totalCash);
		
		return totalCash;
	}

	/**
	 * Returns a string representation of the session
	 * @return The representation of the session
	 */
	public String toString()
	{
		BigDecimal total = new BigDecimal("0");
		
		for(Sale sale : getSales())
		{
			total = total.add(sale.calcTotal());
		}
		
		String tempStr = "Session : Cashier :"+getCashier().getPerson().getName()+" Register:"+getRegister().getNumber();
		tempStr += " Date : "+getStartDateTime().toString()+" Total : "+total;
		
		for(Sale sale: getSales())
		{
			tempStr += "\n  "+sale.toString()+"\n";
		}
		
		return tempStr;
	}
}