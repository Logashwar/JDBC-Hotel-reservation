package busresevation;

public class Bus {
	
	  private int busno;
	  private boolean ac;
	  private  int capacity;
	 
	 
	public Bus(int busno, boolean ac,int capacity){
		
		this.busno = busno;
		this.ac = ac;
		this.capacity = capacity;
	}
	
	public int getBusno() {
		return busno;
	}
	public void setBusno(int busno) {
		this.busno = busno;
	}
	public boolean isAc() {
		return ac;
	}
	public void setAc(boolean ac) {
		this.ac = ac;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	 
	 
	public void displayinfo() {
		System.out.println("Bus no: " +busno + "\tAc:" + ac + "\tcapacity:" + capacity);
	}
		
}


