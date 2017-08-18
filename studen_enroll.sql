-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: student_enroll_db
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` varchar(36) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `streetName` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `zipCode` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('417d4a21-eafc-437b-992d-97d444738c37','Arlington',NULL,'TX','312 UTA BLVD','Apt 208',76010);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` varchar(36) NOT NULL,
  `courseNo` int(11) NOT NULL,
  `course_title` varchar(255) NOT NULL,
  `days` varchar(255) NOT NULL,
  `end_time` varchar(255) NOT NULL,
  `enrolled` int(11) NOT NULL,
  `instructor` varchar(255) NOT NULL,
  `max` int(11) NOT NULL,
  `section` int(11) NOT NULL,
  `semesterId` int(11) NOT NULL,
  `start_time` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('3854d70e-a478-4215-a7d7-2a18568fb5f3',5351,'Parallel Processing','T','9:50 PM',50,'Ahmad',50,1,1,'7:00 PM','CSE'),('d1321c59-08e9-4e2c-b1fe-f960fa5d275f',5325,'Sfwr Eng II','R','8:20 PM',34,'Amaravadi',45,2,1,'7:00 PM','CSE'),('d43c5f33-b94d-4986-bd85-562057aee805',6363,'Machine Learning','MWF','1:50pm',58,'Athitsos',80,1,1,'1:00 PM','CSE'),('523febff-e1b5-44d7-a0b3-33b31a140259',3320,'Operating Systems','MW','8:20 PM',45,'Bakker',45,1,1,'7:00 PM','CSE'),('2358135e-8ffa-43e9-ba22-3533a9ef2016',3315,'Theoretical Concepts','TR','12:20 PM',43,'Barasch',45,1,1,'11:00 AM','CSE'),('c0fe11e8-b236-4bf8-a48e-24e72a5dfba1',3315,'Theoretical Concepts','TR','3:20 PM',45,'Barasch',45,2,1,'2:00 PM','CSE'),('ce81653b-b899-456c-b4d3-e60f0b3e90ca',4191,'Individual Projectis','TBA','12:00 AM',0,'Barasch',10,1,1,'12:00 AM','CSE'),('e461fe4b-9dc9-40c3-8d86-ea99d4d1f69a',4391,'Individual Projectis','TBA','12:00 AM',3,'Barasch',10,1,1,'12:00 AM','CSE'),('d1d4a8d5-cadc-4311-bfce-7a99e71ab62a',2315,'Discrete Structures','MW','5:20 PM',44,'Biswas',45,1,1,'4:00 PM','CSE'),('ddc23c93-b34f-486b-83e7-7dc862a9f46f',2315,'Discrete Structures','MW','6:50 PM',43,'Biswas',50,4,1,'5:30 PM','CSE'),('078e3877-1664-4b11-a0a9-dde784e34204',2315,'Discrete Structures','MW','8:20 PM',17,'Biswas',45,5,1,'7:00 PM','CSE'),('eeb668fc-a4f8-4824-a840-55115b088b04',2312,'Computer Org & Assemb Lang','MWF','11:50 AM',45,'Brezeale',45,2,1,'11:00 AM','CSE'),('916ae63f-7b15-48e9-80e5-3ff1ad928656',3313,'Intro to Signal Processing','TR','4:50 PM',45,'Brezeale',50,1,1,'3:30 PM','CSE'),('01e09e85-597a-4599-a2e2-d37e739ad5e9',3380,'Linear Algebra','TR','12:20 PM',55,'Brezeale',55,1,1,'11:00 AM','CSE'),('575d61d1-e4bc-489a-98ca-b6896a4b5598',3442,'Embedded Systems I','TR','1:50 PM',14,'Burns',30,1,1,'12:30 PM','CSE'),('95619ce2-3b39-47af-ab77-b9c653d935b1',3442,'Embedded Systems I Lab','T','04;50 pm',1,'Burns',15,2,1,'2:00 PM','CSE'),('671097ee-f70e-449d-9fa2-5f35a1de64b7',3442,'Embedded Systems I Lab','W','3:50 PM',13,'Burns',15,3,1,'1:00 PM','CSE'),('2c2e6edf-a58c-4003-85ca-e8d4b1819b1e',2441,'Intro to Digital Logic','TR','12:20 PM',19,'Carroll',24,1,1,'11:00 AM','CSE'),('33b420af-ebe4-48da-996b-cbaf882f30ca',2441,'Intro to Digital Logic Lab','F','4:50 PM',9,'Carroll',15,2,1,'2:00 PM','CSE'),('8a208a9c-4e48-4c9c-a0d6-a091d249b39e',2441,'Intro to Digital Logic Lab','F','11:50 AM',10,'Carroll',12,3,1,'9:00 AM','CSE'),('22573cc7-9f51-4a30-8207-bb4bc684f92a',4323,'Quantitative Computer Arch','TR','3:20 PM',30,'Carroll',30,1,1,'2:00 PM','CSE'),('35521301-62a3-43bf-b6f8-e87337291eb1',6331,'Adv Tops Database ','TR','10:50 AM',19,'Chakravarthy',30,3,1,'9:30 AM','CSE'),('8c3cb5b6-85b2-4ca2-92e1-684723c356a9',6349,'Spec Tops Adv Networks','TR','01;50 pm',40,'Che',40,1,1,'12:30 PM','CSE'),('bc0fa299-7e44-43e1-bcc1-173c8569616b',1310,'Intro Computers & Programming','TR ','3:20 PM',45,'Conly',45,2,1,'2:00 PM','CSE'),('13977cc1-607a-4b45-acb9-d58e460e0540',4317,'Comptuer System Dsgn II','TR','12:20 PM',23,'Conly',30,5,1,'11:00 AM','CSE'),('7d18ce01-9645-4a4b-b70d-22fcfab0a76b',4317,'Computer System Dsgn II','F','11:50 AM',23,'Conly',30,6,1,'10:00 AM','CSE'),('fdb01f42-e3ac-4b36-ae84-d7f40177a490',4317,'Computer System Dsgn II','TR','10:50 AM',27,'Conly',30,3,1,'9:30 AM','CSE'),('8fe66be3-5c79-4a84-b91d-6f3a1e16fa7d',4317,'Computer system Dsgn II','F','2:00 PM',27,'Conly',30,4,1,'1:00 PM','CSE'),('1c97952f-6b5e-4c28-bbe8-10f25f1886d8',4314,'Professional Practices','TR','10:50 AM',38,'Cross',40,1,1,'9:30 AM','CSE'),('c70c468e-7662-4157-85ff-0c57321eaa31',4314,'Professional Practices','TR','12:20 PM',18,'Cross',40,2,1,'11:00 AM','CSE'),('6c8cd3b8-f971-4eed-8779-db887d16e027',4322,'Software Project Management','TR','3:20 PM',55,'Cross',60,1,1,'2:00 PM','CSE'),('66e3fa6e-036d-49fb-8602-de83bf1f830a',3311,'Object-Oriented Sftw Eng','TR','12:20 PM',15,'Csallner',40,1,1,'11:00 AM','CSE'),('ef1a46a1-305d-4d29-b68e-bc9895492a3e',6324,'Adv Tops Software Eng','TR','3:20 PM',9,'Csallner',30,2,1,'2:00 PM','CSE'),('8da7a933-4da6-4a24-8923-bf0852769e0e',5311,'Algorithms  ','TR','1:50 PM',42,'Das',40,2,1,'12:30 PM','CSE'),('e529a38e-b42c-4fe7-b10f-23859e41836d',6311,'Advanced Algorithms','TR','4:50 PM',30,'Das',30,1,1,'3:30 PM','CSE'),('0c898894-d613-46db-aacf-54667ef748e3',5330,'Database Systems','S','3:50 PM',40,'Dash',40,1,1,'1:00 PM','CSE'),('e8020c37-f8b2-4ad3-922c-b0d53bcad228',1320,'intermediate Programming','MW','3:50 PM',45,'Datta',45,1,1,'2:30 PM','CSE'),('661fee15-ee79-4d9d-9cc8-7507a2987187',1320,'intermediate Programming','TR','1:50 PM',45,'Datta',45,2,1,'12:30 PM','CSE'),('317f6d20-6f62-4fd5-a6ce-9ed974898f04',1320,'intermediate Programming','TR','3:20 PM',46,'Datta',45,4,1,'2:00 PM','CSE'),('d5a56c24-584a-47f3-be46-31a58f3b89d1',4344,'Computer Network Organization','MW','3:20 PM',41,'Datta',45,2,1,'2:00 PM','CSE'),('40f555f1-666f-4d1a-91b6-b5547498b0f9',3310,'Fundamentals of SFWR Eng','MW','6:50 PM',45,'Davis',45,2,1,'5:30 PM','CSE'),('bd76cd48-8567-4eb8-9ffa-cdc2b2e19051',4334,'Data Mining','TR','10:50 AM',45,'Diaz',45,1,1,'9:30 AM','CSE'),('7d7e5b13-ee09-47ac-838c-d2307d48e3a2',5320,'Spec Topics Sofware Eng','W','6:50 PM',27,'Diaz',40,1,1,'4:00 PM','CSE'),('b4355ad2-c567-45fe-9eeb-a86daebb2c9d',5334,'Data Mining','TR','12:20 PM',45,'Diaz',45,2,1,'11:00 AM','CSE'),('e856a99f-2e0a-4c4c-8a55-ff742316fecc',5335,'Web Data Management','T','4:50 PM',45,'Diaz',45,2,1,'2:00 PM','CSE'),('9632e4ac-1447-4ee8-9138-80af0ba26ad9',5334,'Data Mining','T','9:50 PM',40,'Ding',40,1,1,'7:00 PM','CSE'),('94214af9-4597-492c-b056-f4bdb891969b',1105,'Intro to Computer Science & Eng','M','11:50 AM',71,'Eary',100,1,1,'11:00 AM','CSE'),('5e539792-52ce-477b-83c0-70406fed6a5e',1105,'Intro to Computer Science & Eng','W','11:50 AM',78,'Eary',100,2,1,'11:00 AM','CSE'),('20a69b17-d250-49b4-9752-7868c35af4f9',1105,'Intro to Computer Science & Eng','F','11:50 AM',57,'Eary',100,3,1,'11:00 AM','CSE'),('6015ad9e-bf50-4089-b86c-034316300221',5306,'Distributed Systems','TR','12:20 PM',40,'Eary',40,1,1,'11:00 AM','CSE'),('03450f59-6e07-4f3d-86a4-2587e70d4b49',5306,'Distributed Systems','TR','3:20 PM',27,'Eary',40,4,1,'2:00 PM','CSE'),('39257b6d-bb26-4a1e-a8b7-ccb549622c4f',5191,'Individual Study','TBA','TBA',17,'Elmasri',50,1,1,'TBA','CSE'),('22dbd889-31dc-416a-86d9-a08be2e6dec6',6331,'Adv Tops Database ','MW','6:50 PM',33,'Elmasri',35,2,1,'5:30 PM','CSE'),('7e02d638-b729-406a-9bee-b6de626540bf',4305,'Compilers','TR','3:20 PM',8,'Fegaras',20,1,1,'2:00 PM','CSE'),('a518868d-f584-40dc-8857-93d05fc677bf',5317,'Compilers','TR','3:20 PM',23,'Fegaras',25,1,1,'2:00 PM','CSE'),('4ed6e9ef-bd9d-442a-a255-eb3637ac270b',5335,'Web Data Management','TR','6:50 PM',45,'Fegaras',45,1,1,'5:30 PM','CSE'),('912fe397-685e-4bcb-91c0-3e08ffd9738f',6329,'Spec Tops Adv Sftwr Eng','F','3:50 PM',35,'Frailey',35,2,1,'1:00 PM','CSE'),('a0c63b6e-c866-4e8b-a9ce-39169c6b6274',3302,'Programming Languages','W','6:50 PM',48,'French',48,1,1,'4:00 PM','CSE'),('b527f7fc-0fcc-49e0-9bfe-39f20959e0d5',2315,'Discrete Structures','TR','12:20 PM',45,'Gao',45,2,1,'11:00 AM','CSE'),('a399dbc5-0379-4c2d-b465-89aa712d5e90',2315,'Discrete Structures','TR','3:20 PM',45,'Gao',45,3,1,'2:00 PM','CSE'),('7c9de1f5-47dc-4395-abcd-e73267ca8a04',5311,'Algorithms','TR','6:50 PM',40,'Ghidini',40,3,1,'5:30 PM','CSE'),('27e96530-5839-40dc-9e6b-4958fd896802',5311,'Algorithms','TR','08:20pm',37,'Ghidini',40,4,1,'7:00 PM','CSE'),('35badd18-4afc-490a-8a8b-5c9709188a53',6389,'Adv NM, Graphics, Image Proc','M ','6:50 PM',10,'H. Huang',30,1,1,'4:00 PM','CSE'),('50ae8848-db8c-459d-a995-1260a33d33a9',4303,'Computer Graphics','MW','5:20 PM',30,'Hayes',30,1,1,'4:00 PM','CSE'),('4703abc1-7eda-47eb-9722-fae807b9480b',5365,'Computer Graphics','MW','5:20 PM',3,'Hayes',5,1,1,'4:00 PM','CSE'),('ec874aa3-7cda-494a-834e-1772263dc61e',4379,'Unmanned Vehicle','M','5:50 PM',1,'Huber',10,1,1,'3:00 PM','CSE'),('52233df8-79a6-4331-9843-61e687622a23',4391,'Individual Projectis','TR','4:50 PM',3,'Huber',3,3,1,'3:30 PM','CSE'),('ecdddb36-8183-44ee-b5e4-6cb3f28226be',5301,'Data Analysis & Modeling','TR','4:50 PM',54,'Huber',60,1,1,'3:30','CSE'),('e58f05cc-c9eb-49b7-a9b5-00081409dbdb',5384,'Unmanned Vehicle','M','5:50 PM',2,'Huber',15,1,1,'3:00 PM','CSE'),('b8f50f24-1319-481b-94b5-444bbc9d09b5',4391,'Individual Projectis','F','6:50 PM',1,'J. Huang',1,6,1,'4:00 PM','CSE'),('74579e5a-6a5e-42a0-aedf-9b761b26eac4',6392,'Adv Special Topics','F','6:50 PM',29,'J. Huang',30,1,1,'4:00 PM','CSE'),('1a0d666c-cf11-4613-9e88-fa92d6df1fad',5382,'Secure Programming','F','04;50 pm',40,'Jones',40,1,1,'2:00 PM','CSE'),('ee682d26-413c-4f04-ae36-d5cf4aca4c26',6367,'Computer Vision','W','9:50 PM',24,'Kamangar',40,1,1,'7:00 PM','CSE'),('15558100-fc17-47fe-b52c-281342e6d8f3',1301,'Computer Literacy','MW','5:20 PM',13,'Kashefi',60,1,1,'4:00 PM','CSE'),('be365194-faf7-4fac-8212-5313353d67cb',1301,'Computer Literacy Lab','M','8:20 PM',7,'Kashefi',20,3,1,'5:30 PM','CSE'),('dfcd384b-bcc0-4724-94bd-de131744eea2',1301,'Computer Literacy Lab','W','9:50 PM',6,'Kashefi',20,4,1,'7:00 PM','CSE'),('fe75c8e9-6a2c-4f00-a590-5a415edbacec',5344,'Computer Networks ','TR','4:50 PM',21,'Kashefi',45,1,1,'3:30 PM','CSE'),('40dee8f6-369c-4367-b1ec-b404a38c91f4',5344,'Computer Networks ','TR','3:20 PM',6,'Kashefi',50,2,1,'2:00 PM','CSE'),('a9090653-f6fd-4198-8a1a-eb4c2f12e664',3310,'Fundamentals of SFWR Eng','TR','1:50 PM',45,'Khalili',45,1,1,'12:30 PM','CSE'),('867326f1-bec8-4088-b0b4-1bea1ae7d6b7',5325,'Sfwr Eng II','TR','3:20 PM',20,'Khalili',45,1,1,'2:00 PM','CSE'),('215078bd-817a-46e9-bbf3-b6c8eb1b6fb5',5335,'Web Data Management','BUPT','',0,'Khalili',9,20,1,'','CSE'),('c9ce7b73-8f49-4418-978e-352e1e44ffa2',6324,'Adv Tops Software Eng','TR','10:50 AM',25,'Khalili',30,1,1,'9:30 AM','CSE'),('645c2a59-08be-4933-8719-22ab5c653e37',6324,'Adv Tops Software Eng','TR','10:50 AM',10,'Khalili',9,22,1,'9:30 AM','CSE'),('2b80180c-d6aa-440e-9380-3aa5deb03418',3311,'Object-Oriented Sftw Eng','TR','4:50 PM',45,'Kung',45,1,1,'3:30 PM','CSE'),('e027453b-6304-4ffd-9335-481f8989a5b3',5328,'Sfwr Eng Team Project','TR','6:50pm',10,'Kung',30,1,1,'5:30 PM','CSE'),('19d709ac-991f-4179-b1b4-1fb2ab032645',1311,'Intro to Programming Engrs','TR','6:50 PM',58,'Laleh',80,2,1,'5:30 PM','CSE'),('7c0066f1-1316-4bc7-a0ee-6d0693de7571',4321,'Software Testing','TR','12:20 PM',37,'Lei',47,1,1,'11:00 AM','CSE'),('b1e401dd-85b6-46a2-afb9-0701861d30f5',4392,'Special Topics','MW','6:50 PM',10,'levine',10,1,1,'5:30 PM','CSE'),('14070bd4-607d-456a-8a11-73a56f1357f6',5381,'Information Security II','MW','6:50 PM',24,'Levine',30,1,1,'5:30 PM','CSE'),('6a70c755-22f2-41ca-91f5-0f4ec699844a',6331,'Adv Tops Database ','MW','5:20 PM',35,'Levine',35,1,1,'4:00 PM','CSE'),('2108a5a4-f4b7-4871-9481-91eddc35809e',6331,'Adv Tops Database ','MW','3:50 PM',40,'Levine',40,4,1,'2:30 PM','CSE'),('5d5d73c8-ba92-4f5f-af71-6ce367a33633',4380,'Information Security','MW','5:20 PM',36,'Lloyd',45,1,1,'4:00 PM','CSE'),('271e0e64-b670-4d64-be66-a71117e344ef',4380,'Information Security Lab','W','10:00 PM',15,'Lloyd',18,2,1,'7:00 PM','CSE'),('90b10f93-1a4c-48db-bc0b-b050d00ea29e',4380,'Information Security Lab','M','8:30 PM',18,'Lloyd',18,3,1,'5:30 PM','CSE'),('12693d98-a3b6-42c9-8941-8a6cfe6a6cf7',4380,'Information Security Lab','F','3:20 PM',3,'Lloyd',9,4,1,'12:30 PM','CSE'),('999a1820-9fd1-444b-8c79-80d30bb7b8e2',5369,'Intelligent Systems','TR','3:20 PM',8,'Makedon',12,1,1,'2:00 PM','CSE'),('fb11f124-c349-4ad3-95bd-e59aab4ed943',6369,'Spec Tops Adv Intelligent Sys','TR','3:20 PM',9,'Makedon',12,2,1,'2:00 PM','CSE'),('dc0b1551-1718-4826-97b9-42020104dc28',2312,'Computer Org & Assemb Lang','MW','5:20 PM',45,'McMurrough',45,1,1,'4:00 PM','CSE'),('d902d4df-141d-4dee-b70a-88372a99fe00',4316,'Computer System Dsgn I','MW','11:50 AM',30,'McMurrough',30,1,1,'11:00 AM','CSE'),('4afe9b1a-c4de-467d-bbc3-156b05c06753',4316,'Computer System Dsgn I','F','10:50 AM',30,'McMurrough',30,2,1,'9:00 AM','CSE'),('08579c70-9927-406f-a876-d6e224dd81d9',4317,'Computer System Dsgn II','TR','10:50 AM',30,'McMurrough',30,1,1,'9:30 AM','CSE'),('bfdf1d6b-4dd4-48db-96c9-1733d7d9d79c',4317,'Computer System Dsgn II','F','12:00 PM',30,'McMurrough',30,2,1,'11:00 AM','CSE'),('8a9d74fa-816a-4d23-952d-38bd71aa2b1b',5380,'Information Security I','F','6:50 PM',45,'Ming',45,1,1,'4:00 PM','CSE'),('f9df6ad0-4f5a-407d-8457-5505c568a08d',5380,'Information Security I lab','T','10:00 PM',18,'Ming',18,2,1,'7:00 PM','CSE'),('71ba54c1-45a3-449f-8b07-1264cc677be3',5380,'Information Security I lab','R','8:30 PM',18,'Ming',18,3,1,'5:30 PM','CSE'),('76b8f2e5-4bd4-463d-8ea1-19375f446354',5380,'Information Security I lab','F','3:20 PM',9,'Ming',9,4,1,'12:30 PM','CSE'),('4f1cb663-3757-42bd-bb61-17c11756b62b',1310,'Intro Computers & Programming','MW','8:20 PM',45,'Moore',45,1,1,'7:00 PM','CSE'),('526dfc48-0567-48fd-9608-ad9bddfc591a',5306,'Distributed Systems','MWF','11:50 AM',41,'Morgan',40,2,1,'11:00 AM','CSE'),('095c0410-15d4-4614-9e98-ed4afcdb9d54',5306,'Distributed Systems','MWF','10:50 AM',40,'Morgan',40,3,1,'10:00 AM','CSE'),('6f721c83-00bf-4cc2-abb8-24ad04209950',5345,'Fundamentals of Wireless','R','8:20 PM',39,'Mufti',40,1,1,'5:30 PM','CSE'),('8d0980da-087f-4d77-9a69-141426178695',3330,'Database Sys & File Structures','TR','8:20 PM',45,'Park',45,2,1,'7:00 PM','CSE'),('0367dc7f-f132-44a6-89f5-41d9320f258b',1310,'Intro Computers & Programming','TR','1:50 PM',45,'Qudah',45,3,1,'12:30 PM','CSE'),('5f237fff-09b4-41f3-a9c3-c7696c3f1f45',3320,'Operating Systems','TR','3:20 PM',45,'Rao',45,2,1,'2:00 PM','CSE'),('b718ce08-931f-4e7a-885f-4370295f08de',1325,'Object Oriented Programming','TR','1:50 PM',40,'Rice',40,1,1,'12:30 PM','CSE'),('8b5c78f5-9012-4984-9272-eb919bb44482',1325,'Object Oriented Programming','TR','9:20 AM',22,'Rice',40,2,1,'8:00 AM','CSE'),('5b548c0b-c6f5-40ed-a2c5-860234371d99',1325,'Object Oriented Programming','TR','10:50 AM',34,'Rice',40,3,1,'9:30 AM','CSE'),('a698aafe-e2c5-47ad-b3e4-a631a29d6013',4321,'Software Testing','TR','4:50 PM',22,'Robb',20,2,1,'3:30','CSE'),('67060198-6348-48ee-bc80-736a6a3e268c',5321,'Software Testing','TR','4:50 PM',18,'Robb',43,1,1,'3:30 PM','CSE'),('f2493668-6a30-4014-aad3-07181300eff1',5321,'Software Testing','TR','3:20 PM',45,'Robb',45,2,1,'2:00 PM','CSE'),('c2318f8a-563c-4387-9ff3-09f43ab9d6b1',5324,'Sftw Eng Anly, Dsgn, Testing','TR','12:20 PM',37,'Robb',40,2,1,'11:00 AM','CSE'),('e845eebb-b0e6-4815-8dc5-47af4426d5ec',5324,'Sftw Eng Anly, Dsgn, Testing','TR','10:30 AM',15,'Robb',40,3,1,'9:30 AM','CSE'),('38b82873-8340-4c08-b4e0-3d3f336229d1',2312,'Computer Org & Assemb Lang','MW','5:20 PM',13,'S. Jiang',45,3,1,'4:00 PM','CSE'),('4c901ae3-10b3-4bef-a6eb-2b5f9133f7c8',6329,'Spec Tops Adv Sftwr Eng','T','8:20 PM',16,'Sahoo',35,1,1,'5:30 PM','CSE'),('76a0c478-59e7-43cb-a3e5-d989dd50e22f',3330,'Database Sys & File Structures','R','1:50 PM',31,'Saleh',45,1,1,'11:00 AM','CSE'),('9dcdc364-703b-47f6-90a7-1173ced47035',1320,'intermediate Programming','MW','5:20 PM',30,'Salemizadeh',45,3,1,'4:00 PM','CSE'),('e5fa240c-9d1a-437e-bab5-089a989fca42',2320,'Algorithms & Data Structures','TR','10:50 AM',40,'Stefan',45,1,1,'9:30 AM','CSE'),('fe740136-9af8-4f24-8527-f874b5ced300',2320,'Algorithms & Data Structures','TR','12:20 PM',22,'Stefan',45,2,1,'11:00 PM','CSE'),('934f50ca-42d5-469a-b29f-f0e7164072b6',1310,'Intro Computers & Programming','TR','4:50 PM',86,'Tiernan',160,5,1,'3:30 PM','CSE'),('112602e8-94f4-4f34-991f-aa4d598df2ed',1311,'Intro to Programming Engrs','MW ','5:20 PM',80,'tripathi',80,1,1,'4:00 PM','CSE'),('f94fa98b-99d4-4a5d-849b-2c949c44875b',1320,'intermediate Programming','TR','10:50 AM',42,'tripathi',45,5,1,'9:30 AM','CSE'),('1aab04f1-5382-4e28-9e1b-fe187e31a6a3',1320,'intermediate Programming','TR','12:20 PM',22,'tripathi',45,6,1,'11:00 AM','CSE'),('4307fc0d-8c29-46f9-928b-a3cc4d2981af',5330,'Database Systems','MW','6:50 PM',45,'tripathi',45,2,1,'5:30 PM','CSE'),('5dad70d6-7e36-4fc2-990c-309a37832a9c',4342,'Embedded II','TR','1:50 PM',17,'Walker',20,1,1,'12:30 PM','CSE'),('e9684925-6cc4-496f-b813-922145062f9e',2320,'Algorithms & Data Structures','TR','4:50 PM',45,'Weems',45,3,1,'3:30 PM','CSE'),('a561e7d9-ce7d-4856-a27c-813eaf2693e7',5311,'Algorithms','TR','8:20 PM',3,'Weems',40,5,1,'7:00 PM','CSE'),('d8541af2-ad2c-4fcf-b72a-9fbcf5f6101e',4391,'Individual Projectis','TR','8:20 PM',1,'Weems',1,5,1,'7:00 PM','CSE'),('e17a781c-3623-4e5f-bc18-8de8dcfa9172',4344,'Computer Network Organization','TR','12:20 PM',48,'Y. Liu',48,1,1,'11:00 AM','CSE'),('79ed6a4a-1b59-4a58-a5c2-73f93e0a980e',5344,'Computer Networks ','BUPT','',0,'Yonghe',9,20,1,'','CSE'),('cd112232-8cab-4e0b-aa85-ae6266a49039',2100,'Practical Computer Systems Lab','W','7:30 PM',25,'ZARUBA',25,1,1,'5:30 PM','CSE'),('4fd78367-b4eb-41b4-8d01-02859fa633f5',2100,'Practical Computer Systems Lab','W','7:30 PM',25,'ZARUBA',25,2,1,'5:30 PM','CSE'),('2870b819-f41c-4423-8d22-d915bf9f7717',2100,'Practical Computer Systems Lab','R','7:30 PM',25,'ZARUBA',25,3,1,'5:30 PM','CSE'),('6501dd04-b68d-4ff1-b8cb-2070a4fbfe69',2100,'Practical Computer Systems Lab','R','8:30 PM',23,'ZARUBA',25,4,1,'5:30 PM','CSE');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uuid` varchar(36) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `isFaculty` bit(1) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UK_e6gkqunxajvyxl5uctpl2vl2p` (`email`),
  UNIQUE KEY `UK_5ir1yd4k8cjxl4oaeksk9mu3a` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-02 12:37:03
