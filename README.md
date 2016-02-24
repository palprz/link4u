# Link4U [September 2015]
Web application for creating own board on website with important for us links.

## Technologies:
1. Frontend:
  - jQuery and Ajax
  - CSS3
  - HTML5
2. Backend:
  - Java 7
  - Maven ver. 1.8
  - Spring framework (also for JDBC) ver. 4.2.0
  - Tomcat ver. 2.1
  - log4j ver. 1.2.17
3. Database:
  - MySQL (XAMPP) ver. 3.2.1

## Functionalities:
User can:
- create account (username and password)
- change password
- create/change/remove new genres (catalogues)
- create/change/remove link (address, description)

## Steps for run the application:
1. Create database with using commands from 'sql' file in resources directory.
2. Check (and update if there are different details for your database) file `datasource.xml`.
3. Download all dependencies with using maven.
4. Deploy web service (e.g. Tomcat or Glassfish) with Link4U resource.
5. Open browser with `http://localhost:8080/Link4U/login.html` address.

## Screens from running application

1) Homepage
2) Registration
3) Login
4) Homepage after login