package com.example.pp_3_1_1.service;

import com.example.pp_3_1_1.model.User;
import com.example.pp_3_1_1.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }
    @Transactional
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }
    @Transactional
    public User saveUser(User user) {
        return userDao.save(user);
    }
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

}
