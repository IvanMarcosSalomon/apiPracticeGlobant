# README

Important Links
API Documentation:
Swagger Json Format - http://localhost:8080/v2/api-docs
Swagger UI - http://localhost:8080/swagger-ui/
To check the database
H2 DB - http://localhost:8080/h2-console/
url=jdbc:h2:mem:api_users
username=user
password=user
How to get it to work:
1) Download the code as a zip or clone it to your local machine.
2) Import the proyect as an Existing Maven Proyect by going to File - Import
![image](https://user-images.githubusercontent.com/49291080/115636317-12684a80-a2e4-11eb-844a-c42b81883b23.png)
Select the Existing Maven Proyect option.
![image](https://user-images.githubusercontent.com/49291080/115636361-2c099200-a2e4-11eb-8992-d442c2004a01.png)
And browse to the folder where the proyect has been stored.
3) Go to RestfulWebUserServicesApplication.java, open it, select the class name and run it as a Java
Application.
![image](https://user-images.githubusercontent.com/49291080/115636395-404d8f00-a2e4-11eb-908a-04b618d61776.png)
4) The app should be running, the default port is 8080, I left a parameter to change this in the case
that the port is already in use.
![image](https://user-images.githubusercontent.com/49291080/115636453-5bb89a00-a2e4-11eb-947a-7be88c761543.png)
I you go to the src/main/resources/application.properties file you will find this configuration:
Just change the port number to an available one.
