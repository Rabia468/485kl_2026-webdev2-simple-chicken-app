# simple Chicken app with Spring Boot - Postgres - alpine.JS


to compile/run for production:
```
mvn clean install  
docker-compose build --no-cache 
docker-compose up -d
echo  "https://${CODESPACE_NAME}-8080.githubpreview.dev"
```

to compile/run for development:
```
mvn clean install  
mvn spring-boot:run -Dspring-boot.run.profiles=dev
echo  "https://${CODESPACE_NAME}-8080.githubpreview.dev"
```

usefull:
```
curl http://localhost:8080/api/counter
docker-compose logs
docker-compose down
docker ps 
docker image ls 
```
# Where are things

- The index.html is at [src/main/resources/static](/src/main/resources/static/index.html)
- sql things is at [src/main/resources/](/src/main/resources/)
- Simple REST API is at [src/main/java/com/example/](src/main/java/com/example/counter/CounterController.java)

# run the tests
- pytest tests

# what to do ??
- Change things to pass the tests
- Push your changes 
