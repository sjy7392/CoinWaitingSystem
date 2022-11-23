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
        createTable();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerNoraebangUser();
                //createTable();  //JTable에 출력
            }
        });
    }

    private void registerNoraebangUser(){
        String name = tfName.getText();
        String phone = tfPhone.getText();
        String NumberOfPersons = tfNumberOfPersons.getText();
        String time = tfTime.getText();

        if(name.isEmpty()||phone.isEmpty()||NumberOfPersons.isEmpty()||time.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields");
            return;
        }

        JOptionPane.showMessageDialog(null, "Successfully Added");
        noraebangUser = addNoraebangUserToDatabase(name, phone, NumberOfPersons, time); //db에 추가
        createTable(); //JTable에 data 출력하기
    }

    public NoraebangUser noraebangUser;
    private NoraebangUser addNoraebangUserToDatabase(String name, String phone, String NumberOfPersons, String time){
        NoraebangUser noraebangUser = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/MyStore";
        final String USERNAME = "root";
        final String PASSWORD = "7392";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Noraebangusers (name, phone, NumberOfPersons, time) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, NumberOfPersons);
            preparedStatement.setString(4, time);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                noraebangUser = new NoraebangUser();
                noraebangUser.name = name;
                noraebangUser.phone = phone;
                noraebangUser.NumberOfPersons = NumberOfPersons;
                noraebangUser.time = time;
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return noraebangUser;
    }

    private void createTable(){

        final String DB_URL = "jdbc:mysql://localhost:3306/MyStore";
        final String USERNAME = "root";
        final String PASSWORD = "7392";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT name, phone, numberofpersons, time FROM mystore.noraebangusers";
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[][] data = {{rs.getString("name"), rs.getString("phone"), rs.getString("numberofpersons"), rs.getString("time")}};
                String[] columnNames = {"Name","Phone","numberofpersons","time"};
                showTable = new JTable(data,columnNames);
        }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FirstNoraebangForm window = new FirstNoraebangForm();
                    //NoraebangUser noraebangUser = window.noraebangUser;
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
