import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginPanelAdmin extends FrontPage implements ActionListener {
    Font titleFont = new Font("Cooper Black", Font.BOLD, 32);

    JFrame jFrame ;
    JPanel sloganPanel, whitePanel, blankPanel;
    JLabel sloganLabel, loginLabel, userLabel, passwordLabel;
    JButton logInButton;
    JTextField adminName, adminPassword;
    private Font font = new Font("Consolas", Font.BOLD, 24);
    private Font buttonFont = new Font("Consolas", Font.BOLD, 18);


    LoginPanelAdmin() throws SQLException, ClassNotFoundException {
        super();
        jFrame = new JFrame("Client");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(1050, 600);
        jFrame.setLayout(new BorderLayout());

        whitePanel = new JPanel();
        whitePanel.setBackground(Color.white);
        whitePanel.setLayout(null);

        loginLabel = new JLabel("Login Admin");
        loginLabel.setFont(font);

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
        logInButton.setBounds(adminName.getX()+45, 320, 100, 40);
        logInButton.addActionListener(this);
        whitePanel.add(logInButton);

        ImageIcon backgroundIcon = new ImageIcon("Images/Netflix.png");
        Image scaledImage = backgroundIcon.getImage().getScaledInstance(
                jFrame.getWidth() / 3, jFrame.getHeight() /3, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel gifLabel = new JLabel(scaledIcon);
        gifLabel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());


//        backButton = new JButton("SignUp");
//        backButton.setBackground(Color.decode("#1c1515"));
//        backButton.setForeground(Color.WHITE);
//        backButton.setFont(buttonFont);
//        backButton.setBounds(250, 320, 100, 40);
//        backButton.addActionListener(this);
//
//        whitePanel.add(backButton);



        sloganPanel = new JPanel();
        sloganPanel.setLayout(new GridLayout(4, 1));
        sloganPanel.setBackground(Color.black);
        sloganPanel.setBounds(0 ,0 ,jFrame.getWidth()/4 , jFrame.getHeight());
        sloganPanel.setPreferredSize(new Dimension(jFrame.getWidth() / 3, jFrame.getHeight()));

        blankPanel = new JPanel();
        blankPanel.setBackground(Color.black);
        sloganPanel.add(blankPanel);

        sloganPanel.add(gifLabel);


        sloganLabel = new JLabel("MovieMania");
        sloganLabel.setFont(titleFont);
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
                if (username.equals("sadia") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Login Successful. Redirect to new page");
                    frame.dispose();
                    new AdminWindow();
                } else {
                    JOptionPane.showMessageDialog(null, "User Does not Exist. Kindly signup");
                }
            }

        }
    }

    private Font getCustomFont() {
        // Customize the font as needed
        Font baseFont = new Font("Sans-serif", Font.BOLD, 24);

        // You can add more attributes if necessary
        Map<TextAttribute, Float> attributes = new HashMap<>();
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);

        // Apply the attributes to the font
        return baseFont.deriveFont(attributes);
    }


}