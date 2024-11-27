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