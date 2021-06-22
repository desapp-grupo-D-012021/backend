package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.User;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUser(),user.getPassword(),new ArrayList<>());

    }

    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCritic(user.getCritic());
        return dao.save(user);
    }

    @Transactional
    public User getUserByUsername(String username){
        User user = dao.findByUser(username);
        return user;
    }

    @Transactional
    public List<User> getAllUsers() {
        return dao.findAll();
    }


}
