package control;

import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMICallback extends Remote {
    public User addUser(User user) throws RemoteException;;
    public User updatePassword(User user) throws RemoteException;;
    public boolean deleteUser(User user) throws RemoteException;;
    public User login(User user) throws RemoteException;;
    public ArrayList<User> showAllUsers() throws RemoteException;;
}
