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
4. Copy the department controller function that is to be used in employment service as seen this interface.
4. Implement the method to use the feign client.
5. Test the response of the implementation.