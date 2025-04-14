package busresevation;
import java.util.*;

public class busdemo {

	
	public static void main(String[] args) {
		
		
		ArrayList<Bus>  buses = new ArrayList();
		
		buses.add(new Bus(301, true,50));
		buses.add(new Bus(302, false,30));
		buses.add(new Bus(303, true, 40));
 		
	
		int userinp = 1 ;
		Scanner sc = new Scanner(System.in);
		while(userinp == 1 ) {
				
			for(Bus b : buses) {
				b.displayinfo();
			}
			
			System.out.println("Enter 1 to book 2 to exit");
			userinp = sc.nextInt();
			if(userinp == 1) {
				System.out.println("Booking.......");
			}
		}
	}
	
	
}
