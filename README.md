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

New: Swagger activated for testing interactively the rest API
```
http://localhost:8080/swagger-ui/index.html
```

usefull:
```
docker-compose logs
docker-compose down
docker-compose down -v # to reset the pgdata volume
docker ps 
docker image ls 
```

If you need to clear docker volumes (for postgres):
```
docker-compose down -v
```

carefull -> data are reset !

# Where are things

- The index.html is at [src/main/resources/static](/src/main/resources/static/index.html)
- Simple REST API is at [src/main/java/com/example/](src/main/java/com/example/chicken/)

# run the tests
- pytest tests

# what to do ??
- Change things to pass the tests
- Push your changes 
