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
INSERT INTO `attachment` (`attachmentid`,`attachmentpath`,`attachmentname`,`attachmentsize`,`emailid`) VALUES 
 (1,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/wellmail.sql','wellmail.sql',0.01,2),
 (2,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/JavaMail API详解.txt','JavaMail API详解.txt',0.019,16);
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
  CONSTRAINT `FK38B724207910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `FK38B72420E0E4FEFA` FOREIGN KEY (`groupid`) REFERENCES `contactgroup` (`groupid`)
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

/*!40000 ALTER TABLE `contactgroup` DISABLE KEYS */;
INSERT INTO `contactgroup` (`groupid`,`groupname`,`containusercount`,`username`) VALUES 
 (1,'朋友',0,'aaaaaa@mymail.com'),
 (2,'亲人',0,'aaaaaa@mymail.com'),
 (3,'同事',0,'aaaaaa@mymail.com'),
 (4,'老师',0,'aaaaaa@mymail.com'),
 (5,'同学',0,'aaaaaa@mymail.com'),
 (6,'朋友',0,'bbbbbb@mymail.com'),
 (7,'亲人',0,'bbbbbb@mymail.com'),
 (8,'同事',0,'bbbbbb@mymail.com'),
 (9,'老师',0,'bbbbbb@mymail.com'),
 (10,'同学',0,'bbbbbb@mymail.com');
/*!40000 ALTER TABLE `contactgroup` ENABLE KEYS */;


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
  CONSTRAINT `FK5C24B9C2CB8AF17` FOREIGN KEY (`tagid`) REFERENCES `mailtag` (`tagid`),
  CONSTRAINT `FK5C24B9C7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `FK5C24B9C8BB88818` FOREIGN KEY (`folderid`) REFERENCES `folder` (`folderid`),
  CONSTRAINT `FK5C24B9C9459E8C4` FOREIGN KEY (`priorityid`) REFERENCES `priority` (`priorityid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`email`
--

/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` (`emailid`,`sender`,`recipients`,`subject`,`content`,`senttime`,`mailtype`,`msgsize`,`unread`,`folderid`,`priorityid`,`tagid`,`username`) VALUES 
 (1,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 1','test 1test 1test 1test 1test 1','2010-06-09 09:19:55','text/html',0.01,0,9,2,9,'bbbbbb@mymail.com'),
 (2,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 1','test 1test 1test 1test 1test 1','2010-06-09 09:19:55','text/html',0.01,0,1,2,1,'aaaaaa@mymail.com'),
 (3,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 2','test2 sl;dfja;lsdjf;lasjdf;lasjdf;lasjdf;lajsd;lfja;sldjf;','2010-06-09 09:20:21','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (4,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 2','test2 sl;dfja;lsdjf;lasjdf;lasjdf;lasjdf;lajsd;lfja;sldjf;','2010-06-09 09:20:21','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com'),
 (5,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 3','test 3 a;sdfcmnv,z.xcvnzx,mcvn.zxcnv.z,xcnv.z,xcnvadsfja;sd','2010-06-09 09:20:42','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (6,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 3','test 3 a;sdfcmnv,z.xcvnzx,mcvn.zxcnv.z,xcnv.z,xcnvadsfja;sd','2010-06-09 09:20:42','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com');
INSERT INTO `email` (`emailid`,`sender`,`recipients`,`subject`,`content`,`senttime`,`mailtype`,`msgsize`,`unread`,`folderid`,`priorityid`,`tagid`,`username`) VALUES 
 (7,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 4','sd;fjas;l xc,vzmxc,vmroiuoqwpeoirqpweurpoqiuepoqw','2010-06-09 09:21:03','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (8,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 4','sd;fjas;l xc,vzmxc,vmroiuoqwpeoirqpweurpoqiuepoqw','2010-06-09 09:21:03','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com'),
 (9,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 5','fasdfjwoeruocx,vmxoqwieujcvmiqoweuroqwef','2010-06-09 09:21:23','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (10,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 5','fasdfjwoeruocx,vmxoqwieujcvmiqoweuroqwef','2010-06-09 09:21:23','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com'),
 (11,'bbbbbb@mymail.com','aaaaaa@mymail.com','test6',';dfajsdcxvmx.,cc,c,c,c,cc,c,c,c,cc,c,c','2010-06-09 09:21:43','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (12,'bbbbbb@mymail.com','aaaaaa@mymail.com','test6',';dfajsdcxvmx.,cc,c,c,c,cc,c,c,c,cc,c,c','2010-06-09 09:21:44','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com');
INSERT INTO `email` (`emailid`,`sender`,`recipients`,`subject`,`content`,`senttime`,`mailtype`,`msgsize`,`unread`,`folderid`,`priorityid`,`tagid`,`username`) VALUES 
 (13,'bbbbbb@mymail.com','aaaaaa@mymail.com','test7','test 690sd0fasdlkjc482-485340951-341=2-0439124350913847 5873498','2010-06-09 09:22:09','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (14,'bbbbbb@mymail.com','aaaaaa@mymail.com','test7','test 690sd0fasdlkjc482-485340951-341=2-0439124350913847 5873498','2010-06-09 09:22:09','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com'),
 (15,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 8 ','sdf;asljdf;lasjxcmvzxcvnzx,.cnvz.x,cnv.z,xcv<br />\r\nasdfjkals;djfxc<br />\r\nvzxcvzx','2010-06-09 09:22:37','text/html',0.026,0,9,2,9,'bbbbbb@mymail.com'),
 (16,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 8','sdf;asljdf;lasjxcmvzxcvnzx,.cnvz.x,cnv.z,xcv<br />\r\nasdfjkals;djfxc<br />\r\nvzxcvzx','2010-06-09 09:22:37','text/html',0.026,1,1,2,9,'aaaaaa@mymail.com'),
 (17,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 9','sldfjas;ldjfxc,mvqoriutcmvqijskdlj30495u810394094uj0sw3o8utj0ojumgs5ujgt0','2010-06-09 09:22:57','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (18,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 9','sldfjas;ldjfxc,mvqoriutcmvqijskdlj30495u810394094uj0sw3o8utj0ojumgs5ujgt0','2010-06-09 09:22:57','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com');
INSERT INTO `email` (`emailid`,`sender`,`recipients`,`subject`,`content`,`senttime`,`mailtype`,`msgsize`,`unread`,`folderid`,`priorityid`,`tagid`,`username`) VALUES 
 (19,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 10','djfalsjkdxc,vmoweirjcx,mowmciercmircmirmcfirmfirckirkf','2010-06-09 09:23:17','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (20,'bbbbbb@mymail.com','aaaaaa@mymail.com','test 10','djfalsjkdxc,vmoweirjcx,mowmciercmircmirmcfirmfirckirkf','2010-06-09 09:23:17','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com'),
 (21,'bbbbbb@mymail.com','aaaaaa@mymail.com','test11','fsaldkfjsdfacm,cmcmcmcmcmcmcmc','2010-06-09 09:23:39','text/html',0.001,0,9,2,9,'bbbbbb@mymail.com'),
 (22,'bbbbbb@mymail.com','aaaaaa@mymail.com','test11','fsaldkfjsdfacm,cmcmcmcmcmcmcmc','2010-06-09 09:23:39','text/html',0.001,1,1,2,9,'aaaaaa@mymail.com'),
 (23,'aaaaaa@mymail.com','bbbbbb@mymail.com','script 1','script 1script 1script 1script 1script 1script 1script 1','2010-06-09 09:35:50','text/html',0.0009765625,0,2,2,1,'aaaaaa@mymail.com'),
 (24,'aaaaaa@mymail.com','bbbbbb@mymail.com','script 2','<a href=\"mailto:bbbbbb@mymail.combbbbbb@mymail.com\">bbbbbb@mymail.com<u><font color=\"#0000ff\">bbbbbb@mymail.com</font></u></a>&nbsp;wefsadasdasdasda','2010-06-09 09:36:14','text/html',0.0009765625,0,2,2,9,'aaaaaa@mymail.com');
INSERT INTO `email` (`emailid`,`sender`,`recipients`,`subject`,`content`,`senttime`,`mailtype`,`msgsize`,`unread`,`folderid`,`priorityid`,`tagid`,`username`) VALUES 
 (25,'aaaaaa@mymail.com','bbbbbb@mymail.com','script 3',';alsdfjk;lasdjf;lasjdfasdfasdfasdf','2010-06-09 09:36:39','text/html',0.0009765625,0,2,2,9,'aaaaaa@mymail.com'),
 (26,'aaaaaa@mymail.com','bbbbbb@mymail.com','script 4','asdf;lasjdflasd,mvzx,cmvnzx,.mcn,zxcv','2010-06-09 09:37:07','text/html',0.0009765625,0,2,2,9,'aaaaaa@mymail.com'),
 (27,'aaaaaa@mymail.com','bbbbbb@mymail.com','script 5','asdlfjaczxvmz,xcmvzxcnbcm,vbncm,vnbcm,vnbm,cvnbm,cbn,c','2010-06-09 09:37:28','text/html',0.0009765625,0,2,2,9,'aaaaaa@mymail.com'),
 (28,'aaaaaa@mymail.com','bbbbbb@mymail.com','test 1','teat 2sdfgsdfbsdfgbsdb','2010-06-09 12:05:14','text/html',0.001,0,3,2,1,'aaaaaa@mymail.com'),
 (29,'aaaaaa@mymail.com','bbbbbb@mymail.com','test 1','teat 2sdfgsdfbsdfgbsdb','2010-06-09 12:05:15','text/html',0.001,1,7,2,9,'bbbbbb@mymail.com'),
 (30,'aaaaaa@mymail.com','bbbbbb@mymail.com','test2','dfa;lsdjkfl;ajsdfkajs;dlfa','2010-06-09 12:05:36','text/html',0.001,0,3,2,9,'aaaaaa@mymail.com'),
 (31,'aaaaaa@mymail.com','bbbbbb@mymail.com','test2','dfa;lsdjkfl;ajsdfkajs;dlfa','2010-06-09 12:05:37','text/html',0.001,1,7,2,9,'bbbbbb@mymail.com');
INSERT INTO `email` (`emailid`,`sender`,`recipients`,`subject`,`content`,`senttime`,`mailtype`,`msgsize`,`unread`,`folderid`,`priorityid`,`tagid`,`username`) VALUES 
 (32,'aaaaaa@mymail.com','bbbbbb@mymail.com','test 3','asldfja;lsdjflasjdfxdmv,zxnv,zxmcnv,zx','2010-06-09 12:05:57','text/html',0.001,0,3,2,9,'aaaaaa@mymail.com'),
 (33,'aaaaaa@mymail.com','bbbbbb@mymail.com','test 3','asldfja;lsdjflasjdfxdmv,zxnv,zxmcnv,zx','2010-06-09 12:05:57','text/html',0.001,1,7,2,9,'bbbbbb@mymail.com'),
 (34,'aaaaaa@mymail.com','bbbbbb@mymail.com','test3','asdfl;ajsdlfzx,cvnzx,cvn,zxcnvz,xcnv,zxmcv','2010-06-09 12:06:14','text/html',0.001,0,3,2,9,'aaaaaa@mymail.com'),
 (35,'aaaaaa@mymail.com','bbbbbb@mymail.com','test3','asdfl;ajsdlfzx,cvnzx,cvn,zxcnvz,xcnv,zxmcv','2010-06-09 12:06:15','text/html',0.001,1,7,2,9,'bbbbbb@mymail.com'),
 (36,'aaaaaa@mymail.com','bbbbbb@mymail.com','test 5','asdfxcv,zxmcnv,zxmc,vznxb,.mzxc,.vznx.,cv','2010-06-09 12:06:28','text/html',0.001,0,3,2,9,'aaaaaa@mymail.com'),
 (37,'aaaaaa@mymail.com','bbbbbb@mymail.com','test 5','asdfxcv,zxmcnv,zxmc,vznxb,.mzxc,.vznx.,cv','2010-06-09 12:06:28','text/html',0.001,1,7,2,9,'bbbbbb@mymail.com');
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
-- Dumping data for table `wellmail`.`emailspace`
--

/*!40000 ALTER TABLE `emailspace` DISABLE KEYS */;
INSERT INTO `emailspace` (`esid`,`espace`,`spaceleft`,`username`) VALUES 
 (1,2048,2047.9451171875,'aaaaaa@mymail.com'),
 (2,2048,2047.95,'bbbbbb@mymail.com');
/*!40000 ALTER TABLE `emailspace` ENABLE KEYS */;


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

/*!40000 ALTER TABLE `filetype` DISABLE KEYS */;
INSERT INTO `filetype` (`filetypeid`,`filetypename`,`containfilecount`,`foldersize`,`username`) VALUES 
 (1,'我的文档',5,0.574,'aaaaaa@mymail.com'),
 (2,'我的图片',0,0,'aaaaaa@mymail.com'),
 (3,'我的音乐',0,0,'aaaaaa@mymail.com'),
 (4,'我的多媒体',0,0,'aaaaaa@mymail.com'),
 (5,'我的软件',0,0,'aaaaaa@mymail.com'),
 (6,'我的文档',0,0,'bbbbbb@mymail.com'),
 (7,'我的图片',0,0,'bbbbbb@mymail.com'),
 (8,'我的音乐',0,0,'bbbbbb@mymail.com'),
 (9,'我的多媒体',0,0,'bbbbbb@mymail.com'),
 (10,'我的软件',0,0,'bbbbbb@mymail.com');
/*!40000 ALTER TABLE `filetype` ENABLE KEYS */;


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

/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` (`folderid`,`foldername`,`folderspace`,`mailcount`,`username`) VALUES 
 (1,'收件箱',0.045,11,'aaaaaa@mymail.com'),
 (2,'草稿箱',-0.0048828125,0,'aaaaaa@mymail.com'),
 (3,'已发送',0.005,5,'aaaaaa@mymail.com'),
 (4,'已删除',0.0048828125,0,'aaaaaa@mymail.com'),
 (5,'垃圾邮件',0,0,'aaaaaa@mymail.com'),
 (6,'广告邮件',0,0,'aaaaaa@mymail.com'),
 (7,'收件箱',0.005,5,'bbbbbb@mymail.com'),
 (8,'草稿箱',0,0,'bbbbbb@mymail.com'),
 (9,'已发送',0.045,11,'bbbbbb@mymail.com'),
 (10,'已删除',0,0,'bbbbbb@mymail.com'),
 (11,'垃圾邮件',0,0,'bbbbbb@mymail.com'),
 (12,'广告邮件',0,0,'bbbbbb@mymail.com');
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;


--
-- Table structure for table `wellmail`.`mailtag`
--

DROP TABLE IF EXISTS `mailtag`;
CREATE TABLE `mailtag` (
  `tagid` int(11) NOT NULL auto_increment,
  `tagname` varchar(255) default NULL,
  PRIMARY KEY  (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`mailtag`
--

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
  CONSTRAINT `FK5E44A1DB7910B91D` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `FK5E44A1DBFCA753F4` FOREIGN KEY (`notetypeid`) REFERENCES `notetype` (`notetypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

--
-- Dumping data for table `wellmail`.`notebook`
--

/*!40000 ALTER TABLE `notebook` DISABLE KEYS */;
INSERT INTO `notebook` (`noteid`,`notetitle`,`notecontent`,`username`,`notetypeid`) VALUES 
 (1,'note 1','asdfalsdvlaknvpoadfnvoqenirfvqnjgOSZ9 MVsz mv0 NMVA mv MBGFERFGERE','aaaaaa@mymail.com',1),
 (2,'note 2','asdfasdfasdfasdfadvzxcvzxcvzxcv','aaaaaa@mymail.com',1),
 (3,'note 3','asdfasdfascvzxcvzxcvzxcvzxcv','aaaaaa@mymail.com',1),
 (4,'note 4','asdfasdfacccc','aaaaaa@mymail.com',1),
 (5,'note 5','fasdfasdfasdfasdfasd','aaaaaa@mymail.com',2),
 (6,'note 6','nnnnnnnnnnnnmmmmmmmmmmmmmmmgggggggggggtrrrrrrrrrrrrrreeeeeeee','aaaaaa@mymail.com',1),
 (7,'note7','luihhh','aaaaaa@mymail.com',1),
 (8,'note 8','asdflasjdfl;ajsd;flajsdkfja;sdlfk;lasd','aaaaaa@mymail.com',1),
 (9,'note 9','asdfasdf','aaaaaa@mymail.com',1);
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

/*!40000 ALTER TABLE `notetype` DISABLE KEYS */;
INSERT INTO `notetype` (`notetypeid`,`notetypename`,`containfilecount`,`username`) VALUES 
 (1,'普通记事',8,'aaaaaa@mymail.com'),
 (2,'待办记事',1,'aaaaaa@mymail.com'),
 (3,'其他记事',0,'aaaaaa@mymail.com'),
 (4,'普通记事',0,'bbbbbb@mymail.com'),
 (5,'待办记事',0,'bbbbbb@mymail.com'),
 (6,'其他记事',0,'bbbbbb@mymail.com');
/*!40000 ALTER TABLE `notetype` ENABLE KEYS */;


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
INSERT INTO `othermailbox` (`uid`,`uname`,`upass`,`username`,`tid`) VALUES 
 (1,'bauble','severus125874693','aaaaaa@mymail.com',1),
 (2,'bauble','severus125874693','aaaaaa@mymail.com',3),
 (3,'summerisy','severus125874693','aaaaaa@mymail.com',6),
 (4,'bauble','severus125874693','aaaaaa@mymail.com',7),
 (5,'summerisy','severus125874693','aaaaaa@mymail.com',7);
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
-- Dumping data for table `wellmail`.`userpwd`
--

/*!40000 ALTER TABLE `userpwd` DISABLE KEYS */;
INSERT INTO `userpwd` (`qid`,`question`,`answer`,`username`) VALUES 
 (1,'您母亲的生日是?','999ca7d8a1ca3b6c','aaaaaa@mymail.com'),
 (2,'您母亲的生日是?','999ca7d8a1ca3b6c','bbbbbb@mymail.com');
/*!40000 ALTER TABLE `userpwd` ENABLE KEYS */;


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

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`,`pwdHash`,`pwdAlgorithm`,`useForwarding`,`forwardDestination`,`useAlias`,`alias`) VALUES 
 ('aaaaaa@mymail.com','96niR3fsIyEsVNejULxb6lR3','SHA',0,NULL,0,NULL),
 ('bbbbbb@mymail.com','DgPGIF6mcdfUGg46q/ydFdl+','SHA',0,NULL,0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


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

/*!40000 ALTER TABLE `userscore` DISABLE KEYS */;
INSERT INTO `userscore` (`scoreid`,`level`,`score`,`username`) VALUES 
 (1,1,45,'aaaaaa@mymail.com'),
 (2,1,75,'bbbbbb@mymail.com');
/*!40000 ALTER TABLE `userscore` ENABLE KEYS */;


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

/*!40000 ALTER TABLE `usersinfo` DISABLE KEYS */;
INSERT INTO `usersinfo` (`usersinfoid`,`sex`,`birthday`,`tel`,`username`) VALUES 
 (1,'男','1986.12.13','13654685409','aaaaaa@mymail.com'),
 (2,'男','1986.12.13','13612341234','bbbbbb@mymail.com');
/*!40000 ALTER TABLE `usersinfo` ENABLE KEYS */;


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

/*!40000 ALTER TABLE `webdisk` DISABLE KEYS */;
INSERT INTO `webdisk` (`webspaceid`,`webspace`,`wsleft`,`filecount`,`username`) VALUES 
 (1,1024,1023.426,5,'aaaaaa@mymail.com'),
 (2,1024,1023.426,5,'bbbbbb@mymail.com');
/*!40000 ALTER TABLE `webdisk` ENABLE KEYS */;


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
INSERT INTO `webfile` (`fileid`,`filename`,`filesize`,`uploadtime`,`username`,`filetypeid`) VALUES 
 (1,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/JavaMail_结合JSP_实现中文邮件收发系统.pdf',0.045,'2010-06-09 17:26:23','aaaaaa@mymail.com',1),
 (2,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/a.html',0.008,'2010-06-09 17:26:23','aaaaaa@mymail.com',1),
 (3,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/ProgressBar.html',0.002,'2010-06-09 17:26:23','aaaaaa@mymail.com',1),
 (4,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/JavaMail开发手册.pdf',0.436,'2010-06-09 17:26:24','aaaaaa@mymail.com',1),
 (5,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/126对话框.rar',0.083,'2010-06-09 17:26:24','aaaaaa@mymail.com',1);
/*!40000 ALTER TABLE `webfile` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
