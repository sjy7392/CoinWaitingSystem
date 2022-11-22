import javax.swing.*;
import java.awt.*;
//textField로 data 입력받아 DB에 값 저장한 후 list로 출력
//*고려사항*
//
public class FirstNoraebangForm extends JFrame {
    private JPanel firstnoraebangPanel;
    private JTextField tfName;
    private JTextField tfPhone;
    private JTextField tfNumberOfPersons;
    private JTextField tfTime;
    private JButton btnAdd;
    private JButton btnDelete;
    private JTable showTable;

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
