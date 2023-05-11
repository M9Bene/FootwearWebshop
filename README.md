# FootwearWebshop

### // About // 

A full-stack app for a responsive e-commerce website about sport shoes!

~ still under construction ~
### // Video //
( please watch it on full-screen )

https://github.com/M9Bene/FootwearWebshop/assets/97676446/213ccc60-7e7e-47c0-be4d-078659017e25


### // Tech stack //
#### Back-End
- Java + Spring (+Hibernate)
- H2 database ( changed from PSQL for easier test/setup ) 
- Maven 

#### Front-End
- React
- JavaScript
- CSS
- HTML

#### Test
- JUnit
- Mockito
- Postman

#### Other tools
- IntelliJ IDEA
- Git 

### // Tests //
- for testing! please use different spring profile, named: test 


     because for repository testing there is a separate .sql file to init test data

### // Setup (on Linux) // 
- requirements: java 17
- Clone the repository:
    
    ~ git clone https://github.com/M9Bene/FootwearWebshop.git

#### Back-End
- Navigate to the project directory:

     ~ cd FootwearWebshop/BackEnd-Spring

     ~ mvn clean install

     ~ mvn spring-boot:run

  - The application should now be running at http://localhost:8080/.

#### Front-End
- Navigate to the project directory:
     
     ~ cd FootwearWebshop/frontend-react
     
     ~ npm install
   
     ~ npm start

 - This will start the app in development mode, which you can view in your web browser at http://localhost:3000.
     
#### Other Features to add later
- spring security ( register / login / end-points for authenticated users)
- rating for shoes, also let the logged-in users to vote/rate a product
