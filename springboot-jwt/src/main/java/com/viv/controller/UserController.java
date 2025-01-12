package com.viv.controller;


import com.viv.model.Customer;
import com.viv.model.LoginRequest;
import com.viv.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;  

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            Customer savedCustomer = repository.save(customer);
            if(savedCustomer.getId()  >0 ){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Given user details are registration failed");
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("An exception occured: " + ex.getMessage());
        }
    }

    @PostMapping("/apiLogin")
    public String postMethodName(@RequestBody LoginRequest loginRequest) {
       String jwt =null;
        Authentication unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.userName(),
       loginRequest.password());
       Authentication authResponse= authenticationManager.authenticate(unauthenticated);
       if(authResponse != null && authResponse.isAuthenticated()){
           jwt = "Bearer "+authResponse.getDetails();

       }
        return "entity";
    
    }
    
}
