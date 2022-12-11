# Link4U
Web application for creating own board on website with important for us links.

## Technologies
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
5. Open browser with `http://localhost:8080/Link4U/login.html` address.<br />
You can use `admin` and `admin` for username and password (example account).

## Screens from running application

1) Login:<br />
![alt text][login_img]

2) Homepage:<br />
![alt text][homepage_img]

3) Add link:<br />
![alt text][add_link_img]

4) Edit link:<br />
![alt text][edit_link_img]

[login_img]: https://github.com/palprz/link4u/blob/master/markdown_img_login.png
[homepage_img]: https://github.com/palprz/link4u/blob/master/markdown_img_homepage.png
[add_link_img]: https://github.com/palprz/link4u/blob/master/markdown_img_add_link.png
[edit_link_img]: https://github.com/palprz/link4u/blob/master/markdown_img_edit_link.png
