package hotel_reserve;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Hotel_reservation {
public static void main(String[] args) {
		
		final String dburl ="jdbc:mysql://localhost:3306/lokesh_test";
	    final String user = "root";
	    final String password = "12345";
	    
	    Scanner sc = new Scanner(System.in);
	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection(dburl, user, password);
		   
		    
		    //program  starts
		    
		    while(true) {
		    	
		    	System.out.println("\n\n##------------------------------------------------------------------------------##");
		    	System.out.println("\tWelcome to the Hotel Reservation system by logash\t");
		    	System.out.println("\t\tSelect anyone of the options below\t");
		    	System.out.println("1. Reserve a room");
		    	System.out.println("2. View reservation");
		    	System.out.println("3. Get room number");
		    	System.out.println("4. Update reservation");	
		    	System.out.println("5. Delete reservation");
		    	System.out.println("6. Exit the application");
		    	
		    	
		    	int choice = sc.nextInt();
		    	
		    	switch(choice) {
		    	case 1: ReserveRoom(con,sc);
		    	break;
		    	case 2: viewReservation(con);
		    	break;
		    	case 3: getRoom(con,sc);
		    	break;
		    	case 4: updateReservation(con, sc); 
		    	break;
		    	case 5: deleteReservation(con,sc);
		    	break;
		    	case 6: exit();
		    	sc.close();
		    	return;
		    	default:System.out.println("please enter a valid option ");
		    	}
		    }
		    
			
		} catch (ClassNotFoundException |SQLException | InterruptedException  e) {
			e.printStackTrace();
		}

}
	
	public static void ReserveRoom(  Connection con, Scanner sc){
	  
		System.out.println("Enter the guest name:");
		String gname = sc.next();
		System.out.println("Enter the guest number:");
		long gnum = sc.nextLong();
		System.out.println("Enter the room number:");
		int room = sc.nextInt();
		
		String query = "INSERT INTO reservations(Guest_Name, Guest_contact, room_number) VALUE"
		+"('"+gname+"',"+gnum+","+room+")";
		
		
		try(Statement  stmt =  con.createStatement()){
						
			int count = stmt.executeUpdate(query);
			
			if(count > 0) {
				System.out.println("Room is reserved for you!!! ");
				System.out.println();
			}else {
				System.out.println("Not updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void viewReservation(Connection con){
		String query = "SELECT r_id,Guest_Name, Guest_contact, room_number, r_stamp FROM reservations";
			
		try(Statement  stmt =  con.createStatement()){
		ResultSet rs = stmt.executeQuery(query);
		
		System.out.println("*---------------------------------------------------------------------------*");
		System.out.println("|r_id | Guest_Name | Guest_contact | room_number | r_stamp ");
		System.out.println("*---------------------------------------------------------------------------*");
		
		while(rs.next()) {
		 	int rid = rs.getInt("r_id");
			String gname = rs.getString("Guest_Name");
			String num = rs.getString("Guest_contact");
			String roomnunm = rs.getString("room_number");
			String date = rs.getTimestamp("r_stamp").toString();
			
			System.out.printf("| %-3d | %-10s | %-13s | %-12s | %-10s | \n", rid,gname,num,roomnunm,date);
		}
		System.out.println("*---------------------------------------------------------------------------*");
		
		}catch(SQLException e){
			e.printStackTrace();		
			}
	}
	
	public static void getRoom(Connection con, Scanner sc) {
		
		System.out.println("Enter the reservation ID ");
		int rid = sc.nextInt();
		System.out.println("Enter the guest name ");
		String gname = sc.next();
		
		String Query = "SELECT room_number FROM reservations WHERE r_id= "+rid+" And Guest_Name = '"+gname+"'"; 
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Query);
			
			if(rs.next()) {
				int room = rs.getInt("room_number");
				System.out.println("The room number for given Reservation ID :" +rid+ "is  - Room number : "+room);
			}else {
				
				System.out.println("There is no resrvation for the given Rid : "+rid);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}	
	}
	
	public static void updateReservation(Connection con ,Scanner sc) {
		System.out.println("Enter the reservation ID which needs to be updated");
		int rid = sc.nextInt();
		
		if(!isresrvationexist(con,rid)) {
			System.out.println("Reservation do not exists for the given reservation ID ");
			return;
		}
			System.out.println("enter the new guest name");
			String ngname = sc.next();
			System.out.println("enter the new contact number");
			String ncon = sc.next();
			System.out.println("enter the new room  number");
			String nroom = sc.next();
			
			try(Statement st = con.createStatement()) {
				String Query = "UPDATE reservations SET Guest_Name ='"+ngname+"',"+" Guest_contact = '"+ncon+"',"+" room_number = '"+nroom
				+"'"+"WHERE r_id = "+rid ;
				int count = st.executeUpdate(Query);
				if(count > 0) {
					System.out.println("room details  updated succesfully  ");
				}else {
					System.out.println("rooms updation failed");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 
	 public static void deleteReservation(Connection con , Scanner sc) {
		 System.out.println("Enter the Resrevation Id which needs to be deleted");
		 int rid = sc.nextInt();
		 
		 if(!isresrvationexist(con, rid)){
			 System.out.println("Reservation do not exist for the given Rservation ID");
			 return;
		 }
		 
		 String Query = "DELETE FROM reservations WHERE r_id ="+rid ;
		 try (Statement st = con.createStatement()){
			int count = st.executeUpdate(Query);
			
			if(count > 0) {
				System.out.println("Reservation deleted successfully");
			}else {
				System.out.println("Reservation not deleted");
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	 public static void exit() throws InterruptedException {
		 
		 System.out.print("Exiting the application");
		 int i=5;
		 while(i>0) {
			 System.out.print(". ");
			 Thread.sleep(3000);
			 i--;
		 }
		 System.out.println();
		 System.out.println("thank you for using hotel resrevation system");
		 
	 }

	private static boolean isresrvationexist(Connection con, int id) {
	
		String Query = "SELECT r_id FROM reservations WHERE r_id = "+id ;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Query);
			return rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return false;
	}

}
