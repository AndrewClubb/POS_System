package posPD;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * This is a representation of a cashier
 */
public class Cashier
{

	/**
	 * This is the cashier's number
	 */
	private String number;
	
	/**
	 * The person's data
	 */
	private Person person;
	
	/**
	 * The collection of sessions
	 */
	private ArrayList<Session> sessions;
	
	/**
	 * The cashier's password
	 */
	private String password;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public Person getPerson()
	{
		return this.person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * The default constructor
	 */
	public Cashier()
	{
		sessions = new ArrayList<Session>();
	}

	/**
	 * The constructor  that sets the initial values
	 * @param number The cashier's number
	 * @param person The basic person values
	 * @param session The collection of sessions
	 * @param password The cashier's password
	 */
	public Cashier(String number, String password)
	{
		this();
		setNumber(number);
		setPassword(password);
	}
	
	public Cashier(String number, String password, Person person)
	{
		this();
		setNumber(number);
		setPassword(password);
		setPerson(person);
	}

	/**
	 * Adds a session to the collection
	 * @param session The collection to be added
	 */
	public void addSession(Session session)
	{
		sessions.add(session);
	}

	/**
	 * Removes a session from the collection
	 * @param session The session to be removed
	 */
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * Returns if the inputed password is valid
	 * @param password The password to be checked
	 * @return Returns true if the inputed value is valid
	 */
	public Boolean isAuthorized(String password)
	{
		// TODO - implement Cashier.isAuthorized
		throw new UnsupportedOperationException();
	}
	
	public BigDecimal calcTotalForDate(LocalDate date)
	{
		BigDecimal total = new BigDecimal("0");
		
		for(Session session : this.getSessions())
		{
			if(session.getStartDateTime().toLocalDate().equals(date))
				for(Sale sale : session.getSales())
					total = total.add(sale.calcTotal());
		}
		
		return total;
	}

	/**
	 * Returns a string representation of the cashier
	 * @return The representation of the person
	 */
	public String toString()
	{
		return getNumber() + " " + person.getName();
	}

	public boolean isOkayToDelete() {
		if(getSessions().isEmpty())
			return true;
		return false;
	}
	
	public BigDecimal calcDollarSales(GregorianCalendar date)
	{
		//ask each session if they are on the day given
		return new BigDecimal("0");
	}

	public String calcSaleCountForDate(LocalDate date) {
		Integer count = 0;
		
		for (Session session : this.getSessions())
			if(session.getStartDateTime().toLocalDate().equals(date))
				for(Sale sale : session.getSales())
					count++;
		
		return count.toString();
	}
	
	public BigDecimal calcDiffForDate(LocalDate date) {
		BigDecimal difference = new BigDecimal("0");
		
		for (Session session : this.getSessions())
			if(session.getStartDateTime().toLocalDate().equals(date))
				difference = difference.add(session.getAmountDiff());
		
		return difference;
	}

}