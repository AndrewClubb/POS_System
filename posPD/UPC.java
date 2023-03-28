package posPD;
/**
 * A representation of universal product codes
 */
public class UPC
{

	/**
	 * The universal product code
	 */
	private String uPC;
	
	/**
	 * The item linked to UPC
	 */
	private Item item;

	public String getUPC()
	{
		return this.uPC;
	}

	public void setUPC(String uPC)
	{
		this.uPC = uPC;
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
	public UPC()
	{
		
	}

	/**
	 * The constructor that sets the upc
	 * @param upc The upc
	 */
	public UPC(String upc, Item item)
	{
		this();
		setUPC(upc);
		setItem(item);
		item.addUPC(this);
	}

	/**
	 * Returns a string representation of the upc
	 * @return The representation of the upc
	 */
	public String toString()
	{
		return uPC;
	}

	public boolean isOkayToDelete() {
		return true;
	}

}