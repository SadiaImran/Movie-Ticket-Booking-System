import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrontPage implements ActionListener {
    private final JLayeredPane jLayeredPane;
    JButton customerButton;
    JButton adminButton;

     Font titleFont = new Font("Cooper Black", Font.BOLD, 56);
    JFrame frame = new JFrame("MovieMania");
    private Font font = new Font("Fraunches", Font.BOLD, 18);

    FrontPage() {
        frame = new JFrame("Front Page");
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLayeredPane = new JLayeredPane();
        jLayeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        ImageIcon backgroundIcon = new ImageIcon("Images/seats.jpg");
        Image scaledImage = backgroundIcon.getImage().getScaledInstance(
                frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel gifLabel = new JLabel(scaledIcon);
        gifLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        jLayeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);



        JLabel titleLabel = new JLabel("MovieMania");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds((frame.getWidth()) / 3, (frame.getHeight()) / 14, 600, 100);
        jLayeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);

        adminButton = new JButton("Admin");
        customerButton = new JButton("Customer");

        // Set the position and size of the buttons
        adminButton.setBounds(titleLabel.getX() + 120 , titleLabel.getY()+300, 120, 50);
        customerButton.setBounds(titleLabel.getX() + 120, adminButton.getY()+80, 120, 50);

        // Set button colors, font, etc.
        adminButton.setBackground(Color.decode("#561a20"));
        adminButton.setForeground(Color.WHITE);
        adminButton.addActionListener(this);
        adminButton.setFont(font);

        customerButton.setBackground(Color.decode("#561a20"));
        customerButton.setForeground(Color.WHITE);
        customerButton.addActionListener(this);
        customerButton.setFont(font);

        // Add buttons to the layered pane
        jLayeredPane.add(adminButton, JLayeredPane.PALETTE_LAYER);
        jLayeredPane.add(customerButton, JLayeredPane.PALETTE_LAYER);

        frame.add(jLayeredPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }





    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            frame.dispose();
            try {
                new LoginPanelAdmin() ;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == customerButton) {
            frame.dispose();
            try {
                new LogInPanel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        new FrontPage();
    }

}