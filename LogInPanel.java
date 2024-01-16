import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInPanel extends FrontPage implements ActionListener {
    JFrame jFrame ;
    JPanel sloganPanel, whitePanel, blankPanel;
    JLabel logoLabel, sloganLabel, loginLabel, userLabel, passwordLabel;
    JButton logInButton, backButton;
    JTextField adminName, adminPassword;
    Font titleFont = new Font("Cooper Black", Font.BOLD, 32);
    private Font font = new Font("Consolas", Font.BOLD, 50);
    private Font buttonFont = new Font("Consolas", Font.BOLD, 18);
    private static final Color SKY_BLUE_COLOR = new Color(140, 210, 240).darker();
    private static final Color WHITE_COLOR = Color.WHITE;

    LogInPanel() throws SQLException, ClassNotFoundException {
        super();
        jFrame = new JFrame("Client");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(1050, 600);
        jFrame.setLayout(new BorderLayout());

        whitePanel = new JPanel();
        whitePanel.setBackground(Color.white);
        whitePanel.setLayout(null);

        loginLabel = new JLabel("LogIn");
        loginLabel.setFont(Globals.headingFont);

        loginLabel.setForeground(Color.BLACK);
        loginLabel.setBounds(250, 140, 200, 30);
        whitePanel.add(loginLabel);

        userLabel = new JLabel("Username:");
        userLabel.setFont(buttonFont);
        userLabel.setBounds(105, 200, 120, 30);
        whitePanel.add(userLabel);

        adminName = new JTextField();
        adminName.setBounds(225, 200, 200, 30);
        adminName.addActionListener(this);
        whitePanel.add(adminName);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(buttonFont);
        passwordLabel.setBounds(userLabel.getX(), 260, 120, 30);
        whitePanel.add(passwordLabel);

        adminPassword = new JPasswordField();
        adminPassword.setBounds(adminName.getX(), 260, 200, 30);
        adminPassword.addActionListener(this);
        whitePanel.add(adminPassword);

        logInButton = new JButton("LogIn");
        logInButton.setBackground(Color.decode("#1c1515"));
        logInButton.setForeground(Color.WHITE);
        logInButton.setFont(buttonFont);
        logInButton.setBounds(userLabel.getX()+80, 320, 100, 40);
        logInButton.addActionListener(this);
        whitePanel.add(logInButton);

        backButton = new JButton("SignUp");
        backButton.setBackground(Color.decode("#1c1515"));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(buttonFont);
        backButton.setBounds(logInButton.getX()+150, 320, 100, 40);
        backButton.addActionListener(this);
        whitePanel.add(backButton);


        sloganPanel = new JPanel();
        sloganPanel.setLayout(new GridLayout(4, 1));
        sloganPanel.setBackground(Color.black);
        sloganPanel.setBounds(0 ,0 ,jFrame.getWidth()/4 , jFrame.getHeight());
        sloganPanel.setPreferredSize(new Dimension(jFrame.getWidth() / 3, jFrame.getHeight()));

        blankPanel = new JPanel();
        blankPanel.setBackground(Color.black);
        sloganPanel.add(blankPanel);

        ImageIcon backgroundIcon = new ImageIcon("Images/Netflix.png");
        Image scaledImage = backgroundIcon.getImage().getScaledInstance(
                jFrame.getWidth() / 3, jFrame.getHeight() /3, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel gifLabel = new JLabel(scaledIcon);
        gifLabel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());

        sloganLabel = new JLabel("MovieMania");
        sloganLabel.setFont(titleFont);
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sloganPanel.add(gifLabel);
        sloganPanel.add(sloganLabel);


        jFrame.add(sloganPanel, BorderLayout.WEST);
        jFrame.add(whitePanel, BorderLayout.CENTER);

        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logInButton) {
            if(adminName.getText().isEmpty() && adminPassword.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please fill out both fields");
            } else if (adminName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null , "Please Enter username");
            } else if (adminPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null , "Please Enter password");
            } else{
                String username = adminName.getText();
                String password = adminPassword.getText();
                if (loginRequired(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful. Redirect to new page");
                    jFrame.dispose();
                    new CustomerWindow();

                } else {
                    JOptionPane.showMessageDialog(null, "User Does not Exist. Kindly signup");
                    jFrame.dispose();
                    new SignUp();
                }
            }

        } else if (e.getSource() == backButton) {
            jFrame.dispose();
            new SignUp();

        }
    }
    public boolean loginRequired(String userName, String password) {

        try {

            PreparedStatement preparedStatement = Globals.connection.prepareStatement("SELECT customer_name , password  FROM customer WHERE customer_name = ? and password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("customer_name");
                String pass = resultSet.getString("password");
                if (username.equalsIgnoreCase(userName)&&pass.equalsIgnoreCase(password)) {
                    return true;
                } else {
                    return false;
                }
            }
//
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
