import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JButton 사용자로그인Button;
    private JButton 관리자로그인Button;
    private JButton 사용자회원가입Button;
    private JButton 관리자회원가입Button;

    public MainForm() {
        사용자회원가입Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //회원가입 페이지로
            }
        });
    }
}
