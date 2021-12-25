# supermarket-loyalty-card

Supermarket Loyalty card is java 11 project which can be run through the console with typing: java -jar Supermarket-Loyalty-Card-0.0.1-SNAPSHOT.jar

Controllers:
  *UserController
    #endpoints
     <-- creating the user -->
      -@PostMapiing("user")
       (note that you need to insert next parameters: String name, String surname, String mobileNumber, String idCardNumber)
      
      Expected output: 
       {
         "id": 11,
         "name": "David",
         "surname": "Ohrigjanec",
         "mobileNumber": "654789",
         "idCardNumber": "6541587456987",
         "transactions": null
       }
       
     <-- get user by mobileNumber and idCardNumber) -->
      -@@GetMapping("/users")
       (note that you need to insert next parameters: String mobileNumber, String idCardNumber)
       
       Expected output:
         {
           "id": 11,
           "name": "David",
           "surname": "Ohrigjanec",
           "mobileNumber": "654789",
           "idCardNumber": "6541587456987",
           "transactions": []
         }
       
   *PointsController
    #endpoints
      <-- adding points to user account (the user must be registered) -->
        -@PostMapping("/add-points")
        (note that you need to insert next parameters: String cashierId, String mobileNumber, String cardNumber, float euros)
        
        Expected output: 
          {
            "id": 11,
            "name": "David",
            "surname": "Ohrigjanec",
            "mobileNumber": "654789",
            "idCardNumber": "6541587456987",
            "transactions": [
                {
                  "id": 12,
                  "cashierId": "951789",
                  "mobileNumber": "654789",
                  "userCardNumber": "6541587456987",
                  "euros": 290.0,
                  "purchasePoints": 50,
                  "timestamp": "2021-12-24T23:06:58.363"
                }                
        
      <-- purchasing or discount -->
        @PutMapping("/redeem")
        (note that you need to insert next parameters: String cashierId, String mobileNumber, String cardNumber, String option, int points
        
        Expected output:
          {
           "id": 12,
           "cashierId": "951789",
           "mobileNumber": "654789",
           "userCardNumber": "6541587456987",
           "euros": 290.0,
           "purchasePoints": 150,
           "timestamp": "2021-12-24T23:06:58.363",
           "user": {
                     "id": 11,
                     "name": "David",
                     "surname": "Ohrigjanec",
                     "mobileNumber": "654789",
                     "idCardNumber": "6541587456987"
                    }
       
        
        
        
        
        
        
        
        
