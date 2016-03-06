package hwtwo;

public class warehouse {
	//each instance of warehouse contains an array of three ints
	private int [] itemStock = {0,0,0};
	private float priceX;
	private float priceY;
	private float priceZ;
	
	public float getPriceX() {
		return priceX;
	}

	public void setPriceX(float priceOfX) {
		this.priceX = priceOfX;
	}

	public float getPriceY() {
		return priceY;
	}

	public void setPriceY(float priceOfY) {
		this.priceY = priceOfY;
	}

	public float getPriceZ() {
		return priceZ;
	}

	public void setPriceZ(float priceOfZ) {
		this.priceZ = priceOfZ;
	}

	public warehouse(int x, int y, int z) {
		this.itemStock[0] = x;
		this.itemStock[1] = y;
		this.itemStock[2] = z;
	}
	
	public int[] getItemStock() {
		return this.itemStock;
	}
	
	public int getStockX(){
		return this.itemStock[0];
	}
	
	public int getStockY(){
		return this.itemStock[1];
	}
	
	public int getStockZ(){
		return this.itemStock[2];
	}
	
	public void setItemStock(int[] stockArray) {
		this.itemStock = stockArray;
	}

	public void increaseX(int shipment){
		this.itemStock[0] += shipment;
	}
	
	public void increaseY(int shipment){
		this.itemStock[1] += shipment;
	}
	
	public void increaseZ(int shipment){
		this.itemStock[2] += shipment;
	}
	
	public void decreaseX(int shipment){
		this.itemStock[0] -= shipment;
	}
	
	public void decreaseY(int shipment){
		this.itemStock[1] -= shipment;
	}
	
	public void decreaseZ(int shipment){
		this.itemStock[2] -= shipment;
	}
	
	
}
