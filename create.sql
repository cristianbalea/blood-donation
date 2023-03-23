drop database if exists donateblood;
create database donateblood;
use donateblood;

SET FOREIGN_KEY_CHECKS = 0;

drop table if exists Location;
create table Location (
	id integer primary key auto_increment,
    name varchar(40),
    address varchar(40),
    area varchar(40),
    openHour integer,
    closingHour integer,
    opened bool,
    maxNumberOfDonors integer
);

drop table if exists Donor;
create table Donor (
	id integer primary key auto_increment,
    cnp varchar(40),
    firstname varchar(40),
    lastname varchar(40),
    email varchar(40),
    password varchar(40),
    area varchar(40),
    bloodType varchar(40)
);

drop table if exists Admin;
create table Admin (
	id integer primary key auto_increment,
    email varchar(40),
    password varchar(40)
);


drop table if exists Doctor;
create table Doctor (
	id integer primary key auto_increment,
    cnp varchar(40),
    firstname varchar(40),
    lastname varchar(40),
    email varchar(40),
    password varchar(40),
    location integer,
    constraint fk_doctor_location foreign key (location) 
    references Location(id)
);

drop table if exists Appointment;
create table Appointment (
	id integer primary key auto_increment,
    location integer,
    donor integer,
    date date,
    constraint fk_appointment_location foreign key(location)
    references Location(id),
    constraint fk_appointment_donor foreign key(donor)
    references Donor(id)
);

INSERT INTO `donateblood`.`admin` (`email`, `password`) VALUES ('admin', 'admin');
INSERT INTO donor (cnp, firstname, lastname, email, password, area, bloodtype)
values("5020204058899", "John", "Doe", "johndoe@domain.ro", "mypass", "Cluj", "01");
insert into location (name, address, area, openhour, closinghour, opened, maxNumberOfDonors)
values("Centrul Regina Maria", "Zorilor 21", "Cluj", 10, 18, true, 40);
INSERT into doctor (cnp, firstname, lastname, email, password, location)
values("5990707010099", "Mary", "Helen", "mary@domain.ro", "mypass", 1);

