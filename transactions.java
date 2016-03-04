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
	
	public static void readInPrices(){
		
		
	}
	
	public static void readShipment(){
	/*	switch(line){
		case 1: line[1].equals("New");
			shipTo()
		case 2: line[1].equals("Miami")
		
		case 3: line[1].equals("Los")
		case 4: line[1].equals("Houston")
		case 5: line[1].equals("Chicago")
		
		}*/
		
	}
	
	public static void shipTO(warehouse location, int x, int y, int z){
	//	location.setI
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
					  priceX = Float.parseFloat(arr[3].substring(1));
					  priceY = Float.parseFloat(arr[7].substring(1));
					  priceZ = Float.parseFloat(arr[11].substring(1));
					  System.out.println(priceX);
					  
					  initializeWarehouses(reader,NewYork, Miami, LosAngeles, Houston, Chicago);
					  System.out.println(NewYork.getIndivCity().getItemStock()[1]);
					  System.out.println(Chicago.getIndivCity().getItemStock()[1]);
					  System.out.println(Houston.getIndivCity().getItemStock()[1]);
					  
					  System.out.println(Miami.toString());
					  System.out.println(LosAngeles.toString());
					  System.out.println(Houston.toString());
					  System.out.println(Chicago.toString());
					  

			}
			catch(IOException e){
				   System.out.println("Error, could not open the file!");
			   }
			
			System.out.println("Hello there");
			
		}
}
