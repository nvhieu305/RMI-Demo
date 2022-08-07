import control.UserController;
import view.UserView;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        UserView userView = new UserView();
        UserController userController = new UserController(userView);
    }
}
