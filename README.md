Author : Ouahoudi Mehdi

Hexagonal-library > Modules to address separation of concerns (SOLID Design principles) :
    
    application (use cases / input ports)
    domain (business entities, business logic interfaces)
    infrastructure (technical implementation : business, JPA, Mapper, external Api )
    server Web Adapter (controller REST)
    web (Spring Boot bootstrap entrypoint + swagger config)

This project was developed in order to manage a book library through a rest api  :

    Add, Update (Unit Tested), Delete, Get 
        Book and its Author   
    
        Delete Book by id
        Get Book by name
        Get Book by id
        Get scoring's Book (Unit Tested) with computation rule : BookScoringRuleAdapter, GiveBookScoringUseCase
        Get Authors from Id's Book with presentation rule : BookService, FindAuthorByBookIdsUseCase
    
This project can use Http external api call to address this feature :
    
    Find Book by ISBN and LCCN  

This app was developed using JAVA 17 and Spring Boot 3.3.0. It uses an in memory h2 database to store the data.
Running the app

First of all, you'll need to build the project using maven :

mvn clean package

You can then easily start the application using the following command :

java -jar .\server\target\server-1.0-SNAPSHOT.jar

You can access h2 database web gui manager using this connection string <ins>jdbc:h2:mem:testdb</ins> at the following url : 

http://localhost:8080/h2-console

Swagger

You can find a swagger documentation of the API at the following url :

http://localhost:8080/swagger-ui.html

Get started :
    
1 / Setting the authorize Token Api :

    aedz-151-ftyh-554 

2/ Create data -- Add a new book :

    {
        "title": "Le petit prince",
        "publicationDate": "1943-01-01",
        "type": "roman",
        "author": {
            "name": "Antoine de Saint-Exupéry",
            "age" : 125,
            "followersNumber" : 900000
        }
    }

    {
        "title": "Terre des Hommes",
        "publicationDate": "1939-01-01",
        "type": "roman",
        "author": {
            "name": "Antoine de Saint-Exupéry",
            "age" : 125,
            "followersNumber" : 900000
        }
    }

    {
        "title": "Pensées",
        "publicationDate": "1670-01-01",
        "type": "roman",
        "author": {
            "name": "Blaise Pascal",
            "age" : 402,
            "followersNumber" : 150000000
        }
    }

3/ External Api http Call : 

    ISBN:0201558025,LCCN:93005405

mvn clean verify sonar:sonar -Dsonar.projectKey=hexagonal-library -Dsonar.projectName='hexagonal-library' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_55fa38901a58a23e8f1194f61181dfe4cc19b6a8 -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml