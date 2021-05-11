package com.revature.bill_donaldson_p0.services;
import com.revature.bill_donaldson_p0.daos.UserDAO;
import com.revature.bill_donaldson_p0.models.AppUser;
import com.revature.bill_donaldson_p0.exceptions.AuthenticationException;
import com.revature.bill_donaldson_p0.exceptions.InvalidRequestException;
import com.revature.bill_donaldson_p0.exceptions.ResourcePersistenceException;


public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public AppUser authenticate(String username, String password) {

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        AppUser user = userDao.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw new AuthenticationException();
        }
        return user;
    }

    public void register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        if (!userDao.isUsernameAvailable(newUser.getUsername())) {
            throw new ResourcePersistenceException("The provided username is already taken!");
        }

        if (!userDao.isEmailAvailable(newUser.getEmail())) {
            throw new ResourcePersistenceException("The provided email is already taken!");
        }

        userDao.save(newUser);

    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() > 20) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 255) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().length() > 255) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().length() > 25) return false;

        return user.getAge() >= 0;
    }


    public boolean isUserEmailValid(AppUser user){
        /* "Like and regular expressions(???) will be used her.*/

        String email;
        email = user.getEmail();
        int i = 0;
        char temp = 'o';
        temp = email.charAt(i);
        //while ((Character.compare(email.charAt(i),'@') == 0) && (i < email.length())) {
        while ((temp == '@') && (i < email.length())) {
            temp = email.charAt(1);
            if (!Character.isDigit(temp) || !Character.isLetter(temp)) {
                return false;

            }
            i = i+1;
            temp = email.charAt(i);
        }   if (i == email.length())
               return false;
            else if (temp != '@')
                return false;

            else if (email.charAt(i+3) != '.')
                return false;
            else
                return true;
        }

    }

