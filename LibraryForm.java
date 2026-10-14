import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class LibraryForm {
    
    static java.sql.Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = System.getenv("DB_PASSWORD");

        return java.sql.DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try {
    java.sql.Connection con = getConnection();
    JOptionPane.showMessageDialog(null, "MySQL Connected Successfully!");
    con.close();
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Connection Failed: " + ex.getMessage());
}

        JFrame frame = new JFrame();
        
Color bgColor = new Color(30, 34, 42);
Color panelColor = new Color(40, 45, 55);
Color textColor = new Color(235, 238, 242);
Color accentColor = new Color(0, 200, 180);
Color fieldColor = new Color(52, 58, 70);


        frame.setTitle("Library Management System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
JLabel heading = new JLabel("LIBRARY MANAGEMENT SYSTEM");
heading.setBounds(65, 5, 400, 35);
heading.setForeground(accentColor);
heading.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
frame.add(heading);

        frame.getContentPane().setBackground(bgColor);

        // Student Name
        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(40, 40, 100, 30);
        nameLabel.setForeground(textColor);
        frame.add(nameLabel);


        JTextField nameField = new JTextField();
        nameField.setBounds(150, 40, 250, 30);
        nameField.setBackground(fieldColor);
nameField.setForeground(textColor);
nameField.setCaretColor(textColor);
        frame.add(nameField);

        // Student ID
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(40, 90, 100, 30);
        idLabel.setForeground(textColor);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(150, 90, 250, 30);
        idField.setBackground(fieldColor);
idField.setForeground(textColor);
idField.setCaretColor(textColor);
        frame.add(idField);

        // Book Name
        JLabel bookLabel = new JLabel("Book Name:");
        bookLabel.setBounds(40, 140, 100, 30);
        bookLabel.setForeground(textColor);
        frame.add(bookLabel);

        JTextField bookField = new JTextField();
        bookField.setBounds(150, 140, 250, 30);
        bookField.setBackground(fieldColor);
bookField.setForeground(textColor);
bookField.setCaretColor(textColor);
        frame.add(bookField);

        // Book ID
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(40, 190, 100, 30);
        bookIdLabel.setForeground(textColor);
        frame.add(bookIdLabel);

        JTextField bookIdField = new JTextField();
        bookIdField.setBounds(150, 190, 250, 30);
        bookIdField.setBackground(fieldColor);
bookIdField.setForeground(textColor);
bookIdField.setCaretColor(textColor);
        frame.add(bookIdField);
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 240, 150, 35);
        frame.add(submitButton);
        submitButton.setBackground(accentColor);
submitButton.setForeground(Color.BLACK);
submitButton.setFocusPainted(false);
submitButton.setOpaque(true);
submitButton.setBorderPainted(false);

submitButton.addActionListener(e -> {

    String studentName = nameField.getText().trim();
    String studentId = idField.getText().trim();
    String bookName = bookField.getText().trim();
    String bookId = bookIdField.getText().trim();

    if (studentName.isEmpty() || studentId.isEmpty()
            || bookName.isEmpty() || bookId.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please fill all fields!");
        return;
    }

    if (!studentName.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {
        JOptionPane.showMessageDialog(frame,
                "Invalid name! Use letters and spaces only.");
        return;
    }

    if (!studentId.matches("[0-9]+")
            || !bookId.matches("[0-9]+")) {
        JOptionPane.showMessageDialog(frame,
                "Student ID and Book ID must contain digits only!");
        return;
    }

    try {
        Connection con = getConnection();

        String sql = "INSERT INTO issue_records "
                + "(student_id, student_name, book_id, book_name) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, Integer.parseInt(studentId));
        pst.setString(2, studentName);
        pst.setInt(3, Integer.parseInt(bookId));
        pst.setString(4, bookName);

        pst.executeUpdate();

        pst.close();
        con.close();

        JOptionPane.showMessageDialog(frame,
                "Student and Book saved successfully!");

        nameField.setText("");
        idField.setText("");
        bookField.setText("");
        bookIdField.setText("");

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame,
                "Error: " + ex.getMessage());
    }
});

        
JButton viewButton = new JButton("View All Records");
viewButton.setBounds(150, 290, 150, 35);
frame.add(viewButton);
viewButton.setBackground(accentColor);
viewButton.setForeground(Color.BLACK);
viewButton.setFocusPainted(false);
viewButton.setOpaque(true);
viewButton.setBorderPainted(false);
JButton searchButton = new JButton("Search Student");
searchButton.setBounds(150, 340, 150, 35);
frame.add(searchButton);
searchButton.setBackground(accentColor);
searchButton.setForeground(Color.BLACK);
searchButton.setFocusPainted(false);
searchButton.setOpaque(true);
searchButton.setBorderPainted(false);
JButton updateButton = new JButton("Update Record");
updateButton.setBounds(150, 390, 150, 35);
frame.add(updateButton);
updateButton.setBackground(accentColor);
updateButton.setForeground(Color.BLACK);
updateButton.setFocusPainted(false);
updateButton.setOpaque(true);
updateButton.setBorderPainted(false);


JButton deleteButton = new JButton("Delete Record");
deleteButton.setBounds(150, 440, 150, 35);
deleteButton.setBackground(accentColor);
deleteButton.setForeground(Color.BLACK);
deleteButton.setFocusPainted(false);
deleteButton.setOpaque(true);
deleteButton.setBorderPainted(false);
frame.add(deleteButton);

deleteButton.addActionListener(e -> {
    String input = JOptionPane.showInputDialog(
        frame, "Enter Student ID to delete:"
    );

    if (input == null || input.trim().isEmpty()) {
        return;
    }

    try {
        int studentId = Integer.parseInt(input.trim());

        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Are you sure you want to delete Student ID: " + studentId + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM issue_records WHERE student_id = ?")) {

            ps.setInt(1, studentId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(
                    frame, "Record deleted successfully!"
                );
            } else {
                JOptionPane.showMessageDialog(
                    frame, "No record found with this Student ID."
                );
            }
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
            frame, "Please enter a valid numeric Student ID."
        );
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            frame, "Error deleting record: " + ex.getMessage()
        );
    }
});


