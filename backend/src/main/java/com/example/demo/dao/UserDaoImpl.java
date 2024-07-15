package com.example.demo.dao;

// import java.util.List;

import org.springframework.stereotype.Repository;

// import com.example.demo.model.User;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.EntityNotFoundException;
// import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    

    // @PersistenceContext
    // private EntityManager entityManager;

    // @Override
    // public String saveUser(User user) {
    //     try {
    //         entityManager.merge(user);
    //         return "Register Successfully!";
    //     } catch(Exception e) {
    //         throw new RuntimeException("Failed to save user: " + e.getMessage());
    //     }
    // }

    // @Override
    // public User getProfileById(Integer id) {
    //     try {
    //         return entityManager.find(User.class, id);
    //     } catch(Exception e) {
    //         throw new RuntimeException("Failed to retrieve user: " + e.getMessage());
    //     }
    // }

    // @Override
    // public String updateProfile(User user) {
    //     try {
    //         if(getProfileById(user.getId()) != null) {
    //             entityManager.merge(user);
    //             return "Update Successfully!";
    //         }
    //         return null;
    //     } catch(Exception e) {
    //         throw new RuntimeException("Failed to update user: " + e.getMessage());
    //     }
    // }

    // @Override
    // public String deleteUser(Integer id) {
    //     User user = getProfileById(id);
    //     if(user != null) {
    //         entityManager.remove(user);
    //         return "Delete Successfully!";
    //     } else {
    //         throw new EntityNotFoundException("User with id " + id + " not found");
    //     }
    // }

    // @Override
    // public List<User> getAllUser() {
    //     return entityManager.createQuery("SELECT e FROM User e ORDER BY e.id", User.class).getResultList();
    // }


}
