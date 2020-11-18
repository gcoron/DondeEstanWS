# DondeEstanWS
Web Service DondeEstan

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Test](#installation)
5. [Author](#author)

### General Info
***
Web Service Rest developed for communication between the database and the Android mobile application "DondeEstan". This project presents a client-server architecture and a REST architecture that allows the sending of data, in JSON format, to the mobile application. The web service is running in a local environment, having a URL to access it through port 8081 of the personal computer, which allows the application of the hypertext transfer protocol (HTTP). The MVC model was followed for the division of packages.

## Technologies
***
List of technologies used within the project:
* [IDE Java (Integrated Development Environment): Eclipse](https://www.eclipse.org/eclipseide/): Version 2019-12 (4.14.0) 
* [MySQL](https://dev.mysql.com/doc/relnotes/mysql/8.0/en/): Version 8.0
* [Apache Tomcat v9.0 Server.](https://tomcat.apache.org/tomcat-9.0-doc/index.html): Version 9.0
* [Apache Maven Project](https://maven.apache.org/index.html): Version 4.0.0
* [Hibernate ORM](https://hibernate.org/orm/releases/5.4/): Version 5.4.21.Final
* [Spring Framework](https://spring.io/projects/spring-framework): Version 5.2.8.Release
* [Postman](https://learning.postman.com/docs/getting-started/introduction/): Version 7.35.0

## Installation
***
After installing the tools mentioned above, we proceed to create the database by executing the script found inside the DB folder. 
```
$ git clone https://github.com/gcoron/DondeEstanWS.git
$ mysql -u root -p
mysql> source DB/DondeEstanAppDB.sql
```
Next, we copy the WebService/DondeEstanApp/DondeEstanApp.war file into the apache_tomcat/webapps folder. Then we turn on the server.

## Test
Finally, we execute in Postman the requests supported by the web service. For example:
```
http://localhost:8081/DondeEstanApp/wsuo/usersObservees

http://localhost:8081/DondeEstanApp/wsuor/observersUsers

http://localhost:8081/DondeEstanApp/wsu/userLogin?username=user&password=-2sie7%4-kDo
```

## Author

* **Germán Coronel** - [gcoron](https://github.com/gcoron)
