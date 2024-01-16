import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SignUp extends Component implements ActionListener{
    String userName , userPassword , carName , licensePlate ;
    JFrame jFrame ;
    JLabel singUpLabel ,  usernameLabel , passwordLabel , NameLabel , ConfirmpasswordLabel , logoLabel , sloganLabel;
    JPanel signUpPanel  , buttonPanel , iconPanel , blankPanel;
    JTextField usernameField , passwordField , NameField , ConfirmpasswordField;
    Font titleFont = new Font("Cooper Black", Font.BOLD, 32);

    JButton signUpButton ;
    private Font font = new Font("Consolas", Font.BOLD, 28);
    private Font buttonFont = new Font("Consolas", Font.BOLD, 18);
    private static final Color SKY_BLUE_COLOR = new Color(135, 206, 235);
    private static final Color WHITE_COLOR = Color.WHITE;
    SignUp(String n ){

    }
    SignUp() {
        jFrame = new JFrame("Signup Panel");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(1050, 600);
        jFrame.setLayout(new BorderLayout());

        // Create icon panel (25% width)
        iconPanel = new JPanel();
        iconPanel.setLayout(new GridLayout(4, 1));
        iconPanel.setBackground(Color.BLACK);
        iconPanel.setPreferredSize(new Dimension(jFrame.getWidth() / 3, jFrame.getHeight()));

        blankPanel = new JPanel();
        blankPanel.setBackground(Color.BLACK);
        iconPanel.add(blankPanel);

        ImageIcon backgroundIcon = new ImageIcon("Images/Netflix.png");
        Image scaledImage = backgroundIcon.getImage().getScaledInstance(
                jFrame.getWidth() / 3, jFrame.getHeight() /3, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel gifLabel = new JLabel(scaledIcon);
        gifLabel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
        iconPanel.add(gifLabel);

        sloganLabel = new JLabel("MovieMania");
        sloganLabel.setFont(titleFont);
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconPanel.add(sloganLabel);

        // Create signup panel (75% width)
        signUpPanel = new JPanel();
        signUpPanel.setBackground(WHITE_COLOR);
        signUpPanel.setLayout(null);

        JLabel signUp = new JLabel("SignUp");
        signUp.setForeground(Color.BLACK);
        signUp.setBackground(SKY_BLUE_COLOR);
        signUp.setFont(Globals.headingFont);
        signUp.setBounds(250, 100, 200, 30);
        signUpPanel.add(signUp);

        NameLabel = new JLabel("Name:");
        NameLabel.setBounds(105, signUp.getY()+60, 120, 30);
        NameLabel.setFont(buttonFont);
        signUpPanel.add(NameLabel);

        NameField = new JTextField();
        NameField.setBounds(225, signUp.getY()+60, 200, 30);
        NameField.addActionListener(this);
        signUpPanel.add(NameField);

        usernameLabel = new JLabel("Email:");
        usernameLabel.setFont(buttonFont);
        usernameLabel.setBounds(NameLabel.getX(), NameLabel.getY()+60, 120, 30);
        signUpPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(NameField.getX(), NameField.getY() +60 ,200, 30);
        usernameField.addActionListener(this);
        signUpPanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(NameLabel.getX(), usernameLabel.getY()+60, 150, 30);
        passwordLabel.setFont(buttonFont);
        signUpPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(usernameField.getX(), usernameField.getY()+60, 200, 30);
        passwordField.addActionListener(this);
        signUpPanel.add(passwordField);

        ConfirmpasswordLabel = new JLabel("Confirm :");
        ConfirmpasswordLabel.setBounds(NameLabel.getX(), passwordLabel.getY()+60, 150, 30);
        ConfirmpasswordLabel.setFont(buttonFont);
        signUpPanel.add(ConfirmpasswordLabel);

        ConfirmpasswordField = new JPasswordField();
        ConfirmpasswordField.setBounds(passwordField.getX(), passwordField.getY()+60, 200, 30);
        ConfirmpasswordField.addActionListener(this);
        signUpPanel.add(ConfirmpasswordField);

        signUpButton = new JButton("Submit");
        signUpButton.setBounds(signUp.getX(), ConfirmpasswordField.getY()+60, 130, 40);
        signUpButton.setBackground(Color.black);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(buttonFont);

        signUpButton.addActionListener(this);

        signUpPanel.add(signUpButton);
        jFrame.add(iconPanel, BorderLayout.WEST);
        jFrame.add(signUpPanel, BorderLayout.CENTER);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(e.getSource() == signUpButton){
            registerUser();
        }

    }
    public void registerUser() {
        String name = NameField.getText();
        String email = usernameField.getText();
        String password = String.valueOf(passwordField.getText());
        String confirmPass = String.valueOf(ConfirmpasswordField.getText());
        String customerId = null;

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(name);
        System.out.println(email);
//        user = addUserToDatabase(customerId,name, email, password);
//
//        if (user != null) {
//            jFrame.dispose();
//        } else {
//            JOptionPane.showMessageDialog(this,
//                    "Failed to register new user",
//                    "Try again",
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }

//    public User user;
//
//    private User addUserToDatabase(String customerId,String name, String email, String password) {
//        User user = null;
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
//
//            // Get the current customer count from the database
//            Statement countStatement = conn.createStatement();
//            ResultSet countResult = countStatement.executeQuery("SELECT COUNT(*) FROM customer");
//            countResult.next();
//            int customerCount = countResult.getInt(1) + 1; // Increment by 1 for the new customer
//            countStatement.close();
//
//            // Format the customer ID as "S001", "S002", etc.
//            String formattedCustomerId = String.format("C%03d", customerCount);
//
//            // Use PreparedStatement with RETURN_GENERATED_KEYS
//            String sql = "INSERT INTO customer(Customer_ID, Customer_name, Customer_email, password) VALUES (?, ?, ?, ?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            // Set parameters
//            System.out.println(formattedCustomerId);
//            preparedStatement.setString(1, formattedCustomerId);
//            preparedStatement.setString(2, name);
//            preparedStatement.setString(3, email);
//            preparedStatement.setString(4, password);
//
//            // Execute the update
//            int addedRows = preparedStatement.executeUpdate();
//
//            // Check if rows were added
//            if (addedRows > 0) {
//                user = new User();
//                user.customerId = formattedCustomerId;
//                user.name = name;
//                user.email = email;
//                user.password = password;
//            }
//
//
//            // Close resources
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

}