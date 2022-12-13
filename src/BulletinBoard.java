import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BulletinBoard extends JDialog {
    private JButton btnAdd;
    private JButton btnCancel;
    private DefaultTableModel model;
    private JPanel BulletinBoard;
    private JTextField tfContent;
    private JTable showTable;

    public BulletinBoard() {
        setTitle("BulletinBoard");
        setContentPane(BulletinBoard);
        setMinimumSize(new Dimension(500, 429));
        setSize(1200, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
        createTable();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerBoardContent();
                createTable();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showTable.getSelectedRow() != -1) {
                    // remove selected row from the model
                    model.removeRow(showTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });
    }
    private void registerBoardContent(){
        String content = tfContent.getText();

        if(content.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter a field");
            return;
        }

        JOptionPane.showMessageDialog(null, "Successfully Added");
        boardContent = addBoardContentToDatabase(content); //db에 추가
    }

    public BoardContent boardContent;
    private BoardContent addBoardContentToDatabase(String content){
        BoardContent boardContent = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/MyStore";
        final String USERNAME = "root";
        final String PASSWORD = "7392";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO bulletinboard (content) " +
                    "VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, content);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                boardContent = new BoardContent();
                boardContent.content = content;
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return boardContent;
    }

    private void createTable(){

        final String DB_URL = "jdbc:mysql://localhost:3306/MyStore";
        final String USERNAME = "root";
        final String PASSWORD = "7392";

        String[] columnNames = {"USER","CONTENT"};
        model = new DefaultTableModel(columnNames, 0);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT bid, content FROM mystore.bulletinboard";
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //빈 테이블 생성
            while (rs.next()) {
                String bid = User.email;  //너비 수정 필요함
                String content = rs.getString("content");

                Object data[] = {bid, content};
                model.addRow(data);

            }
            //showTable.getColumn("bid").setPreferredWidth(20);
            showTable.setModel(model);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    BulletinBoard bulletinBoard = new BulletinBoard();
                    BoardContent boardContent = bulletinBoard.boardContent;
                    bulletinBoard.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
