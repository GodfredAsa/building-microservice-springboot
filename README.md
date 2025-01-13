## SPRING BOOT MICROSERVICE 

### SETTING UP COMMUNICATION
1. Add the Required Dependency
2. Configure it as a bean 
3. Inject & and use to call the restful api
4. Test the response 

### THE 3 TYPES OF MICROSERVICE COMMUNICATION 
1. REST-TEMPLATE 
2. WEBCLIENT
3. SPRING CLOUD OPEN FEIGN 

### THE 2 STYLES OF COMMUNICATION
1. ASYNCHRONOUS 
    - The client sends a request and does not wait for the response it continues to execute it tasks
    - Examples of technologies used are RABBITMQ OR APACHE KAFKA
    - SERVICE(1) ---message---> QUEUE (MESSAGE BROKER) ----message---> SERVICE(2)
2. SYNCHRONOUS 
    - The client sends a requests and wait for the response from the service. The protocol (HTTP & HTTPS) synchronous.
    - Without the received response the client or service cannot execute any  task.
    - a) Rest-template, b) webclient [both sync and async] & c) spring cloud Open-feign.
    - SERVICE(1) -----http request-----> SERVICE(2),  
    - SERVICE(1) <----http response----- SERVICE(2)

#### Configuring Spring cloud openfeign
1. Add dependency to the service
2. Enable Feign Client using @EnableFeignClients
3. Create Feign API Client interface and annotate as @FeignClient
4. Copy the department controller function that is to be used in employment service as seen this interface. Implement the method to use the feign client.
5. Test the response of the implementation.

#### Creating Multiple Instance of a Service Ex Department-Service
1. Click the Maven symbol (m) on the right side of IntelliJ
2. Expand Life cycle and right select on `package` double click
3. After `BUILD SUCCESS` run `java -jar -Dserver.port=PORT target/SERVICE_NAME.jar`
4. Eg. BUILD SUCCESS run `java -jar -Dserver.port=8083 target/department-service-0.0.1-SNAPSHOT.jar` 

#### LOAD-BALANCING: Distributing load among multiple instances of the same service.
- EUREKA, OPEN-FEIGN, SPRING CLOUD LOAD BALANCER
`//@FeignClient(url = "http://localhost:8082", value = "DEPARTMENT-SERVICE")
  @FeignClient(name = "DEPARTMENT-SERVICE") // uses eureka client internal LB to call different instances of Dpt srv
public interface APIClient {
    @GetMapping("api/departments/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable String code);
}`

### API GATEWAY 
An API Gateway is a server or service that acts as an intermediary between clients 
(like web apps, mobile apps, or other systems) and backend services (like microservices, databases, or external APIs). 
It simplifies the development of APIs by providing a single entry point for managing requests, routing, security, 
and performance optimization.

NB: API Gateway centralises cross-cutting concerns like security, monitoring and rate limiting.

1. Request Routing:
   - Routes incoming client requests to the appropriate backend services. 
   - Supports load balancing by distributing requests across multiple service instances.
   - The security of all microservices are centralised hence each service does need to have its own security implementation.

2. Security:
   - Implements authentication (e.g., OAuth, API keys) and authorization to control access to APIs. 
   - Protects backend services from malicious traffic using rate limiting, throttling, and firewalls.

3. Transformation:
   - Transforms request and response formats (e.g., XML to JSON or vice versa) for compatibility between clients and services.

4. Aggregation:
   - Combines data from multiple microservices into a single response to simplify client-side logic.

5. Monitoring and Analytics:
    - Tracks API usage, performance, and errors. 
    - Provides logs and metrics for observability and debugging.

6. Caching:
   - Reduces load on backend services by caching frequently accessed data.

7. Load Balancing 

#### Benefits of an API Gateway:
- Centralized Management: A single layer to enforce policies and manage APIs.
- Improved Security: Shields backend services from direct exposure to the internet.
- Simplified Client Logic: Offloads complex tasks like authentication and aggregation to the gateway.
- Enhanced Scalability: Enables smooth scaling of backend services by managing traffic efficiently.