updateButton.addActionListener(e -> {

    String studentId = JOptionPane.showInputDialog(
        frame, "Enter Student ID to update:"
    );

    if (studentId == null || studentId.trim().isEmpty()) {
        return;
    }

    if (!studentId.trim().matches("[0-9]+")) {
        JOptionPane.showMessageDialog(frame,
            "Student ID must contain digits only!");
        return;
    }

    try {
        Connection con = getConnection();

        String checkSql =
            "SELECT * FROM issue_records WHERE student_id = ?";
        PreparedStatement checkPst = con.prepareStatement(checkSql);
        checkPst.setInt(1, Integer.parseInt(studentId.trim()));

        ResultSet rs = checkPst.executeQuery();

        if (!rs.next()) {
            JOptionPane.showMessageDialog(frame, "Student not found!");
            rs.close();
            checkPst.close();
            con.close();
            return;
        }

        String oldName = rs.getString("student_name");
        String oldBookName = rs.getString("book_name");
        int oldBookId = rs.getInt("book_id");

        rs.close();
        checkPst.close();

        String newName = JOptionPane.showInputDialog(
            frame, "Enter new Student Name:", oldName
        );

        if (newName == null || newName.trim().isEmpty()) {
            con.close();
            return;
        }

        if (!newName.trim().matches("[a-zA-Z]+( [a-zA-Z]+)*")) {
            JOptionPane.showMessageDialog(frame, "Invalid student name!");
            con.close();
            return;
        }

        String newBookName = JOptionPane.showInputDialog(
            frame, "Enter new Book Name:", oldBookName
        );

        if (newBookName == null || newBookName.trim().isEmpty()) {
            con.close();
            return;
        }

        String newBookIdText = JOptionPane.showInputDialog(
            frame, "Enter new Book ID:", String.valueOf(oldBookId)
        );

        if (newBookIdText == null || newBookIdText.trim().isEmpty()) {
            con.close();
            return;
        }

        if (!newBookIdText.trim().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(frame, "Book ID must be digits only!");
            con.close();
            return;
        }

        String updateSql =
            "UPDATE issue_records SET student_name = ?, book_name = ?, book_id = ? "
            + "WHERE student_id = ?";

        PreparedStatement updatePst = con.prepareStatement(updateSql);
        updatePst.setString(1, newName.trim());
        updatePst.setString(2, newBookName.trim());
        updatePst.setInt(3, Integer.parseInt(newBookIdText.trim()));
        updatePst.setInt(4, Integer.parseInt(studentId.trim()));

        updatePst.executeUpdate();

        updatePst.close();
        con.close();

        JOptionPane.showMessageDialog(frame, "Record updated successfully!");

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
    }
});

searchButton.addActionListener(e -> {

    String studentId = JOptionPane.showInputDialog(
        frame, "Enter Student ID to search:"
    );

    if (studentId == null || studentId.trim().isEmpty()) {
        return;
    }

    if (!studentId.trim().matches("[0-9]+")) {
        JOptionPane.showMessageDialog(frame,
            "Student ID must contain digits only!");
        return;
    }

    try {
        Connection con = getConnection();

        String sql = "SELECT * FROM issue_records WHERE student_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, Integer.parseInt(studentId.trim()));

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(frame,
                "Student ID: " + rs.getInt("student_id")
                + "\nStudent Name: " + rs.getString("student_name")
                + "\nBook ID: " + rs.getInt("book_id")
                + "\nBook Name: " + rs.getString("book_name"));
        } else {
            JOptionPane.showMessageDialog(frame,
                "No record found for this Student ID!");
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame,
            "Error: " + ex.getMessage());
    }
});

viewButton.addActionListener(e -> {
   
try {
    Connection con = getConnection();

    String sql = "SELECT * FROM issue_records";
    PreparedStatement pst = con.prepareStatement(sql);
    ResultSet rs = pst.executeQuery();

    String[] columns = {
        "Student ID", "Student Name", "Book ID", "Book Name"
    };

    javax.swing.table.DefaultTableModel model =
        new javax.swing.table.DefaultTableModel(columns, 0);

    while (rs.next()) {
        Object[] row = {
            rs.getInt("student_id"),
            rs.getString("student_name"),
            rs.getInt("book_id"),
            rs.getString("book_name")
        };

        model.addRow(row);
    }

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    scrollPane.setPreferredSize(new java.awt.Dimension(600, 300));

    JOptionPane.showMessageDialog(
        frame,
        scrollPane,
        "Library Records",
        JOptionPane.PLAIN_MESSAGE
    );

    rs.close();
    pst.close();
    con.close();

} catch (Exception ex) {
    JOptionPane.showMessageDialog(frame,
        "Error: " + ex.getMessage());
}





// Clear all input fields after confirmation

});
        

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}