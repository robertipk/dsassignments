package hwtwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.lang.String;

public class transactions {
	private static float priceX;
	private static float priceY;
	private static float priceZ;
	

	public static void shipItems(String location, int x, int y, int z, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		switch(location){
		case "NewYork":
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
	
	public static void borrow(String type, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		
		warehouse city;
		int num;
		if (type.equals("X"))
			num = 0;
		else if (type.equals("Y"))
			num = 1;
		else  //if type equals Z
			num = 2;
		
		int ny = NewYork.getIndivCity().getItemStock()[num];
		int chicago = Chicago.getIndivCity().getItemStock()[num];
		int miami = Miami.getIndivCity().getItemStock()[num];
		int losangeles = LosAngeles.getIndivCity().getItemStock()[num];
		int houston = Houston.getIndivCity().getItemStock()[num];
		System.out.println("fgegedg" + ny + " " + chicago+miami+losangeles+houston);
		
		//two passes of bubble sort to find the two warehouses with the highest stocks
		record [] arr = {NewYork,Miami,LosAngeles,Houston,Chicago};
		

	}
	
	public static int [] findTwoHighest(int index, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		record [] allStocks = {NewYork, Miami, LosAngeles, Houston, Chicago};
		
		int high1 = Integer.MIN_VALUE;
		record highest = new record ();
		record secondhighest = new record();
		int high2 = Integer.MIN_VALUE;
		for (record city : allStocks) {
		      if (city.getIndivCity().getItemStock()[index] > high1) {
		        high2 = high1;
		        secondhighest = highest;
		        high1 = city.getIndivCity().getItemStock()[index];
		        highest = city;
		      } else if (city.getIndivCity().getItemStock()[index] > high2) {
		        high2 = city.getIndivCity().getItemStock()[index];
		        secondhighest = city;
		      }
		    }
		int [] array = {high1,high2};
		System.out.println("high1 is " + high1 + " high2 is " + high2);
		return array;
	}
	
	public static void readOrder(String city, int x, int y, int z){
		
	}
	
	public static void printOrder(){
		
	}
	
	private static void initializeWarehouses(BufferedReader reader,record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		try{
			record [] cities = {NewYork,Miami,LosAngeles,Houston,Chicago};
			String thisLine;
			String cityname;
			String [] numberArr;
			String numbers;
			
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
	
	public static void readInFile(BufferedReader reader){
		try{
			FileReader file = new FileReader("C:\\Users\\admin\\Desktop\\data.txt");
			reader = new BufferedReader(file);
		}
		catch(IOException e){
			   System.out.println("Error, could not open the file!");
		   }
	}
	public static void main(String [] args)
		{
			record NewYork = new record();
			record Miami = new record();
			record LosAngeles = new record();
			record Houston = new record();
			record Chicago = new record();
			//Miami, LA, Houston, Chicago;
			String thisLine;
			String [] arr;
			BufferedReader reader;
			try{
				FileReader file = new FileReader("C:\\Users\\admin\\Desktop\\data.txt");
				reader = new BufferedReader(file);
	
					  thisLine = reader.readLine();
					  System.out.println(thisLine);
					  arr = thisLine.split(" ");
					  //Find the prices of the items
					  priceX = Float.parseFloat(arr[3].substring(1));
					  priceY = Float.parseFloat(arr[7].substring(1));
					  priceZ = Float.parseFloat(arr[11].substring(1));
					  System.out.println(priceX);
					  //Read in the initial stocks of the items in the warehouses
					  initializeWarehouses(reader,NewYork, Miami, LosAngeles, Houston, Chicago);
					  int [] twohighest = findTwoHighest(2,NewYork, Miami, LosAngeles, Houston, Chicago);
					  borrow("Z",NewYork, Miami, LosAngeles, Houston, Chicago);
					  String cityname;
					  String typeOfTransaction;
					  String numbers;
					  String [] numberArr;
					  while ((thisLine = reader.readLine()) != null) {
						 System.out.println(thisLine);
						 arr = thisLine.split(" ");
						 typeOfTransaction = arr[0];
						//find name of city
						cityname = thisLine.substring(1).replaceAll("[^A-Za-z]+", "");
						//find the three numbers
						numbers = thisLine.replaceAll("[^-?0-9]+", " ");
						numberArr = numbers.split(" ");
						 if (typeOfTransaction.equals("S")){
							  //call shipment function
							 System.out.println("This was a shipment");
							  shipItems(cityname, Integer.parseInt(numberArr[1]),Integer.parseInt(numberArr[2]),Integer.parseInt(numberArr[3]), NewYork, Miami, LosAngeles, Houston, Chicago);
						  }
						 else if (typeOfTransaction.equals("O")){
							  //call order function  
							 System.out.println("This was an order");
						  }
						 
					  }
			}
			catch(IOException e){
				   System.out.println("Error, could not open the file!");
			   }
			
			System.out.println("Final numbers for: ");
			
			NewYork.printStock();
			Miami.printStock();
			LosAngeles.printStock();
			Chicago.printStock();
			Houston.printStock();
			
		}
}
