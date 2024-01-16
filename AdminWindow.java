import com.mongodb.MongoException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminWindow implements ActionListener {
    public final JFrame jFrame;
    private  ArrayList<String> updateChoicesFilters  , updateNumberFilters;
    private JComboBox insertComboBox , updateComboBox , updateChoicesComboBox   , updateNumberComboBox  , deleteComboBox;
    private  JPanel adminPanel , searchCustomerPanel , insertPanel , insertCustomerPanel , updatePanel , deletePanel;
    private Font font = new Font("Cooper Black", Font.BOLD, 50);
    private Font buttonFont = new Font("Consolas", Font.BOLD, 18);
    private static final Color SKY_BLUE_COLOR = new Color(140, 210, 240).darker();
    private static final Color WHITE_COLOR = Color.WHITE;
    private  JButton deleteBookingButton, deleteShowButton,deleteGenreButton,deleteMovieButton, delete,updatePriceButton,updateRatingsButton,updateShowMovieButton,displayButton , searchButton , insertButton , updateShowButton , logInButton  , insertCustomer , updateButton ,genreSubmit, logInButtonGenre;
    private String query;
    private DefaultTableModel defaultTable;
    private JTable moviesTable;
    private JTextField bookingTextField
            ,showTextField,genreNameTextField,movieNameTextField,
            updatePriceField,textField , movieName , updateRatingsField,pgRating ,
            industry , duration , story , price , movieGenre , updateShowMovieField ,
            genreMovie , date , month , year , hour , minute , second;
    private JLabel updatePriceLabel,movieNameLabel , pgRatingLabel , industryLabel , durationLabel , storyLabel , priceLabel , movieNameGenreLabel , dateLabel,  genreNameMovieLabel;

    public AdminWindow() {
        jFrame = new JFrame("Admin Window");
        jFrame.setSize(1050, 600);
        jFrame.setLayout(null);
        jFrame.setBackground(Color.white);
        adminPanel = new JPanel(null);
        adminPanel.setBounds(0 ,0 ,jFrame.getWidth()/ 3 , jFrame.getHeight());
//        adminPanel.setPreferredSize(new Dimension(jFrame.getWidth() , jFrame.getHeight()));

        adminPanel.setBackground(Color.black);

        // Add admin icon and label
        ImageIcon adminIcon = new ImageIcon("Images/icon2.png");
        JLabel adminLabel = new JLabel("Admin", JLabel.CENTER);
        adminLabel.setIcon(adminIcon);
        adminLabel.setFont(Globals.headingFont);
        adminLabel.setForeground(Color.WHITE);


        adminLabel.setBounds(10, 30, adminPanel.getWidth() - 40, 100);

        adminPanel.add(adminLabel);

        // Add display button
        displayButton = new JButton("Display Movies");
        displayButton.setBackground(Color.WHITE.brighter());
        displayButton.setForeground(Color.BLACK);
        displayButton.addActionListener(this);
        displayButton.setBounds(50, 160, adminPanel.getWidth() - 120, 30);
        adminPanel.add(displayButton);

        // Add search button
        searchButton = new JButton("Search Movie");
        searchButton.setBackground(Color.WHITE.brighter());
        searchButton.setForeground(Color.BLACK);
        searchButton.addActionListener(this);
        searchButton.setBounds(50, displayButton.getY()+60, adminPanel.getWidth() - 120, 30);
        adminPanel.add(searchButton);

        insertButton = new JButton("Insert Movie");
        insertButton.setBackground(Color.WHITE.brighter());
        insertButton.setForeground(Color.BLACK);
        insertButton.addActionListener(this);
        insertButton.setBounds(50, searchButton.getY()+60, adminPanel.getWidth() - 120, 30);
        adminPanel.add(insertButton);

        delete = new JButton("Delete");
        delete.setBackground(Color.WHITE.brighter());
        delete.setForeground(Color.BLACK);
        delete.addActionListener(this);
        delete.setBounds(50, insertButton.getY()+60, adminPanel.getWidth() - 120, 30);
        adminPanel.add(delete);

        updateButton = new JButton("Update");
        updateButton.setBackground(Color.WHITE.brighter());
        updateButton.setForeground(Color.BLACK);
        updateButton.addActionListener(this);
        updateButton.setBounds(50, delete.getY()+60, adminPanel.getWidth() - 120, 30);
        adminPanel.add(updateButton);

        jFrame.add(adminPanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == displayButton) {
            clearComponents();
//            query = "Select * from movies";
//            createTable(query);
            createTableForMongo();
        }
        else if (e.getSource() == searchButton) {
            clearComponents();
            if (searchCustomerPanel == null) {
                searchCustomerPanel = new JPanel();

            }
            searchCustomerPanel.setBackground(Color.white);
            searchCustomerPanel.setBounds(adminPanel.getWidth() , 0, jFrame.getWidth() - adminPanel.getWidth() - 20, jFrame.getHeight() - 20);
            searchCustomerPanel.setLayout(null);

            JLabel searchLabel = new JLabel("Enter Movie :");
            searchLabel.setFont(Globals.headingFont);
            searchLabel.setBounds(250, 50, 200, 30);
            searchCustomerPanel.add(searchLabel);

            textField = new JTextField();
            textField.setBounds(searchLabel.getX() - 60, searchLabel.getY()+60, 300, 40);
            searchCustomerPanel.add(textField);

            JButton searchSubmitButton = new JButton("Search");
            searchSubmitButton.setBounds(searchLabel.getX() + 30, textField.getY()+70, 100, 40);
            searchSubmitButton.addActionListener(this);
            searchSubmitButton.setBackground(Color.decode("#1c1515"));
            searchSubmitButton.setForeground(Color.WHITE);
            searchSubmitButton.setFont(buttonFont);
            searchCustomerPanel.add(searchSubmitButton);
            addRepaintRevalidate(searchCustomerPanel);

        }else if (e.getActionCommand().equals("Search")) {
            clearComponents();
            String searchName = textField.getText();
            PreparedStatement preparedStatement = null;
            try {
                String movieNameToSearch = searchName.toUpperCase() + "%";
                preparedStatement = Globals.connection.prepareStatement("SELECT * FROM movies WHERE UPPER(TITLE) LIKE ?");
                preparedStatement.setString(1, movieNameToSearch );
                ResultSet resultSet = preparedStatement.executeQuery();
                defaultTable = Globals.buildDefaultTable(resultSet);
                moviesTable = new JTable(defaultTable);
                moviesTable.setBounds(adminPanel.getWidth() , 110 , jFrame.getWidth() - adminPanel.getWidth() , jFrame.getHeight()) ;
                JScrollPane scrollPane = new JScrollPane(moviesTable);
                scrollPane.setBounds(adminPanel.getWidth(), 0, jFrame.getWidth()-adminPanel.getWidth() - 10 , jFrame.getHeight());
                jFrame.add(scrollPane);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }}
        else if(e.getSource() == delete){
            clearComponents();
            if (deletePanel == null) {
                deletePanel = new JPanel();

            }
            deletePanel.setBackground(Color.white);
            deletePanel.setBounds(adminPanel.getWidth() , 0, jFrame.getWidth() - adminPanel.getWidth() - 20, jFrame.getHeight() - 20);
            deletePanel.setLayout(null);


            Label filterLabel = new Label("Choose Table to Delete: ");
            filterLabel.setBounds(200, 50, 300, 30);
            filterLabel.setFont(Globals.headingFont);
            deletePanel.add(filterLabel);
            String[] filters = {"Movie", "Genre","Show","Booking"};
            deleteComboBox = new JComboBox(filters);
            deleteComboBox.addActionListener(this);
            deleteComboBox.setBackground(Color.WHITE);
            deleteComboBox.setForeground(Color.black);

            deleteComboBox.setBounds(filterLabel.getX() -  20, filterLabel.getY()+60, 300, 40);
            deletePanel.add(deleteComboBox);
            deletePanel.setVisible(true);
            jFrame.add(deletePanel);
            addRepaintRevalidate(deletePanel);
        }
        else if(e.getSource() == deleteComboBox){
            clearComponentsExceptComboBox(deletePanel);

            if (deleteComboBox.getSelectedItem().equals("Movie")){
                clearComponentsExceptComboBox(deletePanel);

                // Add JLabel and JTextField for movie name input
                JLabel nameLabel = new JLabel("Enter the name of the movie to delete:");
                nameLabel.setFont(Globals.titleFont);
                nameLabel.setBounds(deleteComboBox.getX() -30, deleteComboBox.getY()+80, 400, 30);
                deletePanel.add(nameLabel);

                movieNameTextField = new JTextField();
                movieNameTextField.setBounds(nameLabel.getX() +55, nameLabel.getY()+60, 250, 35);
                deletePanel.add(movieNameTextField);

                deleteMovieButton = new JButton("Delete Movie");
                deleteMovieButton.setBounds(movieNameTextField.getX()+30, movieNameTextField.getY()+70, 180, 45);
                deleteMovieButton.addActionListener(this);
                deleteMovieButton.setBackground(Color.decode("#1c1515"));
                deleteMovieButton.setForeground(Color.WHITE);
                deleteMovieButton.setFont(buttonFont);
                deleteMovieButton.setFocusable(false);
                deletePanel.add(deleteMovieButton);

                // Repaint and revalidate
                addRepaintRevalidate(deletePanel);

            }
            else if (deleteComboBox.getSelectedItem().equals("Genre")){

                clearComponentsExceptComboBox(deletePanel);

                JLabel nameLabel = new JLabel("Enter the name of genre to delete:");
                nameLabel.setFont(Globals.titleFont);
                nameLabel.setBounds(deleteComboBox.getX() -30, deleteComboBox.getY()+80, 400, 30);
                deletePanel.add(nameLabel);

                genreNameTextField = new JTextField();
                genreNameTextField.setBounds(nameLabel.getX() +55, nameLabel.getY()+60, 250, 35);
                deletePanel.add(genreNameTextField);

                deleteGenreButton = new JButton("Delete Genre");
                deleteGenreButton.setBounds(genreNameTextField.getX()+30, genreNameTextField.getY()+70, 180, 45);
                deleteGenreButton.addActionListener(this);
                deleteGenreButton.setBackground(Color.decode("#1c1515"));
                deleteGenreButton.setForeground(Color.WHITE);
                deleteGenreButton.setFont(buttonFont);
                deleteGenreButton.setFocusable(false);
                deletePanel.add(deleteGenreButton);

                // Repaint and revalidate
                addRepaintRevalidate(deletePanel);

            }
            else if (deleteComboBox.getSelectedItem().equals("Show")){

                clearComponentsExceptComboBox(deletePanel);

                JLabel nameLabel = new JLabel("Enter Show ID to delete:");
                nameLabel.setFont(Globals.titleFont);
                nameLabel.setBounds(deleteComboBox.getX() -30, deleteComboBox.getY()+80, 400, 30);
                deletePanel.add(nameLabel);

                showTextField = new JTextField();
                showTextField.setBounds(nameLabel.getX() +55, nameLabel.getY()+60, 250, 35);
                deletePanel.add(showTextField);

                deleteShowButton = new JButton("Delete Show");
                deleteShowButton.setBounds(genreNameTextField.getX()+30, genreNameTextField.getY()+70, 180, 45);
                deleteShowButton.addActionListener(this);
                deleteShowButton.setBackground(Color.decode("#1c1515"));
                deleteShowButton.setForeground(Color.WHITE);
                deleteShowButton.setFont(buttonFont);
                deleteShowButton.setFocusable(false);
                deletePanel.add(deleteShowButton);

                // Repaint and revalidate
                addRepaintRevalidate(deletePanel);

            }
            else if (deleteComboBox.getSelectedItem().equals("Booking")){
                clearComponentsExceptComboBox(deletePanel);

                JLabel nameLabel = new JLabel("Enter Booking ID to delete:");
                nameLabel.setFont(Globals.titleFont);
                nameLabel.setBounds(deleteComboBox.getX() -30, deleteComboBox.getY()+80, 400, 30);

                deletePanel.add(nameLabel);

                bookingTextField = new JTextField();
                bookingTextField.setBounds(nameLabel.getX() +55, nameLabel.getY()+60, 250, 35);
                deletePanel.add(bookingTextField);

                deleteBookingButton = new JButton("Delete Booking");
                deleteBookingButton.setBounds(bookingTextField.getX()+30, bookingTextField.getY()+70, 180, 45);
                deleteBookingButton.addActionListener(this);
                deleteBookingButton.setBackground(Color.decode("#1c1515"));
                deleteBookingButton.setForeground(Color.WHITE);
                deleteBookingButton.setFont(buttonFont);
                deleteBookingButton.setFocusable(false);
                deletePanel.add(deleteBookingButton);

                // Repaint and revalidate
                addRepaintRevalidate(deletePanel);

            }

        }
        else if(e.getSource() == deleteMovieButton){
            clearComponents();
            String movieName = movieNameTextField.getText();
            String selectedMovieId = Globals.fetchMovieId(movieName);
            boolean success = Globals.updateDeleteId("show",selectedMovieId,"MOVIE_ID");
            boolean success1 = Globals.updateDeleteId("moviegenre",selectedMovieId,"MOVIE_ID");
            boolean success2 = Globals.updateDeleteId("movies",selectedMovieId,"MOVIE_ID");
            if (success && success1 && success2) {
                JOptionPane.showMessageDialog(jFrame, "Movie deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to delete the movie. Please try again.");
            }

            movieNameTextField.setText("");
        }
        else if(e.getSource() == deleteGenreButton){
            clearComponents();
            String genreName = genreNameTextField.getText();
            String selectedGenreId = Globals.fetchGenreId(genreName);
            boolean success = Globals.updateDeleteId("moviegenre",selectedGenreId,"GENRE_ID");
            boolean success1 = Globals.updateDeleteId("genre",selectedGenreId,"GENRE_ID");
            if (success && success1) {
                JOptionPane.showMessageDialog(jFrame, "Genre deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to delete genre. Please try again.");
            }

            genreNameTextField.setText("");
        }
        else if(e.getSource() == deleteShowButton){
            clearComponents();
            String ShowID = showTextField.getText();
            boolean success = Globals.updateDeleteId("booking",ShowID,"SHOW_ID");
            boolean success1 = Globals.updateDeleteId("seat",ShowID,"SHOW_ID");
            boolean success2 = Globals.updateDeleteId("show",ShowID,"SHOW_ID");
            if (success && success1 && success2) {
                JOptionPane.showMessageDialog(jFrame, "Show deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to delete Show. Please try again.");
            }

            showTextField.setText("");
        }
        else if(e.getSource() == deleteBookingButton){
            clearComponents();
            String bookingID = bookingTextField.getText();
            boolean success = Globals.updateDeleteId("seat",bookingID,"BOOKING_ID");
            boolean success1 = Globals.updateDeleteId("booking",bookingID,"BOOKING_ID");
            if (success && success1) {
                JOptionPane.showMessageDialog(jFrame, "Booking deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to delete booking. Please try again.");
            }

            bookingTextField.setText("");
        }
        else if (e.getSource() == updateButton) {
            clearComponents();
            if (updatePanel == null) {
                updatePanel = new JPanel();
            }
            updatePanel.setBackground(Color.white);
            updatePanel.setBounds(adminPanel.getWidth() , 0, jFrame.getWidth() - adminPanel.getWidth() - 20, jFrame.getHeight() - 20);
            updatePanel.setLayout(null);

            Label filterLabel = new Label("Choose Table : ");
            filterLabel.setBounds(30 , 20 , 150 ,30);
//            filterLabel.setFont( buttonFont);
            filterLabel.setFont(Globals.buttonFont);
            updatePanel.add(filterLabel);

            String[] updateFilters = {"Show", "Movies", "Genre"};
            updateComboBox = new JComboBox<>(updateFilters);
            updateComboBox.addActionListener(this);
            updateComboBox.setBounds(30, 60, 150, 30);
            updateComboBox.setBackground(WHITE_COLOR);
            updateComboBox.setForeground(Color.black);
            updatePanel.add(updateComboBox);
            updatePanel.setVisible(true);

            Label filterLabelUpdate = new Label("Choose Option : ");
            filterLabelUpdate.setBounds(updateComboBox.getX()+200, 20, 150, 30);
            filterLabelUpdate.setFont( buttonFont);
            updatePanel.add(filterLabelUpdate);

            updateChoicesFilters  = new ArrayList<>();
            updateChoicesComboBox = new JComboBox <> (updateChoicesFilters.toArray());
            updateChoicesComboBox.addActionListener(this);
            updateChoicesComboBox.setBounds(updateComboBox.getX()+200, 60, 150, 30);
            updateChoicesComboBox.setBackground(WHITE_COLOR);
            updateChoicesComboBox.setForeground(Color.black);
            updatePanel.add(updateChoicesComboBox);


            Label thirdComboBoxLabel = new Label("Choose Number : ");
            thirdComboBoxLabel.setBounds(updateChoicesComboBox.getX() + 200, 20, 150, 30);
            thirdComboBoxLabel.setFont(buttonFont);
            updatePanel.add(thirdComboBoxLabel);

            updateNumberFilters  = new ArrayList<>();
            updateNumberComboBox = new JComboBox <>(updateNumberFilters.toArray());
            updateNumberComboBox.setBounds(updateChoicesComboBox.getX() + 200, 60, 180, 30);
            updateNumberComboBox.setFont(buttonFont);
            updateNumberComboBox.setBackground(WHITE_COLOR);
            updateNumberComboBox.setForeground(Color.black);
            updatePanel.add(updateNumberComboBox);
            jFrame.add(updatePanel);
            addRepaintRevalidate(updatePanel);

        } else if (e.getSource() == updateComboBox) {
            clearComponentsExceptComboBox(updatePanel);
            int count  = 0 ;
            try {
                count = Globals.countPrimaryKey((String) updateComboBox.getSelectedItem());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if(updateComboBox.getSelectedItem().equals("Show")){
                clearComponentsExceptComboBox(updatePanel);

                updateNumberFilters.clear();
                for(int i = 1; i <=count; i++ ){
                    updateNumberFilters.add(String.valueOf(i));
                }
            } else if (updateComboBox.getSelectedItem().equals("Movies")) {
                clearComponentsExceptComboBox(updatePanel);
                updateNumberFilters.clear();
                ArrayList<String> moviesNames = Globals.fetchMovieNames("Title","Movies") ;
                int size = moviesNames.size();
                for(int i = 0; i <size; i++ ){
                    updateNumberFilters.add(moviesNames.get(i));
                }

            }
            else if (updateComboBox.getSelectedItem().equals("Genre")) {
                clearComponentsExceptComboBox(updatePanel);
                updateNumberFilters.clear();
                ArrayList<String> moviesNames = Globals.fetchMovieNames("GENRE_NAME","GENRE") ;
                int size = moviesNames.size();
                for(int i = 0; i <size; i++ ){
                    updateNumberFilters.add(moviesNames.get(i));
                }

            }

            updateNumberComboBox.setModel(new DefaultComboBoxModel<>(updateNumberFilters.toArray()));
            if(updateComboBox.getSelectedItem().equals("Show")){
                clearComponentsExceptComboBox(updatePanel);
                updateChoicesFilters.clear();
                updateChoicesFilters.add("Show Time");
                updateChoicesFilters.add("Movie Title");
                updateChoicesComboBox.setModel(new DefaultComboBoxModel<>(updateChoicesFilters.toArray()));
            }
            else if (updateComboBox.getSelectedItem().equals("Movies")) {
                clearComponentsExceptComboBox(updatePanel);
                updateChoicesFilters.clear();
                updateChoicesFilters.add("Rating");
                updateChoicesFilters.add("Price");
                updateChoicesComboBox.setModel(new DefaultComboBoxModel<>(updateChoicesFilters.toArray()));
            }

        } else if (e.getSource() == updateChoicesComboBox) {
            clearComponentsExceptComboBox(updatePanel);
            if(updateChoicesComboBox.getSelectedItem().equals("Show Time")){
                clearComponentsExceptComboBox(updatePanel);

                Label dateLabel = new Label(" DD ");
                dateLabel.setBounds(100, 150, 50, 30);
                dateLabel.setFont(buttonFont);
                updatePanel.add(dateLabel);

                date = new JTextField();
                date.setBounds(100, dateLabel.getY() + 30, 40, 30);
                date.addActionListener(this);
                updatePanel.add(date);

                Label dateColon = new Label(" - ");
                dateColon.setBounds(date.getX() + 40, dateLabel.getY() + 40, 30, 10);
                dateColon.setFont(buttonFont);
                dateColon.setForeground(Color.black);
                updatePanel.add(dateColon);

                Label monthLabel = new Label(" MM ");
                monthLabel.setBounds(dateLabel.getX() + 70, 150, 50, 30);
                monthLabel.setFont(buttonFont);
                updatePanel.add(monthLabel);

                month = new JTextField();
                month.setBounds(dateColon.getX() + 35, date.getY(), 40, 30);
                month.addActionListener(this);
                updatePanel.add(month);

                Label monthColon = new Label("-");
                monthColon.setBounds(month.getX() + 45, monthLabel.getY() + 30, 10, 30);
                monthColon.setForeground(Color.black);
                monthColon.setFont(buttonFont);
                updatePanel.add(monthColon);

                Label yearLabel = new Label(" YY ");
                yearLabel.setBounds(monthLabel.getX() + 70, dateLabel.getY(), 50, 30);
                yearLabel.setFont(buttonFont);
                updatePanel.add(yearLabel);

                year = new JTextField();
                year.setBounds(monthColon.getX() + 25, month.getY(), 40, 30);
                year.addActionListener(this);
                updatePanel.add(year);

                Label yearColon = new Label("");
                yearColon.setBounds(year.getX() + 45, yearLabel.getY() + 30, 10, 30);
                yearColon.setForeground(Color.black);
                yearColon.setFont(buttonFont);
                updatePanel.add(yearColon);

                Label hourLabel = new Label(" HH ");
                hourLabel.setBounds(yearLabel.getX() + 70 + 20 , dateLabel.getY(), 50, 30);
                hourLabel.setFont(buttonFont);
                updatePanel.add(hourLabel);

                hour = new JTextField();
                hour.setBounds(yearColon.getX() + 25 + 20, year.getY(), 40, 30);
                hour.addActionListener(this);
                updatePanel.add(hour);

                Label hourColon = new Label(":");
                hourColon.setBounds(hour.getX() + 45, hourLabel.getY() + 30, 10, 30);
                hourColon.setFont(buttonFont);
                hourColon.setForeground(Color.black);
                updatePanel.add(hourColon);

                Label minuteLabel = new Label(" MM ");
                minuteLabel.setBounds(hourLabel.getX() + 70 , dateLabel.getY(), 50, 30);
                minuteLabel.setFont(buttonFont);
                updatePanel.add(minuteLabel);

                minute = new JTextField();
                minute.setBounds(hourColon.getX() + 25 , hour.getY(), 40, 30);
                minute.addActionListener(this);
                updatePanel.add(minute);

                Label minuteColon = new Label(":");
                minuteColon.setBounds(minute.getX() + 45, minuteLabel.getY() +  30, 10, 30);
                minuteColon.setForeground(Color.black);
                minuteColon.setFont(buttonFont);
                updatePanel.add(minuteColon);

                Label secondsLabel = new Label(" SS ");
                secondsLabel.setBounds(minuteLabel.getX() + 70  , dateLabel.getY(), 50, 30);
                secondsLabel.setFont(buttonFont);
                updatePanel.add(secondsLabel);

                second = new JTextField();
                second.setBounds(minuteColon.getX() + 25 , minute.getY(), 40, 30);
                second.addActionListener(this);
                updatePanel.add(second);

                Label secondColon = new Label("");
                secondColon.setBounds(second.getX() + 45, minuteLabel.getY() + 40, 10, 10);
                secondColon.setForeground(Color.black);
                secondColon.setFont(buttonFont);
                updatePanel.add(secondColon);

                updateShowButton = new JButton("Update");
                updateShowButton.setBackground(Color.black);
                updateShowButton.setForeground(Color.WHITE);
                updateShowButton.setFont(buttonFont);
                updateShowButton.setBounds(year.getX() +10, year.getY()+80, 130, 40);
                updateShowButton.addActionListener(this);
                updatePanel.add(updateShowButton);

            } else if (updateChoicesComboBox.getSelectedItem().equals("Movie Title")) {
                clearComponentsExceptComboBox(updatePanel);

                Label updateMovieLabel = new Label(" Title ");
                updateMovieLabel.setFont(Globals.headingFont);
                updateMovieLabel.setBounds(updateChoicesComboBox.getX()+20, updateChoicesComboBox.getY()+70, 120, 40);
                updatePanel.add(updateMovieLabel);

                updateShowMovieField = new JTextField();
                updateShowMovieField.setBounds(updateMovieLabel.getX() -60, updateMovieLabel.getY()+55, 250, 40);
                updateShowMovieField.addActionListener(this);
                updatePanel.add(updateShowMovieField);

                updateShowMovieButton = new JButton("Update");
                updateShowMovieButton.setBackground(Color.black);
                updateShowMovieButton.setForeground(Color.WHITE);
                updateShowMovieButton.setFont(buttonFont);
                updateShowMovieButton.setBounds(updateShowMovieField.getX() +60 , updateShowMovieField.getY()+70, 100, 40);
                updateShowMovieButton.addActionListener(this);
                updatePanel.add(updateShowMovieButton);


            } else if (updateChoicesComboBox.getSelectedItem().equals("Price")) {
                clearComponentsExceptComboBox(updatePanel);
                Label updatePriceLabel = new Label(" Price ");
                updatePriceLabel.setFont(Globals.headingFont);
                updatePriceLabel.setBounds(updateChoicesComboBox.getX()+15, updateChoicesComboBox.getY()+55, 120, 40);
                updatePanel.add(updatePriceLabel);

                updatePriceField = new JTextField();
                updatePriceField.setBounds(updatePriceLabel.getX() -50, updatePriceLabel.getY()+55, 200, 40);
                updatePriceField.addActionListener(this);
                updatePanel.add(updatePriceField);

                updatePriceButton = new JButton("Update");
                updatePriceButton.setBackground(Color.black);
                updatePriceButton.setForeground(Color.WHITE);
                updatePriceButton.setFont(buttonFont);
                updatePriceButton.setBounds(updatePriceField.getX() +60 , updatePriceField.getY()+75, 100, 40);
                updatePriceButton.addActionListener(this);
                updatePanel.add(updatePriceButton);

            } else if (updateChoicesComboBox.getSelectedItem().equals("Rating")) {
                clearComponentsExceptComboBox(updatePanel);
                Label updateRatingsLabel = new Label(" Ratings ");
                updateRatingsLabel.setFont(buttonFont);
                updateRatingsLabel.setBounds(updateChoicesComboBox.getX()+15, updateChoicesComboBox.getY()+50, 80, 40);
                updatePanel.add(updateRatingsLabel);

                updateRatingsField = new JTextField();
                updateRatingsField.setBounds(updateRatingsLabel.getX() -60, updateRatingsLabel.getY()+45, 200, 40);
                updateRatingsField.addActionListener(this);
                updatePanel.add(updateRatingsField);

                updateRatingsButton = new JButton("Update");
                updateRatingsButton.setBackground(SKY_BLUE_COLOR);
                updateRatingsButton.setForeground(Color.WHITE);
                updateRatingsButton.setFont(buttonFont);
                updateRatingsButton.setBounds(updateRatingsField.getX() +50 , updateRatingsField.getY()+55, 100, 40);
                updateRatingsButton.addActionListener(this);
                updatePanel.add(updateRatingsButton);

            }
        }

        else if (e.getSource() == updatePriceButton) {
            try {
                Globals.updateInTable("Movies","Price",updatePriceField.getText(),"TITLE",(String) updateNumberComboBox.getSelectedItem());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Globals.updateInTableMongo("Movies","PRICE",updatePriceField.getText(),"TITLE", String.valueOf(updateNumberComboBox.getSelectedItem()));
        }
        else if (e.getSource() == updateRatingsButton) {

            try {
                Globals.updateInTable("Movies","PG_RATING",updateRatingsField.getText(),"TITLE",(String) updateNumberComboBox.getSelectedItem());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Globals.updateInTableMongo("Movies","PG_RATING",updateRatingsField.getText(),"TITLE",(String) updateNumberComboBox.getSelectedItem());
        } else if (e.getSource() == updateShowMovieButton) {
            String primaryKey ;
            try {
                primaryKey = Globals.getPrimaryKeyFromName("Movies",updateShowMovieField.getText(), "title");
                System.out.println(primaryKey);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                Globals.updateInTable("Show","movie_id","'"+primaryKey+"'" , "show_id",(String) updateNumberComboBox.getSelectedItem());
                primaryKey = Globals.getMongoPrimaryKeyFromNameMongo("Movies","TITLE",updateShowMovieField.getText());
                Globals.updateInTableMongo("Show","MOVIE_ID",primaryKey , "SHOW_ID",Globals.formatMongoPrimaryKey("Show", Integer.parseInt(updateNumberComboBox.getSelectedItem().toString())));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
//            Globals.s
        } else if (e.getSource() == updateShowButton) {

            String showTime = "'" + date.getText() + "-" + month.getText() + "-" + year.getText() +
                    " " + hour.getText() + "." + minute.getText() + "." + second.getText() + ".000'";
            System.out.println(showTime);
            try {
                Globals.updateInTable("show","start_time",showTime,"show_id", (String) updateNumberComboBox.getSelectedItem());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(showTime);
            Globals.updateInTableMongo("Show","START_TIME",showTime,"SHOW_ID", Globals.formatMongoPrimaryKey("Show", Integer.parseInt(updateNumberComboBox.getSelectedItem().toString())));

        } else if (e.getSource() == insertButton) {
            clearComponents();
            if (insertPanel == null) {
                insertPanel = new JPanel();
            }
            insertPanel.setBackground(Color.white);
            insertPanel.setBounds(adminPanel.getWidth() , 0, jFrame.getWidth() - adminPanel.getWidth() - 20, jFrame.getHeight() - 20);
            insertPanel.setLayout(null);
            Label filterLabel = new Label("Choose Table : ");
            filterLabel.setBounds(30 , 20 , 200 ,30);
            filterLabel.setFont( Globals.headingFont);
            insertPanel.add(filterLabel);
            String[] filters = {"Movies", "Genre", "Movies&Genre"};
            insertComboBox = new JComboBox(filters);
            insertComboBox.addActionListener(this);
            insertComboBox.setForeground(Color.black);
            insertComboBox.setBackground(Color.white);


            insertComboBox.setBounds(30, 50, 200, 30);
            insertPanel.add(insertComboBox);
            insertPanel.setVisible(true);
            jFrame.add(insertPanel);
            addRepaintRevalidate(insertPanel);


        }else if (e.getSource() == insertComboBox) {
            clearComponentsExceptComboBox(insertPanel);
            if (insertComboBox.getSelectedItem().equals("Movies")) {

                clearComponentsExceptComboBox(insertPanel);
                movieNameLabel = new JLabel("Title:");
                movieNameLabel.setFont(buttonFont);
                movieNameLabel.setBounds(150, 100, 120, 30);
                insertPanel.add(movieNameLabel);

                movieName = new JTextField();
                movieName.setBounds(movieNameLabel.getX()+140, 100, 200, 30);
                movieName.addActionListener(this);
                insertPanel.add(movieName);

                pgRatingLabel = new JLabel("PG_RATINGS:");
                pgRatingLabel.setFont(buttonFont);
                pgRatingLabel.setBounds(movieNameLabel.getX(), movieName.getY() + 60, 120, 30);
                insertPanel.add(pgRatingLabel);

                pgRating = new JTextField();
                pgRating.setBounds(movieName.getX(), movieName.getY() + 60, 200, 30);
                pgRating.addActionListener(this);
                insertPanel.add(pgRating);

                industryLabel = new JLabel("Industry:");
                industryLabel.setFont(buttonFont);
                industryLabel.setBounds(movieNameLabel.getX(), pgRating.getY() + 60, 120, 30);
                insertPanel.add(industryLabel);

                industry = new JTextField();
                industry.setBounds(movieName.getX(), pgRating.getY() + 60, 200, 30);
                industry.addActionListener(this);
                insertPanel.add(industry);

                durationLabel = new JLabel("Duration:");
                durationLabel.setFont(buttonFont);
                durationLabel.setBounds(movieNameLabel.getX(), industry.getY() + 60, 120, 30);
                insertPanel.add(durationLabel);

                duration = new JTextField();
                duration.setBounds(movieName.getX(), industry.getY() + 60, 200, 30);
                duration.addActionListener(this);
                insertPanel.add(duration);

                storyLabel = new JLabel("Story:");
                storyLabel.setFont(buttonFont);
                storyLabel.setBounds(movieNameLabel.getX(), duration.getY() + 60, 120, 30);
                insertPanel.add(storyLabel);

                story = new JTextField();
                story.setBounds(movieName.getX(), duration.getY() + 60, 200, 30);
                story.addActionListener(this);
                insertPanel.add(story);

                priceLabel = new JLabel("Price:");
                priceLabel.setFont(buttonFont);
                priceLabel.setBounds(movieNameLabel.getX(), story.getY() + 60, 120, 30);
                insertPanel.add(priceLabel);

                price = new JTextField();
                price.setBounds(movieName.getX(), story.getY() + 60, 200, 30);
                price.addActionListener(this);
                insertPanel.add(price);

                logInButton = new JButton("Insert");
                logInButton.setBackground(Color.black);
                logInButton.setForeground(Color.WHITE);
                logInButton.setFont(buttonFont);
                logInButton.setBounds(movieName.getX()+50, price.getY() + 80, 100, 40);
                logInButton.addActionListener(this);
                insertPanel.add(logInButton);

                adminPanel.add(insertPanel);
                addRepaintRevalidate(insertPanel);


            }
            else if (insertComboBox.getSelectedItem().equals("Genre")) {
                clearComponentsExceptComboBox(insertPanel);
                genreNameMovieLabel = new JLabel("Genre:");
                genreNameMovieLabel.setFont(buttonFont);
                genreNameMovieLabel.setBounds(150, 120, 120, 30);
                insertPanel.add(genreNameMovieLabel);

                genreMovie = new JTextField();
                genreMovie.setBounds(genreNameMovieLabel.getX() + 100, 120, 200, 30);
                genreMovie.addActionListener(this);
                insertPanel.add(genreMovie);

                genreSubmit = new JButton("Insert");
                genreSubmit.setBackground(Color.black);
                genreSubmit.setForeground(Color.WHITE);
                genreSubmit.setFont(buttonFont);
                genreSubmit.setBounds(genreMovie.getX()+ 50, genreMovie.getY() + 70, 100, 40);
                genreSubmit.addActionListener(this);
                insertPanel.add(genreSubmit);

                adminPanel.add(insertPanel);
                addRepaintRevalidate(insertPanel);


            } else if (insertComboBox.getSelectedItem().equals("Movies&Genre")) {
                clearComponentsExceptComboBox(insertPanel);
                movieNameGenreLabel = new JLabel("Movie :");
                movieNameGenreLabel.setFont(buttonFont);
                movieNameGenreLabel.setBounds(150, 120, 120, 30);
                insertPanel.add(movieNameGenreLabel);

                movieGenre = new JTextField();
                movieGenre.setBounds( movieNameGenreLabel.getX()+ 100, 120, 200, 30);
                movieGenre.addActionListener(this);
                insertPanel.add(movieGenre);

                genreNameMovieLabel = new JLabel("Genre:");
                genreNameMovieLabel.setFont(buttonFont);
                genreNameMovieLabel.setBounds(150, movieGenre.getY() + 60, 120, 30);
                insertPanel.add(genreNameMovieLabel);

                genreMovie = new JTextField();
                genreMovie.setBounds(genreNameMovieLabel.getX()+ 100, movieGenre.getY() + 70, 200, 30);
                genreMovie.addActionListener(this);
                insertPanel.add(genreMovie);

                logInButtonGenre = new JButton("Insert");
                logInButtonGenre.setBackground(Color.black);
                logInButtonGenre.setForeground(Color.WHITE);
                logInButtonGenre.setFont(buttonFont);
                logInButtonGenre.setBounds(genreMovie.getX() + 50, genreMovie.getY() + 60, 100, 40);
                logInButtonGenre.addActionListener(this);
                insertPanel.add(logInButtonGenre);

                adminPanel.add(insertPanel);
                addRepaintRevalidate(insertPanel);

            }
        }else if (e.getSource() == logInButton) {
            System.out.println("clicked login button");
            try {

                String primaryKey = Globals.getPrimaryKey("movies");
                System.out.println(primaryKey);
                String[] values = {primaryKey, duration.getText(), movieName.getText()
                        , pgRating.getText(), story.getText()   , industry.getText(), price.getText()};
                System.out.println(values);
                Globals.insertData("movies", values);
                primaryKey = Globals.getMongoPrimaryKey("Movies");
                values[0] = primaryKey;
                Globals.insertMongo("Movies",values);
                JOptionPane.showMessageDialog(null,"Movie Inserted!");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == genreSubmit) {
            clearComponents();
            String genreKey ;
            try {
                genreKey = Globals.getPrimaryKey("genre");
                System.out.println(genreKey);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String[] values = {genreKey ,genreMovie.getText()};
            Globals.insertData("genre" , values);
            values[0] = Globals.getMongoPrimaryKey("Genre");
            Globals.insertMongo("Genre",values);
            JOptionPane.showMessageDialog(null,"Genre Inserted !");

        }
        else if (e.getSource() == logInButtonGenre) {
            String movieId , genreId ;
            try {
                movieId = Globals.getPrimaryKeyFromName("movies",movieGenre.getText(),"Title") ;
                genreId = Globals.getPrimaryKeyFromName("genre" , genreMovie.getText() , "Genre_Name");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String[] values = {movieId , genreId};
            Globals.insertData("moviegenre",values);
            values[0] = Globals.getMongoPrimaryKeyFromNameMongo("Movies","TITLE",movieGenre.getText());
            values[1] = Globals.getMongoPrimaryKeyFromNameMongo("Genre","GENRE_NAME",genreMovie.getText());
            Globals.insertMongo("MovieGenre",values);
            JOptionPane.showMessageDialog(null,"Movie & Genre Inserted !");


        } else if (e.getSource() == insertCustomer) {
            clearComponents();
            if (insertCustomerPanel == null) {
                insertCustomerPanel = new JPanel();
            }
            insertCustomerPanel.setBackground(Color.white);
            insertCustomerPanel.setBounds(adminPanel.getWidth() , 0, jFrame.getWidth() - adminPanel.getWidth() - 20, jFrame.getHeight() - 20);
            insertCustomerPanel.setLayout(null);

            movieNameLabel = new JLabel("Title:");
            movieNameLabel.setFont(buttonFont);
            movieNameLabel.setBounds(30, 100, 120, 30);
            insertCustomerPanel.add(movieNameLabel);

            movieName = new JTextField();
            movieName.setBounds(150, 100, 200, 30);
            movieName.addActionListener(this);
            insertCustomerPanel.add(movieName);

            pgRatingLabel = new JLabel("PG_RATINGS:");
            pgRatingLabel.setFont(buttonFont);
            pgRatingLabel.setBounds(30, movieName.getY()+60, 120, 30);
            insertCustomerPanel.add(pgRatingLabel);

            pgRating = new JTextField();
            pgRating.setBounds(150, movieName.getY()+60, 200, 30);
            pgRating.addActionListener(this);
            insertCustomerPanel.add(pgRating);

            industryLabel = new JLabel("Industry:");
            industryLabel.setFont(buttonFont);
            industryLabel.setBounds(30, pgRating.getY()+60, 120, 30);
            insertCustomerPanel.add(industryLabel);

            industry = new JTextField();
            industry.setBounds(150, pgRating.getY()+60, 200, 30);
            industry.addActionListener(this);
            insertCustomerPanel.add(industry);

            durationLabel = new JLabel("Duration:");
            durationLabel.setFont(buttonFont);
            durationLabel.setBounds(30, industry.getY()+60, 120, 30);
            insertCustomerPanel.add(durationLabel);

            duration = new JTextField();
            duration.setBounds(150, industry.getY()+60, 200, 30);
            duration.addActionListener(this);
            insertCustomerPanel.add(duration);

            storyLabel = new JLabel("Story:");
            storyLabel.setFont(buttonFont);
            storyLabel.setBounds(30, duration.getY()+60, 120, 30);
            insertCustomerPanel.add(storyLabel);

            story = new JTextField();
            story.setBounds(150, duration.getY()+60, 200, 30);
            story.addActionListener(this);
            insertCustomerPanel.add(story);

            priceLabel = new JLabel("Price:");
            priceLabel.setFont(buttonFont);
            priceLabel.setBounds(30, story.getY()+60, 120, 30);
            insertCustomerPanel.add(priceLabel);

            price = new JTextField();
            price.setBounds(150, story.getY()+60, 200, 30);
            price.addActionListener(this);
            insertCustomerPanel.add(price);

            logInButton = new JButton("Insert");
            logInButton.setBackground(SKY_BLUE_COLOR);
            logInButton.setForeground(Color.WHITE);
            logInButton.setFont(buttonFont);
            logInButton.setBounds(180, price.getY()+60, 100, 40);
            logInButton.addActionListener(this);
            insertCustomerPanel.add(logInButton);

            adminPanel.add(insertCustomerPanel);
            addRepaintRevalidate(insertCustomerPanel);

        }

    }

    private  void addRepaintRevalidate(JPanel jPanel){
        jFrame.add(jPanel);
        jFrame.repaint();
        jFrame.revalidate();
        jFrame.setVisible(true);
    }

    private void clearComponents() {
//        clearFilterComponents();
        jFrame.getContentPane().removeAll();
        jFrame.add(adminPanel);
        jFrame.repaint();
        jFrame.revalidate();

    }
    public void createTable(String query){
        clearComponents();
        try {
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            defaultTable = Globals.buildDefaultTable(resultSet);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        moviesTable = new JTable(defaultTable);
        moviesTable.setBounds(adminPanel.getWidth() , 0 , jFrame.getWidth() - adminPanel.getWidth() , jFrame.getHeight()) ;
        JScrollPane scrollPane = new JScrollPane(moviesTable);
        scrollPane.setBounds(adminPanel.getWidth(), 0, jFrame.getWidth()-adminPanel.getWidth() - 10 , jFrame.getHeight());
        jFrame.add(scrollPane);
    }
    public  void createTableForMongo(){
        clearComponents();
        try {
            defaultTable = Globals.loadDataFromMongoDb();
        }catch (MongoException ex){
            throw new RuntimeException(ex);
        }
        moviesTable = new JTable(defaultTable);
        moviesTable.setBounds(adminPanel.getWidth() , 0 , jFrame.getWidth() - adminPanel.getWidth() , jFrame.getHeight()) ;
        JScrollPane scrollPane = new JScrollPane(moviesTable);
        scrollPane.setBounds(adminPanel.getWidth(), 0, jFrame.getWidth()-adminPanel.getWidth() - 10 , jFrame.getHeight());
        jFrame.add(scrollPane);

    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Globals.createConnection();
        new AdminWindow();
    }
    private void clearComponentsExceptComboBox(JPanel panel) {
        // Remove all components except JComboBox from the panel
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (!(component instanceof JComboBox)) {
                panel.remove(component);
            }
        }
    }


}