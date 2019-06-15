package teilar.cs4414202.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teilar.cs4414202.security.jwt.JwtTokenProvider;
import teilar.cs4414202.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
     JwtTokenProvider jwtTokenProvider;

    @Autowired
     UserService userSerice;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest data) {
    	System.out.println("password "+data.getPassword()+"username "+data.getUsername());
        try {
            String username = data.getUsername();
            String password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
            //Roles
            
            String token = jwtTokenProvider.createToken(username, this.userSerice.getUserById(username).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    
   
    
}