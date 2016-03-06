package hwtwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.lang.String;

public class transactions {
	private static float priceX, priceY, priceZ;
	
	public static void supplyWarehouses(String location, int x, int y, int z, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		switch(location){
		case "NewYork":
			NewYork.initializeStock(x,y,z);
			break;
		case "Miami":
			Miami.initializeStock(x,y,z);
			break;
		case "LosAngeles":
			LosAngeles.initializeStock(x,y,z);
			break;
		case "Houston":
			Houston.initializeStock(x,y,z);
			break;
		case "Chicago":
			Chicago.initializeStock(x,y,z);
			break;	
		}
	}
	
	public static void borrow(record original, int amountNeeded, String type, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		//this method is run only if the warehouse is not able to fill the order and must borrow from another warehouse
		
		record [] twoHighest = findTwoHighestWarehouses(type, NewYork, Miami, LosAngeles, Houston, Chicago);
		boolean containsEnough;
		record donorCity;
		if (original==twoHighest[0]){
			containsEnough = twoHighest[0].getStockOf(type)>=amountNeeded;
			donorCity = twoHighest[0];
		}
		else{
			containsEnough = twoHighest[0].getStockOf(type)>=amountNeeded;
			donorCity = twoHighest[1];
		}
		original.takeFrom(type, donorCity, amountNeeded);	
	}
	
	//returns the two warehouses with the highest amount of the specified item
	public static record [] findTwoHighestWarehouses(String type, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		record [] allStocks = {NewYork, Miami, LosAngeles, Houston, Chicago};	
		int high1 = Integer.MIN_VALUE;
		record highest = new record ();
		record secondhighest = new record();
		int high2 = Integer.MIN_VALUE;
		int stock;
		for (record city : allStocks) {
			  stock = city.getStockOf(type);
		      if (stock > high1) {
		        high2 = high1;
		        secondhighest = highest;
		        high1 = stock;
		        highest = city;
		      } else if (stock > high2) {
		        high2 = stock;
		        secondhighest = city;
		      }
		    }
		int [] array = {high1,high2};
		System.out.println("high1 is " + high1 + " high2 is " + high2);
		record [] arr = {highest, secondhighest};
		return arr;
	}
	
	public static void readOrder(String city, int x, int y, int z){
		
	}
	
	public static void printOrder(){
		
	}
	
	private static void initializeWarehouses(BufferedReader reader,record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		try{
			record [] cities = {NewYork,Miami,LosAngeles,Houston,Chicago};
			String thisLine, numbers, cityname;
			String [] numberArr;
			
			for(int i=0;i<5;i++)
			{	
				thisLine = reader.readLine();
				//find name of city
				cityname = thisLine.substring(1).replaceAll("[^A-Za-z]+", "");
				//find the three numbers
				numbers = thisLine.replaceAll("[^-?0-9]+", " ");
				numberArr = numbers.split(" ");
				int [] arrayStock = {Integer.parseInt(numberArr[1]),Integer.parseInt(numberArr[2]),Integer.parseInt(numberArr[3])};
				//create warehouse
				cities[i].setCityName(cityname);				
				cities[i].getIndivCity().setItemStock(arrayStock);	
				//System.out.print(cityname);
			}
		}
		catch(IOException e){
			   System.out.println("Error in reading string!");
		   }
	}
	
	public static int searchWarehouses(){
		return 0;
		//return the index of the warehouse
	}
	
	public static void takeFromWarehouse(warehouse giver, warehouse receiver){
		//charge ten percent tax		
	}
	
	public static String findCityName(String text){
		return text.substring(1).replaceAll("[^A-Za-z]+", "");
	}
	
	public static String [] findWarehouseStocks(String text){
		String numbers = text.replaceAll("[^-?0-9]+", " ");
		String [] numberArr = numbers.split(" ");
		return numberArr;
	}
	
	public static void readInFile(BufferedReader reader){
		try{
			FileReader file = new FileReader("C:\\Users\\admin\\Desktop\\data.txt");
			reader = new BufferedReader(file);
		}
		catch(IOException e){
			   System.out.println("Error, could not open the file!");
		   }
	}
	
	public static void performTransaction(String typeOfTransaction, String city, String []inventoryCount, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		 if (typeOfTransaction.equals("S")){
			  //call shipment function
			 System.out.println("This was a shipment");
			  supplyWarehouses(city, Integer.parseInt(inventoryCount[1]),Integer.parseInt(inventoryCount[2]),Integer.parseInt(inventoryCount[3]), NewYork, Miami, LosAngeles, Houston, Chicago);
		  }
		 else if (typeOfTransaction.equals("O")){
			  //call order function  
			 System.out.println("This was an order");
		  }	
	}
	
	public static void setPrices(String text, float priceX, float priceY, float priceZ){
		String [] arr = text.split(" ");
		//Find the prices of the items
		priceX = Float.parseFloat(arr[3].substring(1));
		priceY = Float.parseFloat(arr[7].substring(1));
		priceZ = Float.parseFloat(arr[11].substring(1));
	}
	
	public static void printInventories(record NewYork, record Miami, record LosAngeles, record Chicago, record Houston){
		NewYork.printInventory();
		Miami.printInventory();
		LosAngeles.printInventory();
		Chicago.printInventory();
		Houston.printInventory();	
	}
	
	public static void main(String [] args)
		{
			record NewYork = new record();
			record Miami = new record();
			record LosAngeles = new record();
			record Houston = new record();
			record Chicago = new record();
			String thisLine;
			
			BufferedReader reader;
			try{
				FileReader file = new FileReader("C:\\Users\\admin\\Desktop\\data.txt");
				reader = new BufferedReader(file);
	
					  thisLine = reader.readLine();		 
					  setPrices(thisLine, priceX, priceY, priceZ);
					  //Read in the initial stocks of the items in the warehouses
					  initializeWarehouses(reader,NewYork, Miami, LosAngeles, Houston, Chicago);
					  //int [] twohighest = findTwoHighest(2,NewYork, Miami, LosAngeles, Houston, Chicago);
					  //borrow("Z",NewYork, Miami, LosAngeles, Houston, Chicago);
					  String city, typeOfTransaction;
					  String [] inventoryCount;
					  while ((thisLine = reader.readLine()) != null) {
						 System.out.println(thisLine);
						 typeOfTransaction = thisLine.split(" ")[0];
						 city = findCityName(thisLine);	
						 inventoryCount = findWarehouseStocks(thisLine);
						 performTransaction(typeOfTransaction, city, inventoryCount,NewYork, Miami, LosAngeles, Houston, Chicago);
											 
					  }
			}
			catch(IOException e){
				   System.out.println("Error, could not open the file!");
			   }			
			System.out.println("Final numbers for: ");
			printInventories(NewYork, Miami, LosAngeles, Houston, Chicago);

			
		}
}
