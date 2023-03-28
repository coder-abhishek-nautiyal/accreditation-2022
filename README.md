# accreditation-2022
This is Learning Management System - Case Study which is part of accreditation 20222.

Below is the end point for api gateway - 
http://localhost:9999/

Below is the end point for eureka via api gateway - 
http://localhost:9999/eureka/web

Below is the end point for login to course system via gateway-
http://localhost:9999/api/v/1.0/lms/courses/login

For registration of user , login and check authentication from user service below are the api -
http://localhost:9999/api/v/1.0/lms/company/login
http://localhost:9999/api/v/1.0/lms/company/register
http://localhost:9999/api/v/1.0/lms/company/refreshtoken
http://localhost:9999/api/v/1.0/lms/company/adminTokenTest

Below is the end point for course service via api gateway - 
http://localhost:9999/api/v/1.0/lms/courses/getall

Below is the end point for course service without api gateway - 
http://localhost:9998/api/v/1.0/lms/courses/getall

Below is the end point for course service swagger ui - 
http://localhost:9998/swagger-ui/

Below is to get course service config properties from config service - 
http://localhost:9997/course-service/default

Below is the end point to call Course Service Config from Api Gateway-
http://localhost:9999/course-service/default

Below is the end point to validate userTokenTest with Bearer Token of User Role -
http://localhost:9996/api/v/1.0/lms/company/userTokenTest

Below is the end point to validate userTokenTest with Bearer Token of User Role -
http://localhost:9996/api/v/1.0/lms/company/adminTokenTest

Below is to get user service config properties from config service -
http://localhost:9997/user-service/default

Below is the end point to call User Service Config from Api Gateway-
http://localhost:9999/user-service/default

Below is the end point for user service swagger ui -
http://localhost:9996/swagger-ui/

###########################

Steps to upload UI code to S3 - 
execute ng build command to build angular app , dist folder will be created
Open AWS S3 and Create bucket - In object ownership select ACLs Enabled
Uncheck - "Block Public Access settings for this bucket" and acknowledge it
and click on Create Bucket
Open AWS S3 - select and add all files from dist folder 
go to permission make it public and acknowledge taking risk and click upload 
Open S3 - navigate to properties tab 
navigate at the end there is option of static website hoisting 
enable it and in error and index both enter index.html
nothing to enter in redirection rules - save it 
You will get static web hoisting url 

###############

Steps to create RDS database in AWS -
Search RDS create database and select MYSQL
Select template as free tier 
Select public access yes 
Once database is created copy end point and start consuming with port , user credentials and db name
if not able to connect to RDS AWS connection then issue with security group or port
open security group in new window of rds , check inbound rules
do all traffic rules and select source as anywhere IPV4 and save rules
then try connecting from aws console which they provide
it will take some time to create instance
mysql -v
mysql -h enterEndPoint -u enterUserName -p enterPassword
it should connect
show databases;
if we need to change port simply modify and change


###############

Steps to create Elastic Beanstalk in AWS - 
Search for Elastic beanstalk and enter application name - 
example service-registry
Select platform as Java and Platform Branch as Corretto 11 and keep version as it is 
In Application code - Upload your code from local 
Add version label so that next time another version can be referred if issue with this file
service-registry-sourcev1 - like this is example 
Click on configure more options if Database needs to be mentioned for Elastic Beanstalk
click on create application
Note it is expected that port number should be 5000 - imp

###############

Steps to create EC2 -- 
Create instance , enter name 
Select free tier AMI from catalog - Amazon Linux 2 AMI (HVM) - Kernel 5.10, SSD Volume Type
enter key pair name -
In Firewall (security groups)  Info - select Allow HTTPS traffic from the internet and Allow HTTP traffic from the internet
Click on launch instance 
go to instance - and navigate to Security tab 
go to security group and Edit inbound rule - 
and add - all traffic , mysql/aurora and all tcp all with anywhere ipv4 
if mongo also there then add dynamo db -
Connect to EC2 instance , 
If we go with SSH Client connect , enter the command where we have downloaded that key-value pair file 
else we can go with EC2 instance connect 
execute below command to install jdk 11
sudo amazon-linux-extras install java-openjdk11
once installed - check java --version
now upload jar to s3 and execute below command in ec2 instance 
wget objectUrlToEntered
enter command ls to see all files 
enter command rm fileName to remove file 
to start jar file execute command java -jar jarname

if property values needs to be change do below --
java -jar -Dspring.cloud.config.uri=http://13.50.231.248:9997  user-service-0.0.1-SNAPSHOT.jar
this will ovverirde property value -- imp 

