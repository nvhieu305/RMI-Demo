import control.UserController;

import java.rmi.RemoteException;

public class Main {
//    Xây dựng ứng dụng phân tán cho phép người dùng từ xa thực thi việc quản lý tài khoản. Thông
//    tin mỗi tài khoản (username, password, role) trong đó role = 1 là Admin, role = 2 là user thường.
//    Đối tượng từ xa sẽ hỗ trợ các thao tác:

//       Thêm tài khoản
//       Sửa mật khẩu tài khoản (Admin cũng có quyền sửa mật khẩu của tài khoản khác)
//       Xóa tài khoản (Chỉ tài khoản Admin, mới được phép xóa tài khoản khác)
//       Thực hiện việc đăng nhập
//       Hiển thị thông tin username và password của tài khoản (Mỗi user chỉ được phép hiển
//        thị thông tin của user đó; ngoài ra Admin cũng có thể xem thông tin của các tài khoản khác)

//    Thông tin được truyền lên server theo kiểu đối tượng.
//    Cài đặt ứng dụng trên theo mô hình MVC.
//      - Sử dụng JDBC – MySQL (export sql script và nộp cùng code)
//      - Viết Server và Client
//      - Sử dụng giao diện

    public static void main(String[] args) throws RemoteException {
        UserController userController = new UserController();
    }
}