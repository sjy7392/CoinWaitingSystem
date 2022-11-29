import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardForm extends JFrame {
    private JPanel dashboardPanel;
    private JLabel lbAdmin;
    private JButton btnUserLogin;
    private JButton btnUserRegister;

    public DashboardForm(){
        setTitle("Dashboard");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnUserLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm(DashboardForm.this);
                User user = loginForm.user;
                if (user != null) {  //로그인 성공
                    NoraebangListForm window = new NoraebangListForm();  //NoraebangListForm으로 넘어가기
                    loginForm.setVisible(false);
                }
                else {  //로그인 실패
                    JOptionPane.showMessageDialog(null, "Authentication canceled");
                }
            }
        });

        btnUserRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(DashboardForm.this);
            }
        });
    }

    public static void main(String[] args){
        DashboardForm myform = new DashboardForm();
    }
}
