import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MyPage extends JDialog {
    private JTable table;
    private JScrollPane scrollpane;
    private JPanel MyPagePanel;
    private DefaultTableModel model;

    public MyPage() {
        setTitle("MyPagePanel");
        setContentPane(MyPagePanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
        createTable_2();
    }
    private void createTable_2() {

        final String DB_URL = "jdbc:mysql://localhost:3306/MyStore";
        final String USERNAME = "root";
        final String PASSWORD = "7392";

        String[] columnNames = {"Name", "Email", "Phone", "Address"};
        model = new DefaultTableModel(columnNames, 0);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT name, email, phone, address FROM mystore.users";
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //빈 테이블 생성
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("email");
                String numberofpersons = rs.getString("phone");
                String time = rs.getString("address");

                Object data[] = {name, phone, numberofpersons, time};
                model.addRow(data);
            }
            table.setModel(model);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyPage mypage = new MyPage();
                    //NoraebangUser noraebangUser = window.noraebangUser;
                    mypage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}