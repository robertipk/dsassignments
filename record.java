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
	
	public void printStock(){
		int [] stock = this.indivCity.getItemStock();
		System.out.println(this.cityName + ": " + stock[0] + " " + stock[1] + " " + stock[2]);
	}
}
