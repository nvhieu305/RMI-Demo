package view;

import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class DetailUserUI extends JFrame{
    private JTextField edtNewPassword;
    private JTextField edtNewPasswordConFirm;
    private JButton btnChangePassword;
    private JTable tblTable;
    private JPanel mainDemo;
    private JTable tblUser;
    private JLabel tvInfoUser;
    private JLabel tvLogEvent;
    private JLabel tvUserChose;
    private JButton btnDelete;
    private JTextField edtPassUserChose;
    private JButton btnChangeOtherPass;

    private User user;
    ArrayList<User> users = new ArrayList<>();
    private DetailUserCallback detailUserCallback;
    private User userClick;
    String[] columnNames = { "Name", "Pass", "Role" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    public DetailUserUI(User userIN, DetailUserCallback detailUserCallback){
        setTitle("Login");
        setContentPane(mainDemo);
        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tblUser.setModel(tableModel);
        tblUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setVisible(true);


        this.detailUserCallback = detailUserCallback;
        this.user = userIN;
        tvInfoUser.setText("Username: " + user.getUsername() + " - Password: " + user.getPassword() + " - Role: " + user.getRole());

        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = user;
                u.setPassword(edtNewPasswordConFirm.getText());
                if(edtNewPasswordConFirm.getText().equals(edtNewPassword.getText())) {
                    User userResult = detailUserCallback.updatePassword(u);
                    if(userResult == null) {
                        showLog("PassChange Error!");
                    } else {
                        showLog("PassChange Success!");
                        user = userResult;
                        tvInfoUser.setText("Username: " + user.getUsername() + " - Password: " + user.getPassword() + " - Role: " + user.getRole());
                        edtNewPasswordConFirm.setText("");
                        edtNewPassword.setText("");
                        callList();
                    }
                } else {
                    showLog("new password != old password");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getRole() == 1) {
                    if(userClick != null && userClick.getId() != user.getId()) {
                        boolean check = detailUserCallback.deleteUser(userClick);
                        if(check) {
                            callList();
                            showLog("Delete user success!");
                        } else {
                            showLog("Delete user Error!");
                        }
                    }else {
                        showLog("Delete user Error!");
                    }
                }
            }
        });

        btnChangeOtherPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getRole() == 1) {
                    if(userClick != null && userClick.getId() != user.getId()) {
                        User u = userClick;
                        u.setPassword(edtPassUserChose.getText());
                        User userResult = detailUserCallback.updatePassword(u);
                        if(userResult == null) {
                            showLog("PassChange Error!");
                        } else {
                            showLog("PassChange Success!");
                            userClick = userResult;
                            tvUserChose.setText("Username: " + userClick.getUsername() + " - Password: " + userClick.getPassword() + " - Role: " + userClick.getRole());
                            edtPassUserChose.setText("");;
                            callList();
                        }
                    } else {
                        showLog("PassChange Error!");
                    }
                }
            }
        });

        callList();
    }

    public void callList () {
        if(user.getRole() == 1) {
            users = detailUserCallback.showAllUsers();
            if(users != null && users.size() > 0){
                createTable();
            }
        }
    }

    public void createTable() {
        tableModel.setRowCount(0);
        for(int i = 0; i < users.size(); i++) {
            String username = users.get(i).getUsername();
            String password = users.get(i).getPassword();
            String role = users.get(i).getRole().toString();
            Object[] dataCheck = {username, password, role};
            tableModel.addRow(dataCheck);
        }


        tblUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = tblUser.getSelectedRow();
                    System.out.println(index);
                    userClick = users.get(index);
                    tvUserChose.setText("Username: " + userClick.getUsername() + " - Password: " + userClick.getPassword() + " - Role: " + userClick.getRole());
                }
            }
        });
    }


    public void showLog(String log) {
        tvLogEvent.setText(log);
    }
}
