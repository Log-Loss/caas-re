package caas.poc.service;

import caas.poc.entity.User;
import caas.poc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(String email, String password) {
        User user = new User();
        user.email = email;
        user.password = password;
        userRepository.saveAndFlush(user);
        return user;
    }

    public User update(Integer id, String email, String password) {
        User user = userRepository.findOne(id);
        user.email = email;
        user.password = password;
        userRepository.saveAndFlush(user);
        return user;
    }

    public Boolean auth(String email, String password) {
        return userRepository.findByEmail(email).password.equals(password);
    }


    public void remove(Integer id) {
        userRepository.delete(id);
    }

    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean exists(Integer id) {
        return userRepository.exists(id);
    }

    public Boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void removeAll() {
        userRepository.deleteAll();
    }

}
