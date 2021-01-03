## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

What things you need to install 

```
* JDK 8
* Maven
* postgres
* docker (if want to run it through docker image)

```
### helping softwares 

those are optional you can use any softwares you re familiar with 

```
* VS code
* postman
* pgadmin

```
### Installing

--> First clone the repository
- git clone https://github.com/SiddharthChoudhary/spring_boot_e-commerce_implementation.git

- Once cloned, cd spring_boot_e-commerce_implementation

- run sh ./mvnw package && java -jar target/order-0.0.1-SNAPSHOT.jar

- docker-compose up , to run this successfully, you would need to have the .jar file available and so for that the above command should work first. Once your above command runs you can try your hands on docker command as well


A step by step series of examples that tell you how to get a development env running
after instaling the Prerequisites all you need to do is to clone the project then run it.

to add an order you need at least one customer and item in your database 
to build and run docker images execute 
```
*docker-compose up

```
and dont forget to change the datasource url in properties to
```
*spring.datasource.url=jdbc:postgresql://db:5432/ecommerce

```


