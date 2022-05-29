Drop table if exists reserves;
Drop table if exists room;
Drop table if exists instructor;
Drop table if exists building;

create table building (
	id					int				not null,
    building_name		varchar(30)		not null,
    primary key(id)
);




create table room (
	id					int				not null auto_increment unique,
    room_name			varchar(30) 	not null,
    building_id			int				not null,
    capacity 			int 			not null,
	property_string		varchar(30) 	not null, 
    reservation_status	varchar(30) 	not null,
    primary key(room_name, building_id ),
    foreign key (building_id) references building(id)
);




create table instructor (
	id					int				not null,
    instructor_name		varchar(30)		not null,
    instructor_email 	varchar(30) 	not null,
    city_state			varchar(30) 	not null,    
    primary key(id)
);




create table reserves (
	reservation_id      int 			not null auto_increment,
	instructor_id		int 			not null,
    room_id				int		    	not null,
    primary key(reservation_id),
    foreign key (instructor_id) references instructor (id),
	foreign key (room_id) references room (id)
);



