package control;

import control.dao.UserDAO;
import control.utils.ConnectionUtils;
import model.User;
import view.UserView;

import java.awt.desktop.UserSessionEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UserController extends UnicastRemoteObject implements RMICallback {
    UserView userView;
    UserDAO userDAO;
    private Registry registry;
    private int serverPort = 11111;
    private String rmiService = "rmiService";

    public UserController(UserView userView) throws RemoteException {
        try {
            userDAO = new UserDAO(ConnectionUtils.getMyConnection());
            this.userView = userView;

            registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind(rmiService, this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User addUser(User user) {
        ArrayList<User> list = this.userDAO.getAll();
        for(int i = 0; i < list.size(); i++) {
            if(user.getUsername().equals(list.get(i).getUsername())) {
                userView.showLog("Add user fail! // User exist");
                return null;
            }
        }

        int rowCount = this.userDAO.insert(user);
        if(rowCount == 0) {
            userView.showLog("Add user fail!");
            return null;
        }
        else {
            userView.showLog("Add user success!");
            return login(user);
        }
    }

    @Override
    public User updatePassword(User user) {
        if(this.userDAO.update(user) == 0) {
            userView.showLog("Update user fail!");
            return null;
        }
        else {
            userView.showLog("Update user success!");
            return login(user);
        }
    }

    @Override
    public boolean deleteUser(User user) {
        int rowCount = this.userDAO.delete(user);
        if(rowCount == 0) {
            userView.showLog("Delete user fail!");
            return false;
        } else {
            userView.showLog("Delete user success!");
            return true;
        }
    }

    @Override
    public User login(User user) {
         User u = this.userDAO.getByLogin(user.getUsername(), user.getPassword());
         if(u == null) {
             userView.showLog("Login user fail!");
         } else {
             userView.showLog("Login user success!");
         }
         return u;
    }

    @Override
    public ArrayList<User> showAllUsers() {
        ArrayList<User> list = this.userDAO.getAll();
        if(list.size() == 0) {
            userView.showLog("Show all user fail!");
        } else {
            userView.showLog("Show all user success!");
        }
        return list;
    }
}
