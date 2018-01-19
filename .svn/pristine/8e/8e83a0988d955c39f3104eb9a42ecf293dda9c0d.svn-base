CREATE Database spring_project;

Use spring_project;

CREATE TABLE IF NOT EXISTS `deferrals` (
`Def_ID` int(11) NOT NULL,
  `Student_ID` varchar(10) NOT NULL,
  `Lect_ID` varchar(10) NOT NULL,
  `Programme_ID` varchar(20) NOT NULL,
  `Module_ID` varchar(10) NOT NULL,
  `Approved` enum('True','False') NOT NULL DEFAULT 'False'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=782 ;



CREATE TABLE IF NOT EXISTS `lecturer` (
  `Lect_ID` varchar(10) NOT NULL,
  `Firstname` varchar(20) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Lecturer_Email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `modules` (
  `Module_ID` varchar(10) NOT NULL,
  `CRN` int(10) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Lect_ID` varchar(20) NOT NULL,
  `Semester_ID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `programme` (
  `Programme_ID` varchar(20) NOT NULL,
  `Num_Years` int(11) NOT NULL,
  `Coord_ID` varchar(10) NOT NULL,
  `Prog_Year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `registration` (
  `Student_ID` varchar(10) NOT NULL,
  `CRN` int(10) NOT NULL,
  `Programme_ID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `semester` (
  `Semester_ID` varchar(20) NOT NULL,
  `Programme_ID` varchar(20) DEFAULT NULL,
  `StartDate` enum('Sept','Jan') NOT NULL,
  `FinishDate` enum('Dec','May') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `student` (
  `Student_ID` varchar(10) NOT NULL,
  `Firstname` varchar(20) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Student_Email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `deferrals`
 ADD PRIMARY KEY (`Def_ID`,`Student_ID`), ADD KEY `Student_ID` (`Student_ID`), ADD KEY `Programme_ID` (`Programme_ID`), ADD KEY `Module_ID` (`Module_ID`), ADD KEY `Lect_ID` (`Lect_ID`);


ALTER TABLE `lecturer`
 ADD PRIMARY KEY (`Lect_ID`);


ALTER TABLE `modules`
 ADD PRIMARY KEY (`Module_ID`,`CRN`), ADD UNIQUE KEY `CRN` (`CRN`), ADD KEY `Lect_ID` (`Lect_ID`), ADD KEY `Semester_ID` (`Semester_ID`);


ALTER TABLE `programme`
 ADD PRIMARY KEY (`Programme_ID`), ADD KEY `Coord_ID` (`Coord_ID`), ADD KEY `Programme_ID` (`Programme_ID`);


ALTER TABLE `registration`
 ADD PRIMARY KEY (`Student_ID`,`CRN`), ADD KEY `CRN` (`CRN`), ADD KEY `Programme_ID` (`Programme_ID`);


ALTER TABLE `semester`
 ADD PRIMARY KEY (`Semester_ID`), ADD KEY `Programme_ID` (`Programme_ID`);


ALTER TABLE `student`
 ADD PRIMARY KEY (`Student_ID`);


ALTER TABLE `deferrals`
MODIFY `Def_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=782;

ALTER TABLE `deferrals`
ADD CONSTRAINT `deferrals_ibfk_2` FOREIGN KEY (`Programme_ID`) REFERENCES `programme` (`Programme_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `deferrals_ibfk_3` FOREIGN KEY (`Module_ID`) REFERENCES `modules` (`Module_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `deferrals_ibfk_4` FOREIGN KEY (`Student_ID`) REFERENCES `student` (`Student_ID`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `modules`
ADD CONSTRAINT `modules_ibfk_1` FOREIGN KEY (`Lect_ID`) REFERENCES `lecturer` (`Lect_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `modules_ibfk_2` FOREIGN KEY (`Semester_ID`) REFERENCES `semester` (`Semester_ID`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `programme`
ADD CONSTRAINT `programme_ibfk_1` FOREIGN KEY (`Coord_ID`) REFERENCES `lecturer` (`Lect_ID`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `registration`
ADD CONSTRAINT `registration_ibfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `student` (`Student_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `registration_ibfk_2` FOREIGN KEY (`CRN`) REFERENCES `modules` (`CRN`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `registration_ibfk_3` FOREIGN KEY (`Programme_ID`) REFERENCES `programme` (`Programme_ID`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `semester`
ADD CONSTRAINT `semester_ibfk_2` FOREIGN KEY (`Programme_ID`) REFERENCES `programme` (`Programme_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (100, 'L001', '123456', TRUE);
 
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (0, 100, 'ROLE_ADMIN');

INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (101, 'Bill', '123456', TRUE);
 
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (1, 101, 'ROLE_USER');