#### SPRING CLOUD GATEWAY TO AUTOMATICALLY CREATING ROUTES 
1. Done in the API-Gateway service properties
2. Enable the discovery locator 
3. Manual routes creation is best as used mostly in Enterprise Applications
4. Manual routes creation is best as used mostly in Enterprise Applications

#### EXTERNALIZE CONFIGURATION OF SERVICES USING SPRING CLOUD CONFIG SERVER
1. When there is configuration changes do need to restart each service and its associated instances
2. Config Server enables the externalization of the all service configurations
3. Provides a central repository of service configuration 

##### DETAILS 
1. It's just a spring boot project with the needed dependencies.
   - dependencies: config server, eureka discovery client, spring boot actuator
2. All services point to it as it also points to the repository (GitHub)

#### SPRING CLOUD SETBACK
1. When changes are made to the configuration of each service in the git repository example employee-service
   - We need to call the refresh endpoint of that service manually so imagine we make changes to each all services then we need
   - to iterate this for all those services which is not a good experience to resolve this we use `spring cloud bus`
   - dependency's bus model. Which introduces message broker eg `RabbitMQ or Apache Kafka` to broadcast changes to all 
   - associated services. 

#### DEVELOPMENT STEPS 
1. Add spring-cloud-starter-bus-amqp dependency to services [department-service, employment-service]
2. Install rabbit amq using Docker: `docker pull rabbitmq`, `docker run --rm -it -p 5672:5672 rabbitmq`
3. RabbitMQ configuration in application.properties of the related services
4. Create Simple REST API in employee-service
5. Change [department-service, employment-service] application.properties file and call `/busrefresh`
6. Test implementation

#### DISTRIBUTED TRACING WITH SPRING CLOUD SLEUTH AND ZIPKIN
1. Spring cloud sleuth for distributed tracing and
2. Zipkin for visualising trace information through ui.

- Use Distributed tracing to track request through services from start to finish.
- Track how much time each service takes to process requests
- Dependency added at the API-GATEWAY service

#### SETTING UP ZIPKIN FOR DISTRIBUTED TRACING
1. Download zipkin server and add to project root `https://zipkin.io/pages/quickstart.html`
2. start the server `java -jar zipkin-server.jar`
3. Get Zipkin dependency from start.spring.io and to the following modules 
   - api gateway
   - department service
   - employee service
4. Configure zipkin server url in the respective services
5. Accessing the zipkin server: http://127.0.0.1:9411/

#### CIRCUIT BREAKER 
- Fall back method helps reduce cascade failures.
- Circuit breaker prevents a dependent service from continually hitting the down service.
- Retry mechanism retry hitting the service supposedly down multiple times.
- Rate Limit: sets limit to the number of calls.

Circuit Breaker Working
- It maintains 3 states [opened, closed, halfOpened]
- It helps resolve the continual calls to a service when down and save resources.
- By alternating the desired states as needed.

#### STEPS FOR IMPLEMENTING CIRCUIT BREAKER  { DID NOT WORK }
1. Add dependency resilience4j [resilience4j, aop, actuator], 
   - actuator for monitoring circuit breaker state 
   - aop because actuator uses it to get logging and method information
   - resilience 4j provides the implementation for circuit breaking.
2. @CircuitBreaker to method calling external service.
3. Fall back method implementation
4. Add circuit breaker configuration in application.properties file
5. Restart the service and demo.

#### STEPS FOR IMPLEMENTING RETRY { THIS WORKED }
1. Using @Retry on method calling external service 
2. Fall back method implementation
3. Add retry configuration in application.properties 
4. Restart employee-service and test

#### DOCUMENTING APIs USING SWAGGER 
1. See JSON of API http://localhost:8082/v3/api-docs
2. Accessing Docs http://localhost:8082/swagger-ui/index.html
