# Student_Enroll_Microservices
Java Spring Backend 

# Welcome

1. To be very abstract, this project serves as a  backend to any of the student course enrollment systems.
2. When I started this , it had its implementation in Python ,later I ported it to Java (used Spring MVC) and it currently runs as a Spring Boot Application
3. It has many sub-modules listed and each runs as a seperate service. The ApiGateway module acts as a single point entry to communicate with rest of the modules.
4. To get started with this project , clone and import as a maven project
5. User-service and course-service uses mysql database.Set up your own credentials connection in bootstrap.properties located in main->resources.
6. Dump file for data is attached (student_enroll.sql) .
7. Now it is getting bigger daily and there are about 3 people including me who are actively making changes.

# Future:
 I have deployed two modules of this project seperately in AWS ECR, using docker. I had to take those down as Amazon started charging me. Now, recently I have moved on to Spring cloud and still haven't figured how and which of deployment 
  1. I have only run it locally , have to integrate with any continuous integration( Mostly Travis) deploy automatically.
  
  

## What are Used:
  i.Java 8
  ii. Spring Cloud,Spring boot, Hibernate,mysql,h2
  iii. Docker
  iv. Travis
  v. NetFlix API
  
