package view;

import model.User;

public interface LoginSignInCallback {
    User login(User user);
    User addUser(User user);
}
