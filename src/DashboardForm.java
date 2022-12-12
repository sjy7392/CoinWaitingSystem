import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DashboardForm extends JFrame {
    private JPanel dashboardPanel;
    private JButton btnUserSignin;
    private JButton btnUserSignup;
    private JButton btnAdminLogin;
    private JPanel LeftPanel;
    private JLabel mainDesign;
    private JButton button1;


    public DashboardForm(){
        setTitle("Dashboard");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // 사용자 로그인 버튼
        btnUserSignin.addActionListener(new ActionListener() {
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

        // 사용자 회원가입 버튼
        btnUserSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(DashboardForm.this);
            }
        });

        // 노래방 관리자 로그인 버튼
        btnAdminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerLogin managerlogin = new ManagerLogin(DashboardForm.this);
                Manager manager = ManagerLogin.manager;
                if (manager != null) {  //로그인 성공
                    if (Objects.equals(manager.email, "qorrnsu00@naver.com")) {
                        FirstNoraebangForm window = new FirstNoraebangForm();
                        managerlogin.setVisible(false);
                    }
                    else if (Objects.equals(manager.email, "jiyeon@naver.com")) {
                        SecondNoraebangForm window = new SecondNoraebangForm();
                        managerlogin.setVisible(false);
                    }
                    else {
                        ThirdNoraebangForm window = new ThirdNoraebangForm();
                        managerlogin.setVisible(false);
                    }

                }
                else {  //로그인 실패
                    JOptionPane.showMessageDialog(null, "Authentication canceled");
                }
            }
        });

    }

    public static void main(String[] args){
        DashboardForm myform = new DashboardForm();
    }

    /*private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageIcon mainicon = new ImageIcon("src/images/mainImg.png");
        Image mainimg = mainicon.getImage();
        Image mainchangeImg = mainimg.getScaledInstance(500, 268, Image.SCALE_SMOOTH);
        ImageIcon mainchangeIcon = new ImageIcon(mainchangeImg);
        mainDesign = new JLabel(mainchangeIcon);
    }*/
}
