 -- MAIN
 -- BUILDINGS 
 insert into building (id, building_name) values (1, 'Engineering');
 insert into building (id, building_name) values (2, 'Main Building');
 insert into building (id, building_name) values (3, 'Library');
 insert into building (id, building_name) values (4, 'Business');
 insert into building (id, building_name) values (5, 'Architecture');
 
-- INSTRUCTORS
 insert into instructor (id, instructor_name, instructor_email, city_state) values (1, 'Muhammed Lee', 'muhammed@lee.com', 'Istanbul/Turkey');
 insert into instructor (id, instructor_name, instructor_email, city_state) values (2, 'Chris Kon', 'chriscon@fakemail.com', 'New York/USA');
 insert into instructor (id, instructor_name, instructor_email, city_state) values (3, 'Mace Windu', 'macewindu@jedi.com', 'Coruscant/Jedi');



















-- NOT NECESSARY CAN DELETE IT 

-- insert into room (id, room_name, building_id, capacity, property_string) values (1, 'B001', 4, 10, 'table, pc, laptop');
-- insert into room (room_name, building_id, capacity, property_string) values ('B001', 3, 10, 'table, pc, laptop');
-- insert into room (id, room_name, building_id, capacity, property_string) values (1, 'B002', 4, 10, 'table, pc, laptop');
-- insert into room (room_name, building_id, capacity, property_string, reservation_status) values ('u01', 1, 10, 'table, pc, laptop', 'Not occupied');

-- select room.id, room_name, building_name, capacity, reservation_status from room, building where building.id = 5 and room.building_id = 5;
-- select room.id, room_name, building_name, capacity, reservation_status from room, building where building.id = 1 and room.building_id = 1;
-- UPDATE room SET reservation_status = 'Not occupied' WHERE id = 1;
-- insert into reserves (instructor_id, room_id) values(1, 1)
-- select reservation_status from room where id = 1;
-- delete from reserves where reservation_id = 2;
-- UPDATE room SET reservation_status = 'Not occupied' WHERE id = 1;

-- select room.id, room_name, building_id, capacity from room where reservation_status = 'Occupied';
-- select reservation_status from room;
--  select room.id, room_name, capacity, reservation_status from room where reservation_status = 'Occupied'
-- select room_id, room_name, instructor_name from room, reserves, instructor where room.id = reserves.room_id and reserves.instructor_id = instructor.id;
-- select room_id, room_name, instructor_name from room, reserves, instructor where room.id = reserves.room_id and reserves.instructor_id = instructor.id and reserves.instructor_id = 2;