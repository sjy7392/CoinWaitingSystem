import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManagerLogin extends JDialog {
    public static Manager manager;
    private JTextField tfEmail;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel mloginPanel;
    private JPasswordField pfPassword;

    public ManagerLogin(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(mloginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                manager = getAuthenticatedUser(email, password);

                if (manager != null) {
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(ManagerLogin.this,
                            "Email or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private Manager getAuthenticatedUser(String email, String password) {
        Manager manager = null;

        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "7392";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM managers WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                manager = new Manager();
                manager.name = resultSet.getString("name");
                manager.email = resultSet.getString("email");
                manager.phone = resultSet.getString("phone");
                manager.address = resultSet.getString("address");
                manager.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return manager;
    }

    public static void main(String[] args) {
        ManagerLogin managerlogin = new ManagerLogin(null);
    }
}