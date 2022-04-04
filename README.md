# AVITEPA

Account and Customer management system

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
   