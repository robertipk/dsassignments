package hwtwo;

public class record {
	
	warehouse indivCity;
	String cityName;
	
	public record() {
		this.indivCity = new warehouse(0,0,0);
		this.cityName = "Some city"; //rename cityName to warehouse
	}
	
	public record(String cityName) {
		System.out.println("Record constructor called");
		this.indivCity = new warehouse(0,0,0);
		this.cityName = cityName;
	}
	public warehouse getIndivCity() {
		return indivCity;
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
	
	

}
