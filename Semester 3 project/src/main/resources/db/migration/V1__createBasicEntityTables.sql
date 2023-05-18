CREATE TABLE accounts (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    password varchar(200) NOT NULL,
    email varchar(60) NOT NULL,

    primary key(id),
    UNIQUE(username),
    UNIQUE(email)
);


CREATE TABLE events(
    id int NOT NULL primary key auto_increment,
    title varchar(50) not null unique,
    location varchar(60) not null,
    moment datetime not null,
    totalTickets int not null,
    remainingTickets int not null,

    constraint timeLocationClause unique(location, moment)
);

CREATE TABLE tickets (
     id int NOT NULL primary key AUTO_INCREMENT,
     event int not null,
     account_id int not null,

     foreign key (account_id) references accounts(id),
     foreign key (event) references events(id)
);