package it.volta.ts.starhausnicolas.testmultithreading.bean;

public class Horse extends Thread {

	private String horseName;
	private int    handicap;
	private int    meters;

	
	public Horse(String horseName, int handicap) {
		this.horseName = horseName;
		this.handicap  = handicap;
		this.meters    = 0;
	}
	
	public String getHorseName() {
		return horseName;
	}
	
	public int getMeters() {
		return meters;
	}

	@Override
	public void run() {
		while(true) {
			meters++;
			
			try {
				Thread.sleep((long)(handicap * 10 * Math.random()));
			} catch (InterruptedException e) {
				System.out.println("Sleep ends for " + e.getMessage());
			} catch (IllegalArgumentException iae) {
				System.out.println("Sleep ends for " + iae.getMessage());
			}
		}
	}

	@Override
	public String toString() {
		String str;
		
		String mts = "";
		for(int idx = 0; idx < meters; idx++) 
			mts += "*";
		
		str = "> " + horseName + " [" + mts + " meters]";
		
		return str;
	}
	
}
