# restful-web-service

* This is Project for Design Simple Restful Service.
* 기본적인 Restful Api를 SpringBoot로 구현하기 위한 기본을 셋팅하고, 공부하는 프로젝트입니다. 활용된 기술을 개인 프로젝트에 응용하시면 좋을 것 같습니다.

## Dependencies

* This project build with Maven
* JDK version : JDK 11
* SpringBootVersion : 2.5.2
* Packaging : Jar

  ### Dependency : 
    * Spring Boot DevTools
    * Lombok
    * Spring Web
    * Spring Data JPA
    * H2 Database

## application.properties
1. using port 8088 you can put this code.
```server.port=8088```


## HateOas
1. dependency
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```

## Spring Security
1. dependency
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## Actuator
1. dependency
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## Validator
1. dependency
```
<dependency>
	<groupId>javax.validation</groupId>
	<artifactId>validation-api</artifactId>
	<version>2.0.1.Final</version>
</dependency>
```
