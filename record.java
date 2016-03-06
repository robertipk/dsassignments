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
	
	public void takeFrom(String type, record donor, int amountNeeded){
		if (type.equals("X")){
			this.indivCity.increaseX(amountNeeded);
			donor.getIndivCity().decreaseX(amountNeeded);
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
}
