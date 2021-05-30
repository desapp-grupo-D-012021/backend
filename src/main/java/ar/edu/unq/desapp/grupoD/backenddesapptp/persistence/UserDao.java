package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserDao extends JpaRepository<User,Serializable>, CrudRepository<User,Serializable>{

    User findByUser(String user);

    @Override
    <S extends User> S save(S entity);
}
