package hwtwo;

public class record {
	
	warehouse indivCity;
	String cityName;
	
	public record() {
		this.indivCity = new warehouse(0,0,0);
		this.cityName = "Some city";
	}
	
	public record(String cityName) {
		//System.out.println("Record constructor called");
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
	public String toString() {
		return "record [indivCity=" + indivCity + ", cityName=" + cityName + "]";
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
		System.out.println("Shipment being sent to " + this.cityName);
		this.indivCity.increaseX(x);
		this.indivCity.increaseY(y);
		this.indivCity.increaseZ(z);
	}
	
	public void takeFrom(String type, record donor, int amountNeeded, int insuffAmt){
		if (type.equals("X")){
			this.indivCity.increaseX(amountNeeded);
			donor.getIndivCity().decreaseX(amountNeeded);
			float totalPrice = makeTwoCitySale(amountNeeded, insuffAmt, this.indivCity.getPriceX());
		}
		else if (type.equals("Y")){
			this.indivCity.increaseY(amountNeeded);
			donor.getIndivCity().decreaseY(amountNeeded);
		}
		else if (type.equals("Z")){
			this.indivCity.increaseZ(amountNeeded);
			donor.getIndivCity().decreaseZ(amountNeeded);		
		}
	}
	
	public static float makeTwoCitySale(int amtShipped, int amtOriginal, float price){
		float orig = amtOriginal*price;
		float withTax = amtShipped*(1.1)*(price);
		return orig + withTax;
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
		System.out.println("Simple sale from " + city + ": Item" + xyz + " for " + price + " times " + amount + " for " + price*amount);
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
		System.out.println("high1 is " + high1 + " high2 is " + high2);
		record [] arr = {highest, secondhighest};
		return arr;
	}
	
	public void borrow(String type, int amountToShip, int insuffAmt, record NewYork, record Miami, record LosAngeles, record Houston, record Chicago){
		record [] twoHighestWarehouses = findTwoHighestWarehouses(type, NewYork, Miami, LosAngeles, Houston, Chicago);
		boolean donorContainsEnough;
		record donorCity;
		if (this==twoHighestWarehouses[0]){
			donorContainsEnough = twoHighestWarehouses[0].getStockOf(type)>=amountToShip;
			donorCity = twoHighestWarehouses[0];
		}
		else{
			donorContainsEnough = twoHighestWarehouses[0].getStockOf(type)>=amouamountToShipnt;
			donorCity = twoHighestWarehouses[1];
		}
		if (donorContainsEnough)
			this.takeFrom(type, donorCity, amountToShip, insuffAmt);
		else
			System.out.println("The order was unfilled. Not enough inventory in second city.");
		
	}
}
