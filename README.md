# supermarket-loyalty-card

Supermarket Loyalty card is java 11 project which can be run through the console with typing: java -jar Supermarket-Loyalty-Card-0.0.1-SNAPSHOT.jar

Controllers:
  *UserController
    #endpoints
     <-- creating the user -->
      -@PostMapiing("user")
       (note that you need to insert next parameters: String name, String surname, String mobileNumber, String idCardNumber)
       
     <-- get user by mobileNumber and idCardNumber) -->
      -@@GetMapping("/users")
       (note that you need to insert next parameters: String mobileNumber, String idCardNumber)
       
   *PointsController
    #endpoints
      <-- adding points to user account (the user must be registered) -->
        -@PostMapping("/add-points")
        (note that you need to insert next parameters: String cashierId, String mobileNumber, String cardNumber, float euros)
      <-- purchasing or discount -->
        @PutMapping("/redeem")
        (note that you need to insert next parameters: String cashierId, String mobileNumber, String cardNumber, String option, int points
