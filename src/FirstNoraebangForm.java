import javax.swing.*;
import java.awt.*;

public class FirstNoraebangForm extends JFrame {
    private JPanel firstnoraebangPanel;

    public FirstNoraebangForm() {
        setTitle("FirstNoraebang");
        setContentPane(firstnoraebangPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FirstNoraebangForm window = new FirstNoraebangForm();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