if we want that jar should run in background then & at end add like below 
java -jar -Dspring.cloud.config.uri=http://13.50.231.248:9997  user-service-0.0.1-SNAPSHOT.jar &

Below command to pid and kill it
fuser 9996/tcp -- this is to find pid
kill -9 pidNumber 

The moment ec2 instance is terminated or stopped once it is started again - ip will change ..

try nohup java -jar "jar file name" &
above command will execute jar event in background when terminal is closed

nohup java -jar config-service-0.0.1-SNAPSHOT.jar &

sudo netstat -t
above command is to know active tcp port running

tail -f nohup.out 
above command is to see logs from nohup file 

#############

Regarding EC2 deployment - 
Create two EC2 instance - in one instance deploy service registry , api gateway and config service 
in second instance deploy user service and course service 

Execute below command in 1st EC2 instance -
nohup java -jar service-registry-2.7.0.jar &
nohup java -jar api-gateway-2.7.0.jar &
nohup java -jar config-service-0.0.1-SNAPSHOT.jar &

Execute below command in 2nd EC2 instance - note IP below will be of 1st EC2 instance 
nohup java -jar -Dspring.cloud.config.uri=http://13.53.148.38:9997 -Deureka.client.serviceUrl.defaultZone=http://13.53.148.38:8761/eureka user-service-0.0.1-SNAPSHOT.jar &
nohup java -jar -DloginBaseUrl=http://13.53.148.38:9999/api/v/1.0/lms/company/login -Dspring.cloud.config.uri=http://13.53.148.38:9997 -Deureka.client.serviceUrl.defaultZone=http://13.53.148.38:8761/eureka course-service-0.0.1-SNAPSHOT.jar &

To see logs execute - 
tail -f nohup.out

Execute below command to see port occupied along with PID 
sudo netstat -tunlp
for killing any PID 
kill -9 pidNumber 
to find PID based on specific port execute below command -
fuser 9996/tcp -- this is to find pid 

#############
Note every time EC2 instance is stopped and started again - need to perform below things if elastic ip is not alloted
1- Login to EC2 instance service 1 - start service registry , api gateway and config service 
2 - Login to EC2 instance service 2 - start user service and course service - make sure new IP of EC2 instance 1 is entered
3 - Change IP in environment.prod.ts in lms-app and upload in S3 .

if elastic ip is allotted then simply start and stop EC2 instance , 
Execute below command in 2nd EC2 instance - note IP below will be of 1st EC2 instance
nohup java -jar -Dspring.cloud.config.uri=http://13.51.89.228:9997 -Deureka.client.serviceUrl.defaultZone=http://13.51.89.228:8761/eureka user-service-0.0.1-SNAPSHOT.jar &
nohup java -jar -DloginBaseUrl=http://13.51.89.228:9999/api/v/1.0/lms/company/login -Dspring.cloud.config.uri=http://13.51.89.228:9997 -Deureka.client.serviceUrl.defaultZone=http://13.51.89.228:8761/eureka course-service-0.0.1-SNAPSHOT.jar &

##########
Single Swagger URL to access both micro-service based on defination is below -
http://localhost:9999/swagger-ui/

#################
SonarQube Integration
sonarqube requires java 11
go to sonarQube and start bat file like below - 
\sonarqube-9.6.1.59531\sonarqube-9.6.1.59531\bin\windows-x86-64>StartSonar.bat

Below is the sonarQube URL - 
http://localhost:9000/
credentials vary - admin/test

Add below in pom.xml inside plugin -
<plugin>
<groupId>org.sonarsource.scanner.maven</groupId>
<artifactId>sonar-maven-plugin</artifactId>
<version>3.8.0.2131</version>
</plugin>
<plugin>
<groupId>org.jacoco</groupId>
<artifactId>jacoco-maven-plugin</artifactId>
<version>0.8.5</version>
<executions>
<execution>
<id>prepare-agent</id>
<goals>
<goal>prepare-agent</goal>
</goals>
</execution>
<execution>
<id>report</id>
<goals>
<goal>report</goal>
</goals>
</execution>
</executions>
</plugin>

Also while running application
modify options -- add before launch task -- > click add new task -- run maven goals

clean org.jacoco:jacoco-maven-plugin:prepare-agent install
sonar:sonar -Dsonar.login=squ_f2d7fd1e6f6084102015ab550c855824f492347d

Add above commands in maven goals - 
Note - security token can be found from  open sonarqube -- my account - security 

