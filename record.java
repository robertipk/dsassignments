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
	
	public void initializeStock(int x, int y, int z){
		System.out.println("Shipment being sent to " + this.cityName);
		this.indivCity.increaseX(x);
		this.indivCity.increaseY(y);
		this.indivCity.increaseZ(z);
	}
	
	public void takeFrom(String type, record donor, int amountNeeded){
		if (type == "X"){
			this.indivCity.increaseX(amountNeeded);
			donor.getIndivCity().decreaseX(amountNeeded);
		}
		else if (type == "Y"){
			this.indivCity.increaseY(amountNeeded);
			donor.getIndivCity().decreaseY(amountNeeded);
		}
		else { // if type == Z
			this.indivCity.increaseZ(amountNeeded);
			donor.getIndivCity().decreaseZ(amountNeeded);		
		}
	}
	
	public void makeSale(int amount, int type, int price){
		if (type == 0)
			this.getIndivCity().decreaseX(amount);
		else if (type == 1)
			this.getIndivCity().decreaseX(amount);
		else if (type == 2)
			this.getIndivCity().decreaseX(amount);
		System.out.println(this.cityName + " sold " + amount + " of product " + type + " for $" + amount*price);	
	}
	
	public void printStock(){
		int [] stock = this.indivCity.getItemStock();
		System.out.println(this.cityName + ": " + stock[0] + " " + stock[1] + " " + stock[2]);
	}
}
