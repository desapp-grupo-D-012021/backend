package ar.edu.unq.desapp.grupoD.backenddesapptp.webService;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.User;
import ar.edu.unq.desapp.grupoD.backenddesapptp.security.JwtUtil;
import ar.edu.unq.desapp.grupoD.backenddesapptp.security.TokenResponse;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUser(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResourceNotFoundException("Wrong username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUser());

        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponse(jwt));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        return userServiceImpl.addUser(user);
    }

    @PatchMapping("/users/{username}")
    public ResponseEntity setAdmin(@PathVariable String username){
        try {
            User user = userServiceImpl.getUserByUsername(username);
            userServiceImpl.setUserAsAdmin(user);
            return ResponseEntity.ok().body(user);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<User> allUsers(){
        return userServiceImpl.getAllUsers();
    }
}
