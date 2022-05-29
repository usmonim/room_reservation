package assignment1RoomReservation;

import java.sql.*;
import java.util.Scanner;

public class RoomReservationSystem {
	static final String DB_URL = "jdbc:mysql://localhost/room reservation";
	static final String USER = "root";
	static final String PASS = "ã311200å";
//Menu options Strings 	
	public static String menu = "Menu:";
	public static String one = "    1. Create Room";
	public static String two = "    2. Remove Room";
	public static String three = "    3. List all Rooms in a Building With Their Vacancy Information";
	public static String four = "    4. Get Currently Occupied Rooms";
	public static String five = "    5. Search For a Room";
	public static String six = "    6. Get Reservation History of a Room";
	public static String seven = "    7. Get Reservation History of a User";
	public static String eight = "    8. Reserve a Room";
	public static String nine = "    9. Leave a Room";
	public static String zero = "    0 - Show Menu";
	public static Scanner input = new Scanner(System.in);
	public static int choose;
//Function Menu. Prints all menu staff 	
	public static void menu() {
		System.out.println(menu);
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);
		System.out.println(four);
		System.out.println(five);
		System.out.println(six);
		System.out.println(seven);
		System.out.println(eight);
		System.out.println(nine);
		System.out.println(zero);
	}
//Select function where we select option from Menu	
	public static void select() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt  = conn.createStatement();
		int room_id;
		String room_name = "";
		int building_id;
		int capacity;
		String property_string = "";
		int instructor_id;
		String instructor_name;
		String reservation_status;
		String not_occupied = "Not occupied";
		System.out.println("select >: ");
		choose = input.nextInt();
		try {

// Menu Option 1 we create room 
			if(choose == 1) {
				System.out.println("1. Create Room");
				input.nextLine();
				System.out.println("Room Name >: ");
				room_name = input.nextLine();
				
				System.out.println("Building Location >: ");
				building_id = input.nextInt();
				
				System.out.println("Capacity >: ");
				capacity = input.nextInt();
				
				input.nextLine();
				
				System.out.println("Property of  Room >:");
				property_string  = input.nextLine();
				
				PreparedStatement preparedStatement = conn.prepareStatement("insert into room (room_name, building_id, capacity, property_string, reservation_status) values(?, ?, ?, ?, ?)");
				preparedStatement.setString(1, room_name);
				preparedStatement.setInt(2, building_id);
				preparedStatement.setInt(3, capacity);
				preparedStatement.setString(4, property_string);
				preparedStatement.setString(5, not_occupied); //When the room in created i set its reservation_status: Not Occupied. Status changes to "Occupied" when we reserve a room.
				preparedStatement.executeUpdate();
				System.out.println("Room " + room_name + " has been created succesfully.");			
			}
			
	// Menu Option 2 //When i remove room do i need to remove it from reservation history too or no
			else if (choose == 2) {
				System.out.println("2. Remove Room");
				System.out.println("Enter room ID>: ");
				room_id = input.nextInt();
				String get_reserveIfexist = "select* from reserves where room_id = " + room_id + ";";
				ResultSet rs =  stmt.executeQuery(get_reserveIfexist);
				if(!rs.isBeforeFirst()) {
	//				System.out.println("reserve do not exist");
					PreparedStatement preparedStatement = conn.prepareStatement("delete from room where id = ?");
					preparedStatement.setInt(1, room_id);
					preparedStatement.executeUpdate();
					System.out.println("Room has been removed.");
				}
				else {
//					System.out.println("reserve exist");
					PreparedStatement preparedStatement = conn.prepareStatement("delete from reserves where room_id = ?;");
					preparedStatement.setInt(1, room_id);
					preparedStatement.executeUpdate();
					
					PreparedStatement preparedStatement2 = conn.prepareStatement("delete from room where id = ?");
					preparedStatement2.setInt(1, room_id);
					preparedStatement2.executeUpdate();
					System.out.println("Room has been removed.");
					
				}
				
			}
			
	// Menu Option 3	
			else if(choose == 3) {
				System.out.println("3. List all Rooms in a Building With Their Vacancy Information");
				System.out.println("Building ID >: ");
				building_id = input.nextInt();
				String query = "select room.id, room_name, building_name, capacity, reservation_status from room, building where building.id = " + building_id + " and room.building_id = " + building_id + ";";
				
				ResultSet rs =  stmt.executeQuery(query);
				 while (rs.next()) {
					 room_id  = rs.getInt("id");
			         room_name = rs.getString("room_name");
			         String building_name = rs.getString("building_name");
			         capacity = rs.getInt("capacity");
			         reservation_status = rs.getString("reservation_status");
	
			         System.out.print("Room ID: " + room_id);
			         System.out.print("|  Room Name: " + room_name);
			         System.out.print("|  Building Name: " + building_name);
			         System.out.print("|  Capacity: " + capacity);
			         System.out.println("|  Reservation Status: " + reservation_status);
			      }    
			}
			
	// Menu Option 4
			else if(choose == 4) {
				System.out.println("4. Get Currently Occupied Rooms");
				String get_allstatuses = "select room.id, room_name, capacity from room where reservation_status = 'Occupied'";
				ResultSet rs =  stmt.executeQuery(get_allstatuses);
				if(!rs.isBeforeFirst()) {
					System.out.println("Currently occupied rooms not found.");
				}
				else {
					System.out.println("Currently occupied rooms: ");
					while(rs.next()) {
						 room_id = rs.getInt("id");
						 room_name = rs.getString("room_name"); 
						 capacity = rs.getInt("capacity");
						
						 System.out.print("Room ID: " + room_id); 
						 System.out.print("|  Room Name: " +room_name); 
						 System.out.println("|  Capacity: " + capacity);
					}
				}
			}
			
	// Menu Option 5 i need to show all available Not Occupied rohgb just show all rooms satisfying given user conditions.  
			else if(choose == 5) {
				System.out.println("5. Search For a Room");
				String template = "";
				System.out.println("Enter a template>: ");
				template = input.next();
				String get_satusfyingRooms = "select id, room_name, building_id, capacity, reservation_status, property_string from room where property_string like '%" + template + "%';";
				ResultSet rs =  stmt.executeQuery(get_satusfyingRooms);
				if(!rs.isBeforeFirst()) {
					System.out.println("Room with " + "<" + template + ">" + " was not found.");
				}
				else {
					while (rs.next()) {
						 room_id  = rs.getInt("id");
				         room_name = rs.getString("room_name");
				         building_id = rs.getInt("building_id");
				         capacity = rs.getInt("capacity");
				         reservation_status = rs.getString("reservation_status");
				         property_string = rs.getString("property_string");
	
				         System.out.print("Room ID: " + room_id);
				         System.out.print("|  Room Name: " + room_name);
				         System.out.print("|  Building ID: " + building_id);
				         System.out.print("|  Capacity: " + capacity);
				         System.out.print("|  Reservation Status: " + reservation_status);
				         System.out.println("|  Propery: " + property_string);
				      }
				}
			}
			
	// Menu Option 6 reservation history of room 
			else if(choose == 6) {
				System.out.println("6. Get Reservation History of a Room");
				System.out.println("Enter room ID>: ");
				room_id = input.nextInt();
				String getRoom_hist = "select room_id, room_name, instructor_name from room, reserves, instructor where room.id = reserves.room_id and reserves.instructor_id = instructor.id and reserves.room_id = " + room_id + ";";
				ResultSet rs =  stmt.executeQuery(getRoom_hist);
				if(!rs.isBeforeFirst()) {
					System.out.println("Room " + room_id + " has never been reserved.");
				}
				else {
					while(rs.next()) {
						 room_name = rs.getString("room_name"); 
						 instructor_name = rs.getString("instructor_name");
						
						 System.out.print("|  Room Name: " +room_name); 
						 System.out.println("|  Instructor Name: " + instructor_name);
					}
				}
			}
			
	// Menu Option 7
			else if(choose == 7) {
				System.out.println("7. Get Reservation History of a User");
				System.out.println("Enter Instructor ID>: ");
				instructor_id = input.nextInt();
				String getInstructor_hist = "select room_id, room_name, instructor_name from room, reserves, instructor where room.id = reserves.room_id and reserves.instructor_id = instructor.id and reserves.instructor_id = " + instructor_id + ";";
				ResultSet rs =  stmt.executeQuery(getInstructor_hist);
				if(!rs.isBeforeFirst()) {
					System.out.println("Instructor " + instructor_id + " reservation history is empty.");
				}
				else {
					while(rs.next()) {
						 
						 instructor_name = rs.getString("instructor_name");
						 room_name = rs.getString("room_name"); 
						 
						 System.out.print("|  Instructor Name: " + instructor_name);
						 System.out.println("|  Room Name: " +room_name); 
						
					}
				}
			}
			
	// Menu Option 8
			else if(choose == 8) {
				System.out.println("8. Reserve a Room");
				System.out.println("Enter room ID>: ");
				room_id = input.nextInt();
				System.out.println("Enter instructor ID>: ");
				instructor_id = input.nextInt();
				String get_status = "select reservation_status from room where id = " + room_id + ";";
//				String get_roomNameForcheck = "select room_name from room where id = " + room_id + ";";
				ResultSet rs =  stmt.executeQuery(get_status);
//				ResultSet rsRoom_name =  stmt.executeQuery(get_roomNameForcheck);
				while(rs.next()) {
					reservation_status = rs.getString("reservation_status");
					if(reservation_status.equals("Not occupied")) {
						PreparedStatement preparedStatement01 = conn.prepareStatement("UPDATE room SET reservation_status = 'Occupied' WHERE id = ?;"); 
						preparedStatement01.setInt(1, room_id);
						preparedStatement01.executeUpdate();
						 
						PreparedStatement preparedStatement02 = conn.prepareStatement("insert into reserves (instructor_id, room_id) values(?, ?)" );
						preparedStatement02.setInt(1, instructor_id);
						preparedStatement02.setInt(2, room_id); preparedStatement02.executeUpdate();
						System.out.println("Room: " + room_id + " has been reserved.");
					}
					else {
						System.out.println("You can not reserve room: " + room_id);
					}
				}
			}
			
	// Menu Option 9
			else if(choose == 9) {
				System.out.println("9. Leave a Room");
				System.out.println("Enter room ID>: ");
				room_id = input.nextInt();
				String get_status = "select reservation_status from room where id = " + room_id + ";";
				ResultSet rs =  stmt.executeQuery(get_status);
				while(rs.next()) {
					reservation_status = rs.getString("reservation_status");
					if(reservation_status.equals("Occupied")) {
						PreparedStatement preparedStatement01 = conn.
						prepareStatement("UPDATE room SET reservation_status = 'Not occupied' WHERE id = ?;"); 
						preparedStatement01.setInt(1, room_id);
						preparedStatement01.executeUpdate();
						System.out.println("Room: " + room_id + " has been left.");
					}
					else {
						System.out.println("You can not leave unreserved room: " + room_id + ".");
					}
				}
			}
			
			
			
			
	// Menu Option 0 Shows menu again  
			else if(choose == 0) {
				menu();
				select();
			}
			
	// If user enters number >9 or <0
			else {
				System.out.println("Please choose Menu option from 0 to 9");
				select();
			}
			
			select(); //Looping menu so that after we successfully done something it continues working and we can choose other option
			
		}
		
		
		
///HANDLING EXCEPTIONS. 		
		catch(java.sql.SQLIntegrityConstraintViolationException ex) { //EXCEPTION if we try to create 2 identical rooms in one building. 
			System.out.println("There can not 2 rooms with same name in one Building");
			select();
		}
		finally{
		       //finally block used to close resources
		       try{
		          if(stmt!=null)
		             stmt.close();
		       }
		       catch(SQLException se2){
		       }// nothing we can do
		       try{
		          if(conn!=null)
		             conn.close();
		       }
		       catch(SQLException se){
		          se.printStackTrace();
		       }
		}

	}
	
	public static void main(String[] args) throws Exception {
		menu();
		select();
	}
}
