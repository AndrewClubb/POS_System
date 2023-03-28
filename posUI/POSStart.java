package posUI;

import java.time.LocalDate;

import posPD.*;
import posTest.*;

public class POSStart 
{
	public static Store myStore;

	public static void main(String[] args) 
	{
		/*
		myStore = new Store("1","Andrew's Store");
		StoreTest.storeTest1(myStore);
		StoreTest.storeTest2(myStore);
		StoreTest.storeTest3(myStore);
		StoreTest.storeTest4(myStore);
		*/
		
		/*
		myStore = new Store();
		System.out.println("Ready to open Store");
		myStore.openStore();		
		new StoreTest().storePrint(myStore);
		System.out.println("");
		System.out.println("");
		System.out.println("Store Open:"+myStore);
		*/
		
		
		myStore = new Store();
		myStore.openStore();
		POSFrame.open(myStore);
		
	}
}
