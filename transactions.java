package hwtwo;

import java.io.*;
import java.lang.String;

public class transactions {
	private static float priceX, priceY, priceZ;
	
	public static void supplyWarehouses(String city, int x, int y, int z, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		switch(city){
		case "NewYork":
			//System.out.println("#######################");
			NewYork.increaseStock(x,y,z);
			break;
		case "Miami":
			Miami.increaseStock(x,y,z);
			break;
		case "LosAngeles":
			LosAngeles.increaseStock(x,y,z);
			break;
		case "Houston":
			Houston.increaseStock(x,y,z);
			break;
		case "Chicago":
			Chicago.increaseStock(x,y,z);
			break;	
		}
	}
	
	public static void processOrder(String city, int amountX, int amountY, int amountZ, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		record vendorCity;
		if (city=="NewYork")
			vendorCity = NewYork;
		else if (city=="Miami")
			vendorCity = Miami;
		else if (city=="LosAngeles")
			vendorCity = LosAngeles;
		else if (city=="Houston")
			vendorCity = Houston;
		else
			vendorCity = Chicago;
	
		//try to order itemX from city
		int amtToShip = 0;
		if (vendorCity.getStockOf("X") >= amountX)
			//if the warehouse has enough stock, no need to ship from another city
			vendorCity.makeSimpleSale("X", amountX);
		else
			amtToShip = (amountX-vendorCity.getStockOf("X"));
			vendorCity.borrow("X", amtToShip, amountX, NewYork, Miami, LosAngeles, Houston, Chicago);
			//require shipment from second city
		
		if (vendorCity.getStockOf("Y") >= amountY){
			vendorCity.makeSimpleSale("Y", amountY);
		}
		
		if (vendorCity.getStockOf("Z") >= amountZ){
			vendorCity.makeSimpleSale("Z", amountZ);
		}
		
		
			

		
		//try to order itemY from city
		
		//try to order itemZ from city
		
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
	
	public static String findCityName(String text){
		return text.substring(1).replaceAll("[^A-Za-z]+", "");
	}
	
	public static String [] findWarehouseStocks(String text){
		String numbers = text.replaceAll("[^-?0-9]+", " ");
		String [] numberArr = numbers.split(" ");
		return numberArr;
	}
	
	public static void performTransaction(String typeOfTransaction, String city, String []inventoryCount, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		int amountX = Integer.parseInt(inventoryCount[1]);
		int amountY = Integer.parseInt(inventoryCount[2]);
		int amountZ = Integer.parseInt(inventoryCount[3]);
		if (typeOfTransaction.equals("S")){
			  //call shipment function
			 System.out.println("This was a shipment");
			  supplyWarehouses(city, amountX,amountY,amountZ, NewYork, Miami, LosAngeles, Houston, Chicago);
		  }
		 else if (typeOfTransaction.equals("O")){
			  //call order function  
			 processOrder(city,amountX,amountY,amountZ, NewYork, Miami, LosAngeles, Houston, Chicago);
		  }	
	}
	
	public static void setPrices(String text,record NewYork, record Miami, record LosAngeles, record Chicago, record Houston){
		String [] arr = text.split(" ");
		//parse the line of text and set prices
		float priceX = Float.parseFloat(arr[3].substring(1));
		float priceY = Float.parseFloat(arr[7].substring(1));
		float priceZ = Float.parseFloat(arr[11].substring(1));
		NewYork.setPrices(priceX, priceY, priceZ);
		Miami.setPrices(priceX, priceY, priceZ);
		LosAngeles.setPrices(priceX, priceY, priceZ);
		Chicago.setPrices(priceX, priceY, priceZ);
		Houston.setPrices(priceX, priceY, priceZ);		
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
							
			try{
				BufferedReader reader;
				FileReader file = new FileReader("C:\\Users\\admin\\Desktop\\data.txt");
				reader = new BufferedReader(file);
						
				      String thisLine,city, typeOfTransaction;
					  thisLine = reader.readLine();		 
					  setPrices(thisLine, NewYork, Miami, LosAngeles, Houston, Chicago);
					  initializeWarehouses(reader,NewYork, Miami, LosAngeles, Houston, Chicago);
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
