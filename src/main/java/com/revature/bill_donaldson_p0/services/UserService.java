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

        System.out.println("You are within authenticate");
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

        if ((isUserValid(newUser)) /*&& (isUserEmailValid(newUser))*/){
            userDao.save(newUser);
        }
        else
            System.out.println("Did not save user.");


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
        /* I'm going to do a very basic check on e-mail.  I'm only going to
        *  accept *.aol.com emails.  If need be, the logic can be enhanced. **/


        String email = user.getEmail();
        int i = 0;
        char temp = 'o';
        char position8 = email.charAt(email.length()-8);  // Will hold @
        char position7 = email.charAt(email.length()-7);
        char position6 = email.charAt(email.length()-6);
        char position5 = email.charAt(email.length()-5);
        char position4 = email.charAt(email.length()-4);
        char position3 = email.charAt(email.length()-3);
        char position2 = email.charAt(email.length()-2);
        char position1 = email.charAt(email.length()-1);

        if ((Character.compare(position8,'@') == 1) &&
                (Character.compare(position7,'a') == 1) &&
                (Character.compare(position6,'o') == 1) &&
                (Character.compare(position5,'l') == 1) &&
                (Character.compare(position4,'.') == 1) &&
                (Character.compare(position3,'c') == 1) &&
                (Character.compare(position2,'o') == 1) &&
                (Character.compare(position1,'m') == 1))
            return true;
        else
            return false;

    }
}
