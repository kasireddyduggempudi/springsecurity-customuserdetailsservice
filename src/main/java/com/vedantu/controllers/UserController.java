package com.vedantu.controllers;

import com.vedantu.requests.AuthenticationRequest;
import com.vedantu.responses.AuthenticationResponse;
import com.vedantu.security.CustomUserDetailsService;
import com.vedantu.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authReq){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        /*System.out.println("hello");*/

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authReq.getUsername());

        String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    @RequestMapping("/home")
    public String home(){
        return "Welcome to home. This can be accessed by any role but authentication required";
    }

    @RequestMapping(value ="/user")
    public String user(){
        return "Welcome to user portal!!! :)";
    }

    @RequestMapping(value ="/admin")
    public  String admin(){
        return "Welcome to admin panel!!! :)";
    }
}
