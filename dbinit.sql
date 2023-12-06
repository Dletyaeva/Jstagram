use dletyaeva2;

drop table if exists VIZ;
drop table if exists PostDetails;
drop table if exists UserDetails;

create table UserDetails (
	userID int not null,
    userName varchar(255) not null,
    password varchar(255) not null,
    primary key(userID)
);

create table PostDetails(
	postID int not null auto_increment,
	postText varchar(255),
    postTime varchar(255) not null,
    userID int not null,
    primary key (postID),
	foreign key (userID) references UserDetails(userID)
);

create table VIZ (
	userID int not null,
    Alice varchar(2),
    Bob varchar(2),
    Crystal varchar(2),
    David varchar(2),
	primary key (userID),
    foreign key (userID) references UserDetails(userID)
);


insert into UserDetails values
	(1,"Alice", "Alice123"),
    (2,"Bob", "Bob123"),
    (3,"Crystal", "Crystal123") ,
    (4,"David","David123")
;

insert into PostDetails values
	(1, 'Project deadline extended?', '19:00:00 October 12, 2023', 1),  -- Alice
    (2, 'Yep', '19:01:00 October 12, 2023', 2), -- Bod
    (3, 'Fall break', '09:00:00 October 16, 2023', 4), -- David
    (4, 'Lab due tonight?', '23:30:00 October 27, 2023', 1), -- Alice 
    (5, 'No, it\'s due next week', '23:35:00 October 27, 2023', 3) -- Crystal
;

insert into VIZ values
-- (Listof) Al, Bob, Cry, Da
		(1, 'Y', 'Y', 'Y', 'N'),   -- Al
		(2, 'Y', 'Y', 'N', 'N'),   -- Bob
		(3, 'Y', 'Y', 'Y', 'N'),   -- Cry
		(4, 'N', 'N', 'N', 'Y');   -- Da