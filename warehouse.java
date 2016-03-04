package hwtwo;

public class warehouse {
	//each instance of warehouse contains an array of three ints
	private int [] itemStock = {0,0,0};

	public warehouse(int x, int y, int z) {
		this.itemStock[0] = x;
		this.itemStock[1] = y;
		this.itemStock[2] = z;
	}
	
	public void increaseX(int shipment){
		this.itemStock[0] += shipment;
	}
	
	public int[] getItemStock() {
		return itemStock;
	}

	public void setItemStock(int[] stockArray) {
		this.itemStock = stockArray;
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
