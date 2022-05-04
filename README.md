# AVITEPA

Account and Customer management system

Swagger UI can be accessed via this link: http://localhost:9000/swagger-ui/index.html

End point deatils below:

1. Get all accounts as a list:
   GET api/accounts/all
   Accept: application/json
   Content-Type: application/json
2. Create an account with below endpoint
   POST api/accounts/add
   Accept: application/json
   Content-Type: application/json
   
         {
         "accountNumber": 522458,
         "accountType": "SAVINGS",
         "balance": 23962.55,
         "accountStatus": "Active",
         "dateOpened": "2021-04-01"
         }

3. POST /api/customers/add
   Accept: application/json
   Content-Type: application/json
   

      {
      "customerId": 23541,
      "firstName": "Michael",
      "lastName": "Bunk",
      "address": "Albert street",
      "branch": "Los Angelos",
      "mobileNo": "0254721155"
      }
   
4. Transfer amount from one account to another account
   POST /api/accounts/transfer
   Accept: application/json
   Content-Type: application/json
   Query Params:
   Key:fromAccount, value int
   Key:toAccount, value int
   Key:amount, value double
   
5. Get balance of the account by passing account number
   GET /api/accounts/balance
   Accept: application/json
   Content-Type: application/json
   Query Params:
   Key:accountNumber, value int
   
Some of important and widely used actuator endpoints are given below:


/auditevents   -->  Returns all auto-configuration candidates and the reason why they ‘were’ or ‘were not’ applied.

/beans         -->  Returns a complete list of all the Spring beans in your application. 

/mappings      -->  Displays a collated list of all @RequestMapping paths..

/env           -->  Returns list of properties in current environment

/health        -->  Returns application health information.

/caches        -->  It exposes available caches.

/conditions    -->  Shows the conditions that were evaluated on configuration and auto-configuration.

/configprops   -->  It displays a collated list of all @ConfigurationProperties.

/integrationgraph -->  It shows the Spring Integration graph. Requires a dependency on spring-integration-core.

/loggers          -->  The configuration of loggers in the application.

/scheduledtasks   -->  Displays the scheduled tasks in the application.

/sessions         -->  Returns trace logs (by default the last 100 HTTP requests). Requires an HttpTraceRepository bean.

/httptrace        -->  It allows retrieval and deletion of user sessions from a Spring Session-backed session store. Requires a Servlet-based web application using Spring Session.

/shutdown         -->  Lets the application be gracefully shutdown. Disabled by default.

/threaddump       -->  It performs a thread dump.

/metrics          -->  It shows several useful metrics information like JVM memory used, system CPU usage, open files, and much more.

   