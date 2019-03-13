# library-database
A simple library database. Uses a Java interface to interact with MySQL.

It is important to have the library database set up in MySQL beforehand. Also make sure to change the username and password for each file when connecting to the database. 
In each file the connection is defined like so: Connection conn = DriverManager.getConnection("jdbc:mysql://localhost", <insert_user>, <insert_password>);

Make sure to have the correct MySQL drivers and connectors downloaded. 
Connector used: mysql-connector-java-8.0.15.jar
Driver used: com.mysql.cj.jdbc.Driver
