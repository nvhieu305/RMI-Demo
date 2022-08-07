package view;

import model.User;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DetailUserCallback {
    public User updatePassword(User user) ;
    public boolean deleteUser(User user);
    public ArrayList<User> showAllUsers();
}
