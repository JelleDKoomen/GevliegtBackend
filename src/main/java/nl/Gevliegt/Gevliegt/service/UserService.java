package nl.Gevliegt.Gevliegt.service;

import nl.Gevliegt.Gevliegt.model.Role;
import nl.Gevliegt.Gevliegt.model.User;
import nl.Gevliegt.Gevliegt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(String username, String password, String email, String firstName, String lastName, Date birthdate, String profilePictureUrl) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthdate(birthdate);
        user.setProfilePictureUrl(profilePictureUrl);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public boolean validateUser(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}