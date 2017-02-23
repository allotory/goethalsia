-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema wellmail
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ wellmail;
USE wellmail;

--
-- Table structure for table `wellmail`.`attachment`
--

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `attachmentid` int(11) NOT NULL auto_increment,
  `attachmentpath` varchar(255) default NULL,
  `attachmentname` varchar(255) default NULL,
  `attachmentsize` double default NULL,
  `emailid` int(11) default NULL,
  PRIMARY KEY  (`attachmentid`),
  KEY `FK8AF759232660CF92` (`emailid`),
  CONSTRAINT `FK8AF759232660CF92` FOREIGN KEY (`emailid`) REFERENCES `email` (`emailid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`attachment`
--

/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`bcc`
--

DROP TABLE IF EXISTS `bcc`;
CREATE TABLE `bcc` (
  `bccid` int(11) NOT NULL auto_increment,
  `bccname` varchar(255) default NULL,
  `emailid` int(11) default NULL,
  PRIMARY KEY  (`bccid`),
  KEY `FK17C422660CF92` (`emailid`),
  CONSTRAINT `FK17C422660CF92` FOREIGN KEY (`emailid`) REFERENCES `email` (`emailid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`bcc`
--

/*!40000 ALTER TABLE `bcc` DISABLE KEYS */;
/*!40000 ALTER TABLE `bcc` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`cc`
--

DROP TABLE IF EXISTS `cc`;
CREATE TABLE `cc` (
  `ccid` int(11) NOT NULL auto_increment,
  `ccname` varchar(255) default NULL,
  `emailid` int(11) default NULL,
  PRIMARY KEY  (`ccid`),
  KEY `FKC602660CF92` (`emailid`),
  CONSTRAINT `FKC602660CF92` FOREIGN KEY (`emailid`) REFERENCES `email` (`emailid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`cc`
--

/*!40000 ALTER TABLE `cc` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `contactid` int(11) NOT NULL auto_increment,
  `contactname` varchar(255) default NULL,
  `contactemail` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `groupid` int(11) default NULL,
  PRIMARY KEY  (`contactid`),
  KEY `FK38B724207910B91D` (`username`),
  KEY `FK38B72420E0E4FEFA` (`groupid`),
  CONSTRAINT `FK38B72420E0E4FEFA` FOREIGN KEY (`groupid`) REFERENCES `contactgroup` (`groupid`),
  CONSTRAINT `FK38B724207910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`contact`
--

/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`contactgroup`
--

DROP TABLE IF EXISTS `contactgroup`;
CREATE TABLE `contactgroup` (
  `groupid` int(11) NOT NULL auto_increment,
  `groupname` varchar(255) default NULL,
  `containusercount` int(11) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`groupid`),
  KEY `FK4C9B665F7910B91D` (`username`),
  CONSTRAINT `FK4C9B665F7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`contactgroup`
--


--
-- Table structure for table `wellmail`.`email`
--

DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `emailid` int(11) NOT NULL auto_increment,
  `sender` varchar(255) default NULL,
  `recipients` varchar(255) default NULL,
  `subject` varchar(255) default NULL,
  `content` mediumtext,
  `senttime` varchar(255) default NULL,
  `mailtype` varchar(255) default NULL,
  `msgsize` double default NULL,
  `unread` int(11) default NULL,
  `folderid` int(11) default NULL,
  `priorityid` int(11) default NULL,
  `tagid` int(11) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`emailid`),
  KEY `FK5C24B9C7910B91D` (`username`),
  KEY `FK5C24B9C9459E8C4` (`priorityid`),
  KEY `FK5C24B9C2CB8AF17` (`tagid`),
  KEY `FK5C24B9C8BB88818` (`folderid`),
  CONSTRAINT `FK5C24B9C8BB88818` FOREIGN KEY (`folderid`) REFERENCES `folder` (`folderid`),
  CONSTRAINT `FK5C24B9C2CB8AF17` FOREIGN KEY (`tagid`) REFERENCES `mailtag` (`tagid`),
  CONSTRAINT `FK5C24B9C7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `FK5C24B9C9459E8C4` FOREIGN KEY (`priorityid`) REFERENCES `priority` (`priorityid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`email`
--

/*!40000 ALTER TABLE `email` DISABLE KEYS */;
/*!40000 ALTER TABLE `email` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`emailspace`
--

DROP TABLE IF EXISTS `emailspace`;
CREATE TABLE `emailspace` (
  `esid` int(11) NOT NULL auto_increment,
  `espace` double default NULL,
  `spaceleft` double default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`esid`),
  UNIQUE KEY `username` (`username`),
  KEY `FK4FB186EA7910B91D` (`username`),
  CONSTRAINT `FK4FB186EA7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;




--
-- Table structure for table `wellmail`.`filetype`
--

DROP TABLE IF EXISTS `filetype`;
CREATE TABLE `filetype` (
  `filetypeid` int(11) NOT NULL auto_increment,
  `filetypename` varchar(255) default NULL,
  `containfilecount` int(11) default NULL,
  `foldersize` double default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`filetypeid`),
  KEY `FKD43766B67910B91D` (`username`),
  CONSTRAINT `FKD43766B67910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`filetype`
--

--
-- Table structure for table `wellmail`.`folder`
--

DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `folderid` int(11) NOT NULL auto_increment,
  `foldername` varchar(255) default NULL,
  `folderspace` double default NULL,
  `mailcount` int(11) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`folderid`),
  KEY `FKB45D1C6E7910B91D` (`username`),
  CONSTRAINT `FKB45D1C6E7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`folder`
--



--
-- Table structure for table `wellmail`.`mailtag`
--

DROP TABLE IF EXISTS `mailtag`;
CREATE TABLE `mailtag` (
  `tagid` int(11) NOT NULL auto_increment,
  `tagname` varchar(255) default NULL,
  PRIMARY KEY  (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*!40000 ALTER TABLE `mailtag` DISABLE KEYS */;
INSERT INTO `mailtag` (`tagid`,`tagname`) VALUES 
 (1,'重要邮件'),
 (2,'公司邮件'),
 (3,'业务邮件'),
 (4,'资讯邮件'),
 (5,'亲友邮件'),
 (6,'同学邮件'),
 (7,'休闲邮件'),
 (8,'趣闻邮件'),
 (9,'普通邮件');
/*!40000 ALTER TABLE `mailtag` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`notebook`
--

DROP TABLE IF EXISTS `notebook`;
CREATE TABLE `notebook` (
  `noteid` int(11) NOT NULL auto_increment,
  `notetitle` varchar(255) default NULL,
  `notecontent` mediumtext,
  `username` varchar(255) default NULL,
  `notetypeid` int(11) default NULL,
  PRIMARY KEY  (`noteid`),
  KEY `FK5E44A1DB7910B91D` (`username`),
  KEY `FK5E44A1DBFCA753F4` (`notetypeid`),
  CONSTRAINT `FK5E44A1DBFCA753F4` FOREIGN KEY (`notetypeid`) REFERENCES `notetype` (`notetypeid`),
  CONSTRAINT `FK5E44A1DB7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`notebook`
--

/*!40000 ALTER TABLE `notebook` DISABLE KEYS */;
/*!40000 ALTER TABLE `notebook` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`notetype`
--

DROP TABLE IF EXISTS `notetype`;
CREATE TABLE `notetype` (
  `notetypeid` int(11) NOT NULL auto_increment,
  `notetypename` varchar(255) default NULL,
  `containfilecount` int(11) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`notetypeid`),
  KEY `FK5E4CF62C7910B91D` (`username`),
  CONSTRAINT `FK5E4CF62C7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`notetype`
--




--
-- Table structure for table `wellmail`.`othermailbox`
--

DROP TABLE IF EXISTS `othermailbox`;
CREATE TABLE `othermailbox` (
  `uid` int(11) NOT NULL auto_increment,
  `uname` varchar(255) default NULL,
  `upass` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `tid` int(11) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `FKFE4AF7A47910B91D` (`username`),
  KEY `FKFE4AF7A416E859AE` (`tid`),
  CONSTRAINT `FKFE4AF7A416E859AE` FOREIGN KEY (`tid`) REFERENCES `othermailboxtype` (`tid`),
  CONSTRAINT `FKFE4AF7A47910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`othermailbox`
--

/*!40000 ALTER TABLE `othermailbox` DISABLE KEYS */;
/*!40000 ALTER TABLE `othermailbox` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`othermailboxtype`
--

DROP TABLE IF EXISTS `othermailboxtype`;
CREATE TABLE `othermailboxtype` (
  `tid` int(11) NOT NULL auto_increment,
  `tsmtp` varchar(255) default NULL,
  `tpop3` varchar(255) default NULL,
  `tname` varchar(255) default NULL,
  PRIMARY KEY  (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`othermailboxtype`
--

/*!40000 ALTER TABLE `othermailboxtype` DISABLE KEYS */;
INSERT INTO `othermailboxtype` (`tid`,`tsmtp`,`tpop3`,`tname`) VALUES 
 (1,'smtp.126.com','pop.126.com','126'),
 (2,'smtp.163.com','pop.163.com','163'),
 (3,'smtp.sina.com','pop.sina.com.cn','sina'),
 (4,'smtp.sohu.com','pop3.sohu.com','sohu'),
 (5,'smtp.mail.yahoo.com.cn','pop.mail.yahoo.com.cn','yahoo'),
 (6,'smtp.gmail.com','pop3.gmail.com','gmail'),
 (7,'smtp.live.com','pop3.live.com','hotmail live');
/*!40000 ALTER TABLE `othermailboxtype` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`priority`
--

DROP TABLE IF EXISTS `priority`;
CREATE TABLE `priority` (
  `priorityid` int(11) NOT NULL auto_increment,
  `priorityname` varchar(255) default NULL,
  PRIMARY KEY  (`priorityid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`priority`
--

/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
INSERT INTO `priority` (`priorityid`,`priorityname`) VALUES 
 (1,'紧急邮件'),
 (2,'普通邮件'),
 (3,'缓慢邮件');
/*!40000 ALTER TABLE `priority` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`userpwd`
--

DROP TABLE IF EXISTS `userpwd`;
CREATE TABLE `userpwd` (
  `qid` int(11) NOT NULL auto_increment,
  `question` varchar(255) default NULL,
  `answer` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`qid`),
  UNIQUE KEY `username` (`username`),
  KEY `FKF73B2F927910B91D` (`username`),
  CONSTRAINT `FKF73B2F927910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;



--
-- Table structure for table `wellmail`.`users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `pwdHash` varchar(255) default NULL,
  `pwdAlgorithm` varchar(255) default NULL,
  `useForwarding` int(11) default NULL,
  `forwardDestination` varchar(255) default NULL,
  `useAlias` int(11) default NULL,
  `alias` varchar(255) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`users`
--



--
-- Table structure for table `wellmail`.`userscore`
--

DROP TABLE IF EXISTS `userscore`;
CREATE TABLE `userscore` (
  `scoreid` int(11) NOT NULL auto_increment,
  `level` int(11) default NULL,
  `score` int(11) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`scoreid`),
  UNIQUE KEY `username` (`username`),
  KEY `FK154EF9A77910B91D` (`username`),
  CONSTRAINT `FK154EF9A77910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`userscore`
--




--
-- Table structure for table `wellmail`.`usersinfo`
--

DROP TABLE IF EXISTS `usersinfo`;
CREATE TABLE `usersinfo` (
  `usersinfoid` int(11) NOT NULL auto_increment,
  `sex` varchar(255) default NULL,
  `birthday` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`usersinfoid`),
  UNIQUE KEY `username` (`username`),
  KEY `FK1551AEB67910B91D` (`username`),
  CONSTRAINT `FK1551AEB67910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`usersinfo`
--



--
-- Table structure for table `wellmail`.`webdisk`
--

DROP TABLE IF EXISTS `webdisk`;
CREATE TABLE `webdisk` (
  `webspaceid` int(11) NOT NULL auto_increment,
  `webspace` double default NULL,
  `wsleft` double default NULL,
  `filecount` int(11) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`webspaceid`),
  UNIQUE KEY `username` (`username`),
  KEY `FK48F30EF17910B91D` (`username`),
  CONSTRAINT `FK48F30EF17910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`webdisk`
--




--
-- Table structure for table `wellmail`.`webfile`
--

DROP TABLE IF EXISTS `webfile`;
CREATE TABLE `webfile` (
  `fileid` int(11) NOT NULL auto_increment,
  `filename` varchar(255) default NULL,
  `filesize` double default NULL,
  `uploadtime` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `filetypeid` int(11) default NULL,
  PRIMARY KEY  (`fileid`),
  KEY `FK48F3F6D07910B91D` (`username`),
  KEY `FK48F3F6D017A23A88` (`filetypeid`),
  CONSTRAINT `FK48F3F6D017A23A88` FOREIGN KEY (`filetypeid`) REFERENCES `filetype` (`filetypeid`),
  CONSTRAINT `FK48F3F6D07910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`webfile`
--

/*!40000 ALTER TABLE `webfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `webfile` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
