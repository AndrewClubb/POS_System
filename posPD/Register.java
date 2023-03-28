package posPD;

import java.util.ArrayList;

/**
 * A representation of a register
 */
public class Register
{

	/**
	 * The register's identifying number
	 */
	private String number;
	/**
	 * The cash drawer inside the register
	 */
	private CashDrawer cashDrawer;
	
	private ArrayList<Session> sessions; 

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public CashDrawer getCashDrawer()
	{
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer)
	{
		this.cashDrawer = cashDrawer;
	}
	
	public ArrayList<Session> getSessions()
	{
		return sessions;
	}

	/**
	 * The default constructor
	 */
	public Register()
	{
		sessions = new ArrayList<Session>();
		cashDrawer = new CashDrawer();
	}

	/**
	 * The constructor that sets the register's identification number
	 * @param number The register's identification number
	 */
	public Register(String number)
	{
		this();
		this.setNumber(number);
	}
	
	public Register(CashDrawer cashDrawer, String number)
	{
		this();
		setCashDrawer(cashDrawer);
		setNumber(number);
	}
	
	public void addSession(Session session)
	{
		sessions.add(session);
	}
	
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * Returns a string representation of the register
	 * @return The representation of register
	 */
	public String toString()
	{
		return number;
	}

	public boolean isOkayToDelete() {
		if(getSessions().isEmpty())
			return true;
		return false;
	}

}