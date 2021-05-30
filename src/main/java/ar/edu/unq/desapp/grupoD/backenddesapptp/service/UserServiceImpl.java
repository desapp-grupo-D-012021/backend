package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.User;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUser(),user.getPassword(),buildGranted(user.getRol()));

        //return new org.springframework.security.core.userdetails.User("foo","foo", new ArrayList<>());
    }

    public List<GrantedAuthority> buildGranted(String rol){
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(rol));

        return auths;
    }

    @Transactional
    public User addUser(User user) {
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

    @Transactional
    public User setUserAsAdmin(User user){
        user.makeAdmin();
        return user;
    }

}
