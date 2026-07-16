# mrc_ms
A repo that will contain apps to practice Microservice Architecture with Spring

**Spring Cloud Config** provides server- and client-side support for externalized configuration in a distributed system.

# How to Set Up Spring Cloud Config Server
**1. Create a Git Repository**
Store your configuration files in Git.
application.properties          # Common properties
employee-service.properties      # Employee Service configs
department-service.properties    # Department Service configs

employee-service.properties
server.port=8081
spring.datasource.url=...

department-service.properties
server.port=8082
spring.datasource.url=...

Note: The properties filename should match the value of 
spring.application.name=employee-service

**2. Create Config Server**

2a. Add dependencies:
    Spring Cloud Config Server
    Spring Boot Starter Web
    Spring Cloud Dependencies (BOM)

2b. Enable Config Server
    use @EnableConfigServer with @SpringBootApplication

3. Configure Config Server's application.properties
server.port=1111
spring.application.name=config-server
spring.cloud.config.server.git.uri=https://github.com/username/config-repo

4. Run the Config server and test with
http://localhost:1111/application/default
http://localhost:1111/{application-name}/{profile}

# How to use the above Spring Cloud Config Server in different microservices

