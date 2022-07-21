# room_reservation
Java standalone console program, which allows users to acces rooms and reserve them by using a SQL database and Java JDBC. 
You also can find the a SQL script including DDL statemnts DDL and DML statments files for creating database table for storing querriee. You just need to run these queries in MYSQL server scheme so that your tables(room, building, instructor and reserves) were created and you may also do insertion operations to have an instructors and buildings. 


Before using the console application the database should include the 
Buildings:
- Engineering
- Main Building
- Library
- Business                                                        (These are example building names and instructors information you can change them) 
- Architecture
Instructors:
- Muhammed Lee, muhammed@lee.com, Istanbul/Turkey
- Chris Kon, chriscon@fakemail.com, New York/USA
- Mace Windu, macewindu@jedi.com, Coruscant/Jedi


When a room is created, the name of the room must be
unique for each building, for example there can be 2 rooms named 212 if and
only if they are on different buildings. 
Room can only be reserved if and only if there is no current active reservation
on the room.


The console application has the folowwing Menu options: 
Menu:
1. Create Room
2. Remove Room
3. List all Rooms in a Building With Their Vacancy Information
4. Get Currently Occupied Rooms
5. Search For a Room
6. Get Reservation History of a Room
7. Get Reservation History of an Instructor
8. Reserve a Room
9. Leave a Room

The table of Menu options Inputs/Outputs

![Annotation 2022-05-29 115423](https://user-images.githubusercontent.com/98253476/170860173-9ac7018b-7c83-4bdf-a748-5e679edebdf5.jpg)


For modification queries(1,2,8,9) we check if we can execute them. if not, the warning message appears. For example:
If we try to create 2 roooms in the same building with the same room_name then the warning message will appear "Room Creation
is invalid, name must be unique". 

An example run:

![Annotation 2022-05-29 115423](https://user-images.githubusercontent.com/98253476/170860353-e54b81e5-da4e-42a8-ba9e-2389ff60ba52.jpg)


The Dtabase design:
![image5](https://user-images.githubusercontent.com/98253476/180260565-73307f02-c684-428f-9979-77f944e0a056.jpg)


