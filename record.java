//Robert Ip March 6, 2016  Data Structures CISC 3130
package hwtwo;

import java.text.NumberFormat;

public class record {
	
	warehouse indivCity;
	String cityName;
	
	public record() {
		this.indivCity = new warehouse(0,0,0);
		this.cityName = "Some city";
	}
	
	public record(String cityName) {
		this.indivCity = new warehouse(0,0,0);
		this.cityName = cityName;
	}
	public warehouse getIndivCity() {
		return indivCity;
	}

	public int getStockOf(String xyz){
		if (xyz == "X")
			return this.indivCity.getStockX();
		else if (xyz == "Y")
			return this.indivCity.getStockY();
		else 
			return this.indivCity.getStockZ();	
	}


	public void setIndivCity(warehouse indivCity) {
		this.indivCity = indivCity;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public void increaseStock(int x, int y, int z){
		this.indivCity.increaseX(x);
		this.indivCity.increaseY(y);
		this.indivCity.increaseZ(z);
	}
	
	public void takeFrom(String type, record donor, int amountNeeded, int insuffAmt){
		if (type.equals("X")){
			this.indivCity.decreaseX(insuffAmt);
			donor.getIndivCity().decreaseX(amountNeeded);
			System.out.println(amountNeeded + " of itemX shipped from " + donor.cityName + " to " + this.cityName);
			makeTwoCitySale(amountNeeded, insuffAmt, this.indivCity.getPriceX());
		}
		else if (type.equals("Y")){
			this.indivCity.decreaseY(insuffAmt);
			donor.getIndivCity().decreaseY(amountNeeded);
			System.out.println(amountNeeded + " of itemY shipped from " + donor.cityName + " to " + this.cityName);
			makeTwoCitySale(amountNeeded, insuffAmt, this.indivCity.getPriceY());
		}
		else if (type.equals("Z")){
			this.indivCity.decreaseZ(insuffAmt);
			System.out.println(amountNeeded + " of itemZ shipped from " + donor.cityName + " to " + this.cityName);
			donor.getIndivCity().decreaseZ(amountNeeded);
			makeTwoCitySale(amountNeeded, insuffAmt, this.indivCity.getPriceZ());		
		}
	}
	
	public static void makeTwoCitySale(int amtShipped, int amtOriginal, float price){
		NumberFormat fmt1 = NumberFormat.getCurrencyInstance();
		System.out.println("Total Order: " + fmt1.format(amtOriginal*price) + " + " + fmt1.format(amtShipped*1.1*price) );
	}
	
	public void makeSimpleSale(String type, int amount){
		if (type.equals("X")){
			this.getIndivCity().decreaseX(amount);
			printSimpleSale(this.cityName, "X", this.getIndivCity().getPriceX(), amount);
		}
		else if (type.equals("Y")){
			this.getIndivCity().decreaseY(amount);
			printSimpleSale(this.cityName, "Y", this.getIndivCity().getPriceY(), amount);
		}
		else if (type.equals("Z")){
			this.getIndivCity().decreaseZ(amount);
			printSimpleSale(this.cityName, "Z", this.getIndivCity().getPriceZ(), amount);
		}	
	}
	
	public void printInventory(){
		int [] stock = this.indivCity.getItemStock();
		System.out.println(this.cityName + ": " + stock[0] + " " + stock[1] + " " + stock[2]);
	}
	
	public void printSimpleSale(String city, String xyz, float price, int amount){
		NumberFormat fmt1 = NumberFormat.getCurrencyInstance();
		System.out.println("Price of Order: " + fmt1.format(price*amount));
	}
	public void setPrices(float priceX, float priceY, float priceZ){
		this.indivCity.setPriceX(priceX);
		this.indivCity.setPriceY(priceY);
		this.indivCity.setPriceZ(priceZ);
	}
	
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
		record [] arr = {highest, secondhighest};
		return arr;
	}
	
	public void tryToTakeItems(String type, int amtToShip, int insuffAmt, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		//search remaining warehouses for the one which has the most of the desired item
		record [] twoHighestWarehouses = findTwoHighestWarehouses(type, NewYork, Miami, LosAngeles, Houston, Chicago);
		boolean donorContainsEnough;
		record donorCity;
		if (this==twoHighestWarehouses[0]){ //if the warehouse with the most of the desired item is the original warehouse
											//then take from the warehouse with the second highest amount of the item
			donorContainsEnough = twoHighestWarehouses[1].getStockOf(type)>=amtToShip;
			donorCity = twoHighestWarehouses[1];
		}
		else{
			donorContainsEnough = twoHighestWarehouses[0].getStockOf(type)>=amtToShip;
			donorCity = twoHighestWarehouses[0];
		}
		if (donorContainsEnough)
			this.takeFrom(type, donorCity, amtToShip, insuffAmt);
		else
			System.out.println("Order unfilled.");
		
	}
}
