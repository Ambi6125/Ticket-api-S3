CREATE TABLE accounts (
    id long NOT NULL primary key AUTO_INCREMENT,
    username varchar(50) NOT NULL UNIQUE,
    password varchar(200) NOT NULL,
    email varchar(60) NOT NULL UNIQUE
);

CREATE TABLE tickets (
    id long NOT NULL primary key AUTO_INCREMENT,
    event long not null,
    account_id long not null,

    foreign key (account_id) references accounts(id),
    foreign key (event) references events(id)
);

CREATE TABLE events(
    id long NOT NULL primary key auto_increment,
    title varchar(50) not null unique,
    location varchar(60) not null,
    moment datetime not null,

    constraint timeLocationClause unique(location, moment)
);