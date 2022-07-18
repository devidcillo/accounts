# Account API

The Account API is a simple [Spring Boot](https://spring.io/projects/spring-boot) + [JPA](https://spring.io/projects/spring-data-jpa) (with [Postgres](https://www.postgresql.org/)) project representing a no-frills CRUD for Entities that look like so

```
Account:
    id -> UUID, generated by the App
    username -> alphanumeric
    password -> alphanumeric
    member_since -> date in 'mm-DD-yyyy' format
```

## How to Run
This is a [Gradle](https://gradle.org/) built project, the following tasks can be used from the terminal / console

`./gradlew unitTest` - will run the unit test suite\
`./gradlew integrationTest` - will run the integration test suite, app and DB must be running \
`./gradlew bootRun` - will execute the API on __localhost:8080__

## Endpoints
Once the app is executing, you can interact with these endpoints  
-`GET /accounts` will fetch the existing accounts (a SQL script will pre-populate the DB when the app is run)  
-`GET /accounts/{id}` will fetch an existing account  
-`POST /new-account` will accept a JSON body with the `username` and `password` attributes and return it as a response if successfully updated  
-`PUT /accounts/{id}` will accept a JSON body with the `username` and `password` attributes and update an existing account before returning it as a response  
-`DELETE /accounts/{id}` will delete an existing account  


## Further Work
Due to time restrictions, the API is far too simplistic at the moment.  
In priority order, these are the areas where work could continue
- **Security**: While simple user/password security was added at the beginning, attempts at more complex solutions were taking too much time and were put aside. Ideally, a JWT or OAuth solution should be implemented.
- **Testing**: While the App was developed using TDD, there is a rather significant lack of 'edge cases' that were not covered due to time constraints.
- **Error Handling**: There is some bare bones ErrorHandling provided built, but it does not cover all seen exceptions during development.
- **Containerization**: The App is currently relying heavily on its DB being live before running integration tests. Containerizing the DB and the app itself should also improve portability and ease of use. 
- **Documentation**: This Readme is all that is included for documentation. Adding a [Swagger](https://swagger.io/) instance should do away with "business context" added here.  
Adding CI/CD capabilities should fall between Security and Testing; however, as this is a personal project, it is not of utmost importance.