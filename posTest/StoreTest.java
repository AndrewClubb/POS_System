package posTest;

import posPD.*;

public class StoreTest {
	
	public static void storeTest1(Store store)
	{
		Item item;
		UPC upc;
		Price price;
		TaxCategory taxCategory = new TaxCategory("Food","1/1/2020","0.07");
		
		
		store.addTaxCategory(taxCategory);
		
		//First item
		item = new Item(store, "0001","Plain M&M","Food");
		upc = new UPC("11111111111", item);
		store.addUPC(upc);
		price = new Price("3.29", "1/1/2020", item);
		
		//Second item
		item = new Item(store, "0002","Peanut M&M","Food");
		upc = new UPC("22222222222", item);
		store.addUPC(upc);
		price = new Price("3.39", "1/1/2020", item);
				
		//Third item
		item = new Item(store, "0003","Crunchy M&M","Food");
		upc = new UPC("33333333333", item);
		store.addUPC(upc);
		price = new Price("3.49", "1/1/2020", item);
		
		System.out.println("****************");
		System.out.println("Store");
		System.out.println("****************");
		System.out.println(store.toString());
		
		System.out.println("****************");
		System.out.println("Tax Categories");
		System.out.println("****************");
		for(TaxCategory taxCategoryItorator : store.getTaxCategories().values())
		{
			System.out.println(taxCategoryItorator.toString());
		}
		
		System.out.println("****************");
		System.out.println("Items");
		System.out.println("****************");
		for(Item itemItorator : store.getItems().values())
		{
			System.out.println(itemItorator.toString());
		}
	}
	
	public static void storeTest2(Store store)
	{
		Cashier cashier;
		Person person;
		
		//First cashier
		cashier = new Cashier("1","P@$$W0rd1");
		store.addCashier(cashier);
		person = new Person("Bob", "1111 Random St.","Edmond","Oklahoma","73013","405-111-1111","111-11-1111",cashier);
				
		//Second cashier
		cashier = new Cashier("2","P@$$W0rd2");
		store.addCashier(cashier);
		person = new Person("Sue", "2222 Random St.","Edmond","Oklahoma","73013","405-222-2222","222-22-2222",cashier);
		
		//Third cashier
		cashier = new Cashier("3","P@$$W0rd3");
		store.addCashier(cashier);
		person = new Person("San", "3333 Random St.","Edmond","Oklahoma","73013","405-333-3333","333-33-3333",cashier);
		
		System.out.println("****************");
		System.out.println("Cashiers");
		System.out.println("****************");
		for(Cashier i : store.getCashiers().values())
		{
			System.out.println(i.toString());
		}
	}
	
	public static void storeTest3(Store store)
	{
		Register register;
		CashDrawer cashDrawer;
		
		//First register
		cashDrawer = new CashDrawer();
		register = new Register(cashDrawer, "1");
		store.addRegister(register);
		
		//Second register
		cashDrawer = new CashDrawer();
		register = new Register(cashDrawer, "2");
		store.addRegister(register);
		
		//Third register
		cashDrawer = new CashDrawer();
		register = new Register(cashDrawer, "3");
		store.addRegister(register);
		
		System.out.println("****************");
		System.out.println("Registers");
		System.out.println("****************");
		
		for(Register i : store.getRegisters().values())
		{
			System.out.println(i.toString());
		}
	}
	
	public static void storeTest4(Store store)
	{
		Session session;
		Sale sale;
		SaleLineItem saleLineItem;
		
		session = new Session(store.getCashiers().get("1"),store.getRegisters().get("1"));
		sale = new Sale();
		
		//First sale line item
		saleLineItem = new SaleLineItem(sale, store.getItems().get("0001"), "2");
		sale.addSaleLineItem(saleLineItem);
		
		//Second sale line item
		saleLineItem = new SaleLineItem(sale, store.getItems().get("0002"), "2");
		sale.addSaleLineItem(saleLineItem);
		
		session.addSale(sale);
		
		System.out.println("****************");
		System.out.println("Session");
		System.out.println("****************");
		System.out.println(session.toString());
		
	}
	
	public static void storePrint(Store store)
	{
		System.out.println(store);
		
		System.out.println("****************");
		System.out.println("Tax Categories");
		System.out.println("****************");
		for(TaxCategory taxCategory : store.getTaxCategories().values())
		{
			System.out.println(taxCategory);
		}
		
		System.out.println("****************");
		System.out.println("Cashiers");
		System.out.println("****************");
		for(Cashier cashier : store.getCashiers().values())
		{
			System.out.println(cashier.toString());
		}
		
		System.out.println("****************");
		System.out.println("Registers");
		System.out.println("****************");
		
		for(Register register : store.getRegisters().values())
		{
			System.out.println(register);
		}
		
		System.out.println("****************");
		System.out.println("Items");
		System.out.println("****************");
		for(Item item : store.getItems().values())
		{
			System.out.println(item);
		}
		
		System.out.println("****************");
		System.out.println("Sessions");
		System.out.println("****************");
		for(Session session : store.getSessions())
		{
			System.out.println(session);
		}
	}
}