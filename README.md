# Lambazon Online Store (Beta)
Lambazon is an online Ecommerce retailer.

This application represents their forthcoming beta of a new
store-front. It is currently a work in progress, having been left
in a proof-of-concept state by the previous developer.

As the next developer on this project you are required to:
* Ensure that the code meets our development guidelines (See the checkstyle config)
* Stories on the backlog are delviered in a timely fashion
* All work on this project is delivered using TDD and respects the
Testing Pyramid.

# Handover Notes

1. Lee the previous developer did not have a chance to clean up the codebase.
To run the tests or build without checkstyle failing you can use `-Dcheckstyle.skip`
   * Eg. `mvn test -Dcheckstyle.skip`
1. To run the app you can use `mvn spring-boot:run` (add `-Dcheckstyle.skip` if required)
1. The running app's client facing page can be seen at `http://localhost:8080/`
1. The admin portal for managing inventory is located at `http://localhost:8080/admin/product`
1. We value committing your code regularly.
1. `mvn test` will run both checkstyle and jacoco coverage reporting. Look in `target/sites`.
1. To generate all reports (including Findbugs, run `mvn site`)

