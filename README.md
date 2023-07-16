# Project 
This project is a basic password manager. It uses Spring Boot, Spring Web, Java, and the H2 database
It supports CRUD operations.

## Curl examples:

### Display all logins
curl http://localhost:8080/logins

### Post a login
curl -X POST -H "Content-Type: application/json" -d '{\"username\":\"cowboyStar\"}' http://localhost:8080/logins

curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"cowboyStar\", \"password\":\"glowfish\", \"website\":\"www.bok.com\"}" http://localhost:8080/logins

curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"riverDaisy\", \"password\":\"winBo34Xw8\", \"website\":\"www.aoi.com\", \"notes\":\"socks and shoes\"}" http://localhost:8080/logins

### Update a login
curl -X PUT -H "Content-Type: application/json" -d "{\"password\":\"passWordle\"}" http://localhost:8080/logins/1

### Delete a login
curl -X DELETE http://localhost:8080/logins/1

### Generate a password
curl http://localhost:8080/generatePassword
