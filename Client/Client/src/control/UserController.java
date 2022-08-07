package control;

import model.User;
import view.DetailUserCallback;
import view.LoginSignInCallback;
import view.LoginSignInUI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class UserController {
    private String serverHost = "localhost";
    private int serverPort = 11111;
    private RMICallback rmiCallback;
    private Registry registry;
    private String rmiService = "rmiService";

    public UserController() {
        try {
            registry = LocateRegistry.getRegistry(serverHost, serverPort);
            rmiCallback = (RMICallback) (registry.lookup(rmiService));

        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginSignInUI loginSignInUI = new LoginSignInUI(loginSignInCallback, detailUserCallback);
    }
    private LoginSignInCallback loginSignInCallback = new LoginSignInCallback() {
        @Override
        public User login(User user) {
            try {
                return rmiCallback.login(user);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public User addUser(User user) {
            try {
                return rmiCallback.addUser(user);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    };

    private DetailUserCallback detailUserCallback = new DetailUserCallback() {
        @Override
        public User updatePassword(User user) {
            try {
                return rmiCallback.updatePassword(user);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean deleteUser(User user) {
            try {
                return rmiCallback.deleteUser(user);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public ArrayList<User> showAllUsers() {
            try {
                return rmiCallback.showAllUsers();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    };
}




//class LoginListener implements ActionListener {
//    public void actionPerformed(ActionEvent e) {
//        try {
////                User user = userView.login();
////                if(rmiCallback.login(user) != null) {
////                    userView.showMessage("Đăng nhập thành công!");
////                }else {
////                    userView.showMessage("Sai tài khoản hoặc mật khẩu!");
////                }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}
//
//class AddUser implements ActionListener {
//    public void actionPerformed(ActionEvent e) {
//        try {
////                User user = userView.addUser();
////                if(rmiCallback.addUser(user)){
////                    userView.showMessage("Đăng ký thành công!");
////                }else{
////                    userView.showMessage("Username đã được sử dụng bởi tài khoản khác!");
////                }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}













//    public boolean addUser(User user){
//        try {
//            return rmiCallback.addUser(user);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean updatePassword(User user) throws RemoteException{
//        try {
//            return rmiCallback.updatePassword(user);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean deleteUser(User user)throws RemoteException {
//        return rmiCallback.deleteUser(user);
//    }
//
//    public User login(User user) throws RemoteException {
//        return rmiCallback.login(user);
//    }
//
//    public ArrayList<User> showAllUsers() throws RemoteException {
//        return rmiCallback.showAllUsers();
//    }