# Introduction 
Action Monitor App

# Modules
1 - Listener-service (Using LogBack)
    
2 - Producer-service (Using SL4j from lombok)

3 - Consumer-service (Using LogBack)

4 - Web-socket (Using LogBack)

# Build
1 - Start Docker on the system

2 - Run ./dockerBuildAndDeploy.sh

# Test
1 - Junit 5 & Mockito have been used for testing

# Stop de Application
1 - Run docker-compose down

# Interact with app
1 - Send messages from Producer-service post endpoint:

http://localhost:8081/swagger-ui/index.html#/REST%20Apis%20related%20to%20producer%20controller/sendMessageUsingPOST

2 - Receive messages on Web-socket url:

http://localhost:8083/

# Tools used
- Kafdrop: web UI for viewing Kafka topics and browsing consumer group.

http://localhost:9000/

- PGAdmin

http://localhost:5050/

# Issues
- Listener-service is commented out. 

It uses pgjdbc-ng postgres library to List / Notify events from the database.

https://jdbc.postgresql.org/documentation/81/listennotify.html

Docker compose threw Connection refused: hostname not found for postgres server.

Need more time to review. It is to be done!!

- ConsumerServiceTest.java test is failing. Assume it is producer config. 

Need to have a look!!

# Improvements
- Creation a common project with all shared objects/DTOs.

- Addition of Sonarqube in docker-compose.yml to check code coverage in the apps.

- Addition of PMD and CheckStyle for code quality.

- Externalise config using Spring Cloud Config Server.

- Authentication/Login for the chat users.

- Using CDC (Change data capture pattern) using Debezium and Kafka connect instead of listeners.

# Stretch Goals
- Producer version of application
http://localhost:8081/actuator/info

- Consumer version of application
http://localhost:8082/actuator/info

- Producer health of application
http://localhost:8081/actuator/health