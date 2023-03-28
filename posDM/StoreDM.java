package posDM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import posPD.*;

public class StoreDM {

	public static void loadStore(Store store) 
	{
		String fileName ="StoreData_v2020.csv";
		String line = null;
		String dataType; 
		Session session = new Session();
    	Sale sale = new Sale();
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	String[] token = line.split(",");
	        	dataType = token[0];
	        	
	        	if(dataType.contentEquals("Store"))
	        	{
	        		store.setName(token[1]);
	        	}
	        	else if (dataType.contentEquals("TaxCategory"))
	        	{
	        		TaxCategory taxCategory = new TaxCategory(token[1],token[3],token[2]);
	        		store.addTaxCategory(taxCategory);
	        	}
	        	else if (dataType.contentEquals("Cashier"))
	        	{
	        		Person person = new Person(token[2], token[4], token[5], token[6], token[7], token[8], token[3]);
	        		Cashier cashier = new Cashier(token[1],token[9],person);
	        		store.addCashier(cashier);
	        	}
	        	else if (dataType.contentEquals("Item"))
	        	{
	        		Item item = new Item(store, token[1], token[3], token[4]);
	        		UPC upc = new UPC(token[2], item);
	        		
	        		if(token.length == 10)
	        			{
	        				PromoPrice promoPrice = new PromoPrice(token[7],token[8],token[9]);
	        				item.addPrice(promoPrice);
	        			}
	        		
        			Price price = new Price(token[5],token[6]);
        			price.setItem(item);
        			item.addPrice(price);
	        	}
	        	else if (dataType.contentEquals("Register"))
	        	{
	        		Register register = new Register(token[1]);
	        		store.addRegister(register);
	        	}
	        	else if (dataType.contentEquals("Session"))
	        	{
	        		session = new Session(store.findCashierForNumber(token[1]),store.findRegisterByNumber(token[2]));
	        		store.addSession(session);
	        	}
	        	else if (dataType.contentEquals("Sale"))
	        	{
	        		sale = new Sale(token[1]);
	        		session.addSale(sale);
	        	}
	        	else if (dataType.contentEquals("SaleLineItem"))
	        	{
	        		SaleLineItem saleLineItem = new SaleLineItem(sale,store.findItemForNumber(token[1]),token[2]);
	        		sale.addSaleLineItem(saleLineItem);
	        	}
	        	else if (dataType.contentEquals("Payment"))
	        	{
	        		if(token[1].contentEquals("Cash"))
	        		{
	        			Cash cash = new Cash(token[2],token[3]);
	        			sale.addPayment(cash);
	        		}
	        		else if (token[1].contentEquals("Credit"))
	        		{
	        			Credit credit = new Credit(token[2],token[3],token[4],token[5],token[6]);
	        			sale.addPayment(credit);
	        		}
	        		else if (token[1].contentEquals("Check"))
	        		{
	        			Check check = new Check(token[2],token[3],token[4],token[5],token[6]);
	        			sale.addPayment(check);
	        		}
	        	}
	        }
	        
	        // Always close files.
	        bufferedReader.close();            
	    }
	    catch(FileNotFoundException ex) 
	    {
	      System.out.println("Unable to open file '" +  fileName + "'");                
	    }
	    catch(IOException ex) 
	    {
	       System.out.println ("Error reading file '" + fileName + "'");   	
	    }
	}
	
}