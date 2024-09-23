// package com.example.demo.controller;


// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.LoginRequest;
// import com.example.demo.service.JWTService;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;


// @RestController
// @RequestMapping("/api")
// public class LoginController {

//     @Autowired
//     private AuthenticationManager authenticationManager;

//     @Autowired
//     private JWTService jwtService;

//     private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//     @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//         try {
//             System.out.println("ATTEMPTING AUTHENTICATION: ....");
//             Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                     loginRequest.getUsername(), 
//                     loginRequest.getPassword()
//                 )
//             );
//             logger.info("AUTHENTICATION: " + authentication);
//             return ResponseEntity.ok("Authenticated Successfully!");
//             // if(authentication.isAuthenticated()) {
//             //     return ResponseEntity.ok("TESTING SUCCESS");
//             // }
//             // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//             // // Generate token after successfull authentication
//             // String token = jwtService.generateToken(authentication);
//             // System.out.println("Token is " + token);
//             // return ResponseEntity.ok(token);
//             // System.out.println("PASS AUTHENTICATION");
            
//         } 
        
//         // catch (BadCredentialsException e) {
//         //     // Handle the case where credentials are invalid
//         //     logger.error("Password incorrect: ", e);
//         //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//         // } catch (UsernameNotFoundException e) {
//         //     logger.error("Username not found: {}", loginRequest.getUsername(), e);
//         //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//         // }  
        
//         catch (Exception e) {
//             logger.error("Authentication failed", e);
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
//         }
//     }
    
// }
