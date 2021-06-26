This project focuses on unit testing a REST API for managing beer stocks. Unit tests were developed to validate the beer stock management system, and the main concepts and advantages of creating unit tests with JUnit and Mockito were also studied. API functionalities were also developed through the practice of TDD.

To run the project on the terminal, type the following command:

```shell script
mvn spring-boot:run
```

To run the test suite, just run the following command:

```shell script
mvn clean test
```

After executing the above command, just open the following address and view the project execution:

```
http://localhost:8080/api/v1/beers
```

The following prerequisites are required for project execution:

* Java 14 or higher versions.
* Maven 3.6.3 or higher versions.
* Intellj IDEA Community Edition or your favorite IDE.
* GIT version control installed on your machine.

Below, there are some nice links, used as a reference for the development of the project:

* [SDKMan! for managing and installing Java and Maven](https://sdkman.io/)
* [Intellij IDEA Community Reference, for download](https://www.jetbrains.com/idea/download)
* [Intellij Command Shortcuts Palette](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Official Spring website](https://spring.io/)
* [Junit 5 official website](https://junit.org/junit5/docs/current/user-guide/)
* [Official Mockito website](https://site.mockito.org/)
* [Hamcrest official website](http://hamcrest.org/JavaHamcrest/)
* [References - testing in general with Spring Boot](https://www.baeldung.com/spring-boot-testing)
* [Reference for the REST architectural standard](https://restfulapi.net/)
* [Test pyramid reference - Martin Fowler](https://martinfowler.com/articles/practical-test-pyramid.html#TheImportanceOftestAutomation)