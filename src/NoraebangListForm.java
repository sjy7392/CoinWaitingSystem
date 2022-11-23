import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoraebangListForm extends JFrame {
    private JButton firstNoraeButton;
    private JButton secondNoraeButton;
    private JButton thirdNoraeButton;
    private JButton fourthNoraeButton;
    private JButton fifthNoraeButton;
    private JPanel noraebanglistPanel;

    public NoraebangListForm() {
        setTitle("NoraebangList");
        setContentPane(noraebanglistPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    NoraebangListForm window = new NoraebangListForm();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initialize () {
        firstNoraeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstNoraebangForm firstNoraebangForm = new FirstNoraebangForm();
                firstNoraebangForm.setVisible(true);
            }
        });
    }
}