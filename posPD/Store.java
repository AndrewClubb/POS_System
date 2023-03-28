package posPD;

import java.util.*;

import posDM.StoreDM;

/**
 * This is a representation of a store
 */
public class Store
{

	/**
	 * A number to designate a store
	 */
	private String number;
	
	/**
	 * The name of the store
	 */
	private String name;
	
	/**
	 * The collection of tax categories
	 */
	private TreeMap<String, TaxCategory> taxCategories;
	
	/**
	 * The collection of items within the store
	 */
	private TreeMap<String, Item> items;
	
	/**
	 * The collection of cashiers
	 */
	private TreeMap<String, Cashier> cashiers;
	
	/**
	 * The collection of registers
	 */
	private TreeMap<String, Register> registers;
	
	/**
	 * The collection of sessions
	 */
	private ArrayList<Session> sessions;
	
	/**
	 * The collection of UPCs
	 */
	private TreeMap<String, UPC> upcs;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TreeMap<String, TaxCategory> getTaxCategories()
	{
		return this.taxCategories;
	}

	public TreeMap<String, Item> getItems()
	{
		return this.items;
	}

	public TreeMap<String, Cashier> getCashiers()
	{
		return this.cashiers;
	}

	public TreeMap<String, Register> getRegisters()
	{
		return this.registers;
	}

	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}

	public TreeMap<String, UPC> getUpcs()
	{
		return this.upcs;
	}

	public void setUpcs(TreeMap<String, UPC> upcs)
	{
		this.upcs = upcs;
	}

	/**
	 * The default constructor
	 */
	public Store()
	{
		items = new TreeMap<String, Item>();
		taxCategories = new TreeMap<String, TaxCategory>();
		upcs = new TreeMap<String, UPC>();
		cashiers = new TreeMap<String, Cashier>();
		sessions = new ArrayList<Session>();
		registers = new TreeMap<String, Register>();
	}

	/**
	 * The constructor that accepts store's number and store's name
	 * @param number The store number
	 * @param name The store name
	 */
	public Store(String number, String name)
	{
		this();
		this.setNumber(number);
		this.setName(name);
	}

	/**
	 * Add an item to the store's collection
	 * @param item The item to be added
	 */
	public void addItem(Item item)
	{
		items.put(item.getNumber(), item);
	}

	/**
	 * Remove an item from the store's collection
	 * @param item The item to be removed
	 */
	public void removeItem(Item item)
	{
		items.remove(item.getNumber());
	}

	/**
	 * Add a UPC to the store's collection
	 * @param upc the UPC to be added
	 */
	public void addUPC(UPC upc)
	{
		upcs.put(upc.getUPC(), upc);
	}

	/**
	 * Remove a UPC from the store's collection
	 * @param upc The UPC to be removed
	 */
	public void removeUPC(UPC upc)
	{
		upcs.remove(upc.getUPC());
	}

	/**
	 * Add a register to the store's collection
	 * @param register The register to be added
	 */
	public void addRegister(Register register)
	{
		registers.put(register.getNumber(), register);
	}

	/**
	 * Remove a register from the store's collection
	 * @param register The register to be removed
	 */
	public void removeRegister(Register register)
	{
		registers.remove(register.getNumber());
	}

	/**
	 * Add a cashier to the store's collection
	 * @param cashier The cashier to be added
	 */
	public void addCashier(Cashier cashier)
	{
		cashiers.put(cashier.getNumber(), cashier);
	}

	/**
	 * Remove a cashier from the store's collection
	 * @param cashier The cashier to be removed
	 */
	public void removeCashier(Cashier cashier)
	{
		cashiers.remove(cashier.getNumber());
	}

	/**
	 * Add a tax category to the store's collection
	 * @param taxCategory The tax category to be added
	 */
	public void addTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Remove a tax category from the store's collection
	 * @param taxCategory The tax category to be removed
	 */
	public void removeTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.remove(taxCategory.getCategory());
	}

	/**
	 * Add a session to the store's collection
	 * @param session The session to be added
	 */
	public void addSession(Session session)
	{
		sessions.add(session);
	}

	/**
	 * Remove a session from the store's collection
	 * @param session The session to be removed
	 */
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * Returns the register that is linked to the inputed number
	 * @param number The register's number
	 * @return The register linked with the inputed number
	 */
	public Register findRegisterByNumber(String number)
	{
		return registers.get(number);
	}

	/**
	 * Returns the item linked to the inputed UPC
	 * @param upc The item's UPC
	 * @return The item linked with the inputed UPC
	 */
	public Item findItemForUPC(String inUpc)
	{
		Item itemFound = null;
		for(Item item : getItems().values())
		{
			for(UPC upc : item.getUPCs().values())
			{
				if(inUpc.contentEquals(upc.getUPC()))
					itemFound = item;
			}
		}
		return itemFound;
	}

	/**
	 * Returns the item linked to the inputed number
	 * @param number The item's number
	 * @return The item linked with the inputed number
	 */
	public Item findItemForNumber(String number)
	{
		return items.get(number);
	}

	/**
	 * Returns the cashier linked to the inputed number
	 * @param number The cashier's number
	 * @return The cashier being searched for
	 */
	public Cashier findCashierForNumber(String number)
	{
		return cashiers.get(number);
	}

	/**
	 * Returns the tax category that is linked to the inputed name
	 * @param category The tax category's name
	 * @return The tax category linked with the inputed name
	 */
	public TaxCategory findTaxCategoryByName(String category)
	{
		if(category.length() > 0)
		{
			return taxCategories.get(category);
		}
		else
			return null;
	}
	
	public void openStore()
	{
		StoreDM.loadStore(this);
	}

	/**
	 * The string representation of the store
	 * @return The string storing the representation of the store
	 */
	public String toString()
	{	
		return name;
	}

	public boolean isOkayToDeleteTC(TaxCategory taxCategory) {
		Boolean result = true;
		for(Item item:getItems().values())
		{
			if(item.getTaxCategory() == taxCategory)
				result = false;
		}
		return result;
	}

}