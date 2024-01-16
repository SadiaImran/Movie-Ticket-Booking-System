import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class CustomerWindow implements ActionListener, ChangeListener {
    private Font font = new Font("Consolas", Font.BOLD, 22);
    Font titleFont = new Font("Cooper Black", Font.BOLD, 32);
    private JFrame receiptFrame;
    private JTextArea reviewTextArea;
    private JButton submitReviewButton;
    private JButton increasePriceButton, decreasePriceButton;
    private int currentPrice = 0;
    private String query ;
    private JButton submitPriceButton  ;
    private ButtonGroup group ;
    private JPanel IndustryPanel, pricePanel , feedbackPanel;
    private JRadioButton hollyWood , bollyWood , lollyWood ;
    private JFrame jFrame;
    private JButton filterButton, searchButton, displayButton, bookingButton, feedbackButton, displayPackageButton, addPackageButton, deletePackageButton, changePricePackageButton, displayWaitingCustomers;
    private JTextField textField , userNameField , userPasswordField , packageNameField , packageDescriptionField , packagePriceField , priceField , packageTimeField;
    private JTextArea dataTextArea;
    private JPanel customerPanel, displayCustomersPanel, searchCustomerPanel, filterMoviePanel,deleteCustomerPanel, displayWaitingCustomersPanel, displayPackagesPanel, addPackagesPanel, deletePackagesPanel, changePricePackagesPanel;
    private Font buttonFont = new Font("Consolas", Font.BOLD, 14);
    private static final Color SKY_BLUE_COLOR = new Color(140, 210, 240);
    private static final Color WHITE_COLOR = Color.WHITE;
    private DefaultTableModel defaultTable ;
    private JTable moviesTable ;
    private JComboBox jComboBox ;
    private JSlider jSlider ;
    private  Label RatingLabel ;
    private JPanel durationPanel;
    private JRadioButton hour1, hour2, hour3, hour4;
    private JTextField myPriceField;

    public CustomerWindow() {
        jFrame = new JFrame("Customer Window");
        jFrame.setSize(1050, 600);
        jFrame.setLayout(null);
        jFrame.setBackground(Color.white);
        customerPanel = new JPanel(null);
        customerPanel.setBounds(0, 0, jFrame.getWidth() / 3, jFrame.getHeight());
        customerPanel.setBackground(Color.BLACK);

        // Add admin icon and label
        ImageIcon adminIcon = new ImageIcon("Images/icon4.png");
        JLabel adminLabel = new JLabel("Customer", JLabel.CENTER);
        adminLabel.setIcon(adminIcon);
        adminLabel.setFont(Globals.headingFont);
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setBounds(10, 30, customerPanel.getWidth() - 40, 100);

        customerPanel.add(adminLabel);

        // Add display button
        displayButton = new JButton("Display Movies");
        displayButton.setBackground(Color.WHITE.brighter());
        displayButton.setForeground(Color.BLACK);
        displayButton.addActionListener(this);
        displayButton.setBounds(50, 160, customerPanel.getWidth() - 120, 30);
        customerPanel.add(displayButton);

        // Add search button
        searchButton = new JButton("Search Movie");
        searchButton.setBackground(Color.WHITE.brighter());
        searchButton.setForeground(Color.BLACK);
        searchButton.addActionListener(this);
        searchButton.setBounds(50, displayButton.getY()+60, customerPanel.getWidth() - 120, 30);
        customerPanel.add(searchButton);

        // Add delete button
        filterButton = new JButton("Filter Search");
        filterButton.setBackground(Color.WHITE.brighter());
        filterButton.setForeground(Color.BLACK);
        filterButton.addActionListener(this);
        filterButton.setBounds(50, searchButton.getY()+60, customerPanel.getWidth() - 120, 30);
        customerPanel.add(filterButton);

        // Add booking button
        bookingButton = new JButton("Booking");
        bookingButton.setBackground(Color.WHITE.brighter());
        bookingButton.setForeground(Color.BLACK);
        bookingButton.addActionListener(this);
        bookingButton.setBounds(50, filterButton.getY()+60, customerPanel.getWidth() - 120, 30);
        customerPanel.add(bookingButton);

        // Add feedback button


        jFrame.add(customerPanel);

        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
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
        moviesTable.setBounds(customerPanel.getWidth() , 0 , jFrame.getWidth() - customerPanel.getWidth() , jFrame.getHeight()) ;
        JScrollPane scrollPane = new JScrollPane(moviesTable);
        scrollPane.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth()-customerPanel.getWidth() - 10 , jFrame.getHeight());
        jFrame.add(scrollPane);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == displayButton) {
            clearComponents();
            query = "Select TITLE , START_TIME , END_TIME  from movies JOIN SHOW  ON  Movies.MOVIE_ID = SHOW.MOVIE_ID and title = 'Frozen'";
            createTable(query);}
        else if (e.getSource() == searchButton) {
            clearComponents();
            if (searchCustomerPanel == null) {
                searchCustomerPanel = new JPanel();

            }
            searchCustomerPanel.setBackground(WHITE_COLOR);
            searchCustomerPanel.setBounds(customerPanel.getWidth() , 0, jFrame.getWidth() - customerPanel.getWidth() - 20, jFrame.getHeight() - 20);
            searchCustomerPanel.setLayout(null);

            JLabel searchLabel = new JLabel("Enter Movie :");
            searchLabel.setBounds(10, 10, 200, 30);
            searchCustomerPanel.add(searchLabel);

            textField = new JTextField();
            textField.setBounds(10, 50, 200, 30);
            searchCustomerPanel.add(textField);

            JButton searchSubmitButton = new JButton("Search");
            searchSubmitButton.setBounds(10, 90, 200, 30);
            searchSubmitButton.addActionListener(this);
            searchCustomerPanel.add(searchSubmitButton);
            addRepaintRevalidate(searchCustomerPanel);

        }

        else if (e.getActionCommand().equals("Search")) {
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
                moviesTable.setBounds(customerPanel.getWidth() , 110 , jFrame.getWidth() - customerPanel.getWidth() , jFrame.getHeight()) ;
                JScrollPane scrollPane = new JScrollPane(moviesTable);
                scrollPane.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth()-customerPanel.getWidth() - 10 , jFrame.getHeight());
                jFrame.add(scrollPane);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }}
        else if (e.getSource() == filterButton) {
            clearComponents();
            if (filterMoviePanel == null) {
                filterMoviePanel = new JPanel();
            }

            filterMoviePanel.setBackground(WHITE_COLOR);
            filterMoviePanel.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth() - customerPanel.getWidth() - 20, jFrame.getHeight() - 20);
            filterMoviePanel.setLayout(null);

            Label filterLabel = new Label("Choose Filter Search : ");
            filterLabel.setBounds(230, 50, 250, 30);
            filterLabel.setFont( Globals.headingFont);
            filterMoviePanel.add(filterLabel);
            String[] filters = {"Duration", "Industry", "Ratings", "Price"};
            jComboBox = new JComboBox(filters);
            jComboBox.addActionListener(this);
            jComboBox.setForeground(Color.black);
            jComboBox.setBackground(Color.white);


            jComboBox.setBounds(filterLabel.getX() -  40, filterLabel.getY()+60, 300, 40);  // Adjust the bounds as needed
            filterMoviePanel.add(jComboBox);
            filterMoviePanel.setVisible(true);
            jFrame.add(filterMoviePanel);
            addRepaintRevalidate(filterMoviePanel);

        }else if (e.getSource() == bookingButton) {
            clearComponents();
            // Call a method to create and display the booking form
            createBookingForm();
        }
        else if (e.getSource() == feedbackButton) {

            if (feedbackPanel == null) {
                feedbackPanel = new JPanel();
            }
            feedbackPanel.setBackground(WHITE_COLOR);
            feedbackPanel.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth() - customerPanel.getWidth() - 20, jFrame.getHeight() - 20);
            feedbackPanel.setLayout(new BorderLayout());

            reviewTextArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(reviewTextArea);

            // Set bounds for the scroll pane
            scrollPane.setBounds(10, 10, feedbackPanel.getWidth() - 20, feedbackPanel.getHeight() - 60);

            feedbackPanel.add(scrollPane, BorderLayout.CENTER);

            submitReviewButton = new JButton("Submit Review");
            submitReviewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    submitReview();
                }
            });
            feedbackPanel.add(submitReviewButton, BorderLayout.SOUTH);

            feedbackPanel.setVisible(true);
            jFrame.add(feedbackPanel);

            addRepaintRevalidate(feedbackPanel);
        }

        else if (e.getSource() == jComboBox) {
            clearFilterComponents();

            if(jComboBox.getSelectedItem().equals( "Ratings")){

                jSlider = new JSlider(1, 5, 2);
                jSlider.setBounds(jComboBox.getWidth() + 50, 40, 200, 50);
                jSlider.setPaintTicks(true);
                jSlider.setMinorTickSpacing(1);
                jSlider.setPaintTrack(true);
                jSlider.setMajorTickSpacing(5);
                jSlider.setPaintLabels(true);
                jSlider.setBackground(Color.white);
                jSlider.addChangeListener(this);
                jSlider.setLabelTable(jSlider.createStandardLabels(1));
                RatingLabel = new Label();
                RatingLabel.setFont(new Font("Poppins" , Font.ITALIC , 18));
                RatingLabel.setBounds(jSlider.getX() , jSlider.getY()+50 , 200 ,50 );
                filterMoviePanel.add(RatingLabel);
                filterMoviePanel.add(jSlider);

            } else if (jComboBox.getSelectedItem().equals("Industry")) {

                IndustryPanel = new JPanel();
                IndustryPanel.setBounds(jComboBox.getX() -120, jComboBox.getY()+60 , jFrame.getWidth() - customerPanel.getWidth() -50 , 40);
                IndustryPanel.setBackground(Color.white);

                bollyWood = new JRadioButton("BollyWood");
                hollyWood = new JRadioButton("HollyWood");
                lollyWood = new JRadioButton("LollyWood");

                bollyWood.setBounds(jComboBox.getX(), jComboBox.getY()+ 50, 100, 30);
                hollyWood.setBounds(bollyWood.getWidth() , jComboBox.getY() + 50, 100, 30);
                lollyWood.setBounds(hollyWood.getWidth() , jComboBox.getY() + 50, 100, 30);
                bollyWood.setBackground(Color.white);
                bollyWood.addActionListener(this);
                hollyWood.setBackground(Color.white);
                hollyWood.addActionListener(this);
                lollyWood.setBackground(Color.white);
                lollyWood.addActionListener(this);

                group = new ButtonGroup() ;
                group.add(bollyWood);
                group.add(hollyWood);
                group.add(lollyWood);

                IndustryPanel.add(bollyWood);
                IndustryPanel.add(hollyWood);
                IndustryPanel.add(lollyWood);

                filterMoviePanel.add(IndustryPanel);
            }
            else if (jComboBox.getSelectedItem().equals("Duration")) {
                durationPanel = new JPanel();
                durationPanel.setBounds(jComboBox.getX() - 100, jComboBox.getY() + 40, jFrame.getWidth() - customerPanel.getWidth() - 50, 40);
                durationPanel.setBackground(Color.white);

                hour1 = new JRadioButton("1 Hour");
                hour2 = new JRadioButton("2 Hours");
                hour3 = new JRadioButton("3 Hours");
                hour4 = new JRadioButton("4 Hours");

                hour1.setBounds(jComboBox.getX()-60, jComboBox.getY() + 50, 100, 30);
                hour2.setBounds(hour1.getWidth(), jComboBox.getY() + 50, 100, 30);
                hour3.setBounds(hour2.getX() + hour2.getWidth(), jComboBox.getY() + 50, 100, 30);
                hour4.setBounds(hour3.getX() + hour3.getWidth(), jComboBox.getY() + 50, 100, 30);

                hour1.setBackground(Color.white);
                hour2.setBackground(Color.white);
                hour3.setBackground(Color.white);
                hour4.setBackground(Color.white);

                hour1.addActionListener(this);
                hour2.addActionListener(this);
                hour3.addActionListener(this);
                hour4.addActionListener(this);

                ButtonGroup durationGroup = new ButtonGroup();
                durationGroup.add(hour1);
                durationGroup.add(hour2);
                durationGroup.add(hour3);
                durationGroup.add(hour4);

                durationPanel.add(hour1);
                durationPanel.add(hour2);
                durationPanel.add(hour3);
                durationPanel.add(hour4);

                filterMoviePanel.add(durationPanel);
            }
            else if (jComboBox.getSelectedItem().equals("Price")) {

                pricePanel = new JPanel();
                pricePanel.setBounds(jComboBox.getX() -100, jComboBox.getY() + 40, jFrame.getWidth() - customerPanel.getWidth() - 50, 40);
                pricePanel.setBackground(Color.white);

                decreasePriceButton = new JButton("-");
                decreasePriceButton.setBackground(Color.black);
                decreasePriceButton.setForeground(Color.WHITE);
                decreasePriceButton.addActionListener(this);
                decreasePriceButton.setFont(new Font("Consolas", Font.BOLD, 18));
                decreasePriceButton.setBounds(jComboBox.getX()-20, 190, 50, 40);
                filterMoviePanel.add(decreasePriceButton);

                myPriceField = new JTextField();
                myPriceField.setBackground(Color.WHITE.brighter());
                myPriceField.setForeground(Color.BLACK);
                myPriceField.addActionListener(this);
                myPriceField.setFont(new Font("Consolas", Font.BOLD, 18));
                myPriceField.setBounds(decreasePriceButton.getX()+53, 190, 100, 40);
                filterMoviePanel.add(myPriceField);

                increasePriceButton = new JButton("+");
                increasePriceButton.setBackground(Color.WHITE.brighter());
                increasePriceButton.setForeground(Color.white);
                increasePriceButton.addActionListener(this);
                increasePriceButton.setFocusable(false);
                increasePriceButton.setBackground(Color.BLACK);
                increasePriceButton.setFont(buttonFont);
                increasePriceButton.setFont(new Font("Consolas", Font.BOLD, 18));
                increasePriceButton.setBounds(myPriceField.getX()+100, 190, 50, 40);
                filterMoviePanel.add(increasePriceButton);


                submitPriceButton = new JButton("Find Movie");
                submitPriceButton.setFont(new Font("Consolas", Font.BOLD, 14));
                submitPriceButton.setBounds(200, 300, 170, 40);
                submitPriceButton.addActionListener(this);
                submitPriceButton.setBackground(Color.black);
                submitPriceButton.setForeground(Color.white);
                submitPriceButton.setFont(buttonFont);
                filterMoviePanel.add(submitPriceButton);
                filterMoviePanel.add(pricePanel);

            }
            addRepaintRevalidate(filterMoviePanel);

        } else if (e.getSource() == hollyWood) {
            clearComponents();
            System.out.println("hello im in hollywood");
            String value = "Hollywood" ;
            PreparedStatement preparedStatement = null;
            try {

                preparedStatement = Globals.connection.prepareStatement("SELECT * FROM movies WHERE UPPER(industry) = UPPER(?)");
                preparedStatement.setString(1, value.toUpperCase());
                ResultSet resultSet = preparedStatement.executeQuery();
                defaultTable = Globals.buildDefaultTable(resultSet);

                moviesTable = new JTable(defaultTable);
                moviesTable.setBounds(customerPanel.getWidth() , 110 , jFrame.getWidth() - customerPanel.getWidth() , jFrame.getHeight()) ;
                JScrollPane scrollPane = new JScrollPane(moviesTable);
                scrollPane.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth()-customerPanel.getWidth() - 10 , jFrame.getHeight());
                jFrame.add(scrollPane);
                System.out.println("hi im about to exit");

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }
        else if (e.getSource() == lollyWood) {
            clearComponents();
            String value = "LollyWood" ;
            PreparedStatement preparedStatement = null;
            try {

                preparedStatement = Globals.connection.prepareStatement("SELECT * FROM movies WHERE UPPER(industry) = UPPER(?)");
                preparedStatement.setString(1, value.toUpperCase());
                ResultSet resultSet = preparedStatement.executeQuery();
                defaultTable = Globals.buildDefaultTable(resultSet);
                moviesTable = new JTable(defaultTable);
                moviesTable.setBounds(customerPanel.getWidth() , 110 , jFrame.getWidth() - customerPanel.getWidth() , jFrame.getHeight()) ;
                JScrollPane scrollPane = new JScrollPane(moviesTable);
                scrollPane.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth()-customerPanel.getWidth() - 10 , jFrame.getHeight());
                jFrame.add(scrollPane);


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == bollyWood) {
            clearComponents();
            String value = "BollyWood" ;
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = Globals.connection.prepareStatement("SELECT * FROM movies WHERE UPPER(industry) = UPPER(?)");
                preparedStatement.setString(1, value.toUpperCase());
                ResultSet resultSet = preparedStatement.executeQuery();
                defaultTable = Globals.buildDefaultTable(resultSet);
                moviesTable = new JTable(defaultTable);
                moviesTable.setBounds(customerPanel.getWidth() , 110 , jFrame.getWidth() - customerPanel.getWidth() , jFrame.getHeight()) ;
                JScrollPane scrollPane = new JScrollPane(moviesTable);
                scrollPane.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth()-customerPanel.getWidth() - 10 , jFrame.getHeight());
                jFrame.add(scrollPane);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() ==  submitPriceButton) {
            updatePriceFilter();

        } else if (e.getSource() == hour1) {
            filterByDuration(1);
        } else if (e.getSource() == hour2) {
            filterByDuration(2);
        } else if (e.getSource() == hour3) {
            filterByDuration(3);
        } else if (e.getSource() == hour4) {
            filterByDuration(4);
        }
        if (e.getSource() == increasePriceButton) {
            currentPrice += 10;
            updatePriceTextField();
        } else if (e.getSource() == decreasePriceButton) {
            currentPrice = Math.max(0, currentPrice - 10);
            updatePriceTextField();
        }
//        Label currentPriceLabel = (Label) filterMoviePanel.getComponent(6);
//        Label currentPriceLabel = new Label();
//        currentPriceLabel.setText("$" + currentPrice);

        jFrame.revalidate();
        jFrame.repaint();
    }

    public void updatePriceTextField(){
        myPriceField.setText(String.valueOf(currentPrice));
    }
    private void filterByDuration(int duration) {
        clearComponents();
        int value = duration;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Globals.connection.prepareStatement("SELECT * FROM movies WHERE DURATION = ?");
            preparedStatement.setInt(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            defaultTable = Globals.buildDefaultTable(resultSet);
            moviesTable = new JTable(defaultTable);
            moviesTable.setBounds(customerPanel.getWidth(), 110, jFrame.getWidth() - customerPanel.getWidth(), jFrame.getHeight());
            JScrollPane scrollPane = new JScrollPane(moviesTable);
            scrollPane.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth() - customerPanel.getWidth() - 10, jFrame.getHeight());
            jFrame.add(scrollPane);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        jFrame.revalidate();
        jFrame.repaint();
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        RatingLabel.setText("More than "+jSlider.getValue()+" Ratings");
        query = "Select * from movies where PG_RATING >= "+jSlider.getValue() ;
        createTable(query);

        jFrame.add(filterMoviePanel);
        addRepaintRevalidate(filterMoviePanel);

    }

    private  void addRepaintRevalidate(JPanel jPanel){
        jFrame.add(jPanel);
        jFrame.repaint();
        jFrame.revalidate();
        jFrame.setVisible(true);
    }

    private void clearComponents() {
        clearFilterComponents();
        jFrame.getContentPane().removeAll();
        jFrame.add(customerPanel);
        jFrame.repaint();
        jFrame.revalidate();

    }
    private void clearFilterComponents() {
        // Remove components specific to the filter panel
        if (filterMoviePanel != null) {
            filterMoviePanel.removeAll();
            filterMoviePanel.revalidate();
            filterMoviePanel.repaint();

            // Remove components related to the ratings filter
            if (jSlider != null) {
                filterMoviePanel.remove(jSlider);
                filterMoviePanel.remove(RatingLabel);
            }

            // Remove components related to the industry filter
            if (IndustryPanel != null) {
                filterMoviePanel.remove(IndustryPanel);
            }

            jFrame.repaint();
            jFrame.revalidate();
        }

        jSlider = null;
        RatingLabel = null;
        bollyWood = null;
        hollyWood = null;
        lollyWood = null;
        IndustryPanel = null;
        group = null;
    }

    private void updatePriceFilter() {
        clearComponents();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Globals.connection.prepareStatement("SELECT * FROM movies WHERE price >="+currentPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            defaultTable = Globals.buildDefaultTable(resultSet);
            moviesTable = new JTable(defaultTable);
            moviesTable.setBounds(customerPanel.getWidth(), 110, jFrame.getWidth() - customerPanel.getWidth(), jFrame.getHeight());
            JScrollPane scrollPane = new JScrollPane(moviesTable);
            scrollPane.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth() - customerPanel.getWidth() - 10, jFrame.getHeight());
            jFrame.add(scrollPane);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        jFrame.revalidate();
        jFrame.repaint();
    }

    private void createBookingForm() {
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(null);
        bookingPanel.setBackground(WHITE_COLOR);
        bookingPanel.setBounds(customerPanel.getWidth(), 0, jFrame.getWidth() - customerPanel.getWidth() - 20, jFrame.getHeight() - 20);

        JLabel movieLabel = new JLabel("Select Movie:");
        movieLabel.setBounds(200, 50, 300, 30);
        movieLabel.setFont(Globals.headingFont
        );
        bookingPanel.add(movieLabel);

        // Fetch movie names from the database and add them to a JComboBox
        ArrayList<String> movieNames = Globals.fetchMovieNames("TITLE","MOVIES");
        JComboBox<String> movieComboBox = new JComboBox<>(movieNames.toArray(new String[0]));
        movieComboBox.setBackground(Color.white);
        movieComboBox.setForeground(Color.black);
        movieComboBox.setBounds(movieLabel.getX() -50, movieLabel.getY()+40, 300, 40);
        bookingPanel.add(movieComboBox);

        // JLabel to display show timings
        JLabel showTimingLabel = new JLabel("Select Show Timing:");
        showTimingLabel.setBounds(movieComboBox.getX() +30 , movieComboBox.getY()+60, 300, 40);
        showTimingLabel.setFont(font);
        bookingPanel.add(showTimingLabel);

        // JComboBox for show timings
        JComboBox<String> showTimingComboBox = new JComboBox<>();
        showTimingComboBox.setBackground(Color.white);
        showTimingComboBox.setForeground(Color.black);
        showTimingComboBox.setBounds(showTimingLabel.getX() - 30, showTimingLabel.getY()+40, 300, 30);
        bookingPanel.add(showTimingComboBox);

        // JLabel to display available seats
        JLabel seatLabel = new JLabel("Select Seat:");
        seatLabel.setFont(font);
        seatLabel.setBounds(showTimingComboBox.getX() +50, showTimingComboBox.getY()+50, 150, 30);
        bookingPanel.add(seatLabel);

        // Replace the seatComboBox declaration with an ArrayList to store JCheckBox objects
        ArrayList<JCheckBox> seatCheckboxes = new ArrayList<>();

        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(3, 5)); // Adjust the grid layout as needed
        seatPanel.setBounds(seatLabel.getX() -30, seatLabel.getY()+30, 400, 150);
        seatPanel.setBackground(Color.white);
        bookingPanel.add(seatPanel);

        // Add a listener to fetch and display show timings based on the selected movie
        movieComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedMovie = (String) movieComboBox.getSelectedItem();

                // Fetch and display show timings based on the selected movie
                String selectedMovieId = fetchMovieId(selectedMovie);
                System.out.println(selectedMovieId);
                ArrayList<String> showTimings = fetchShowTimings(selectedMovieId);

                // Update the show timings combo box
                showTimingComboBox.removeAllItems();
                for (String showTiming : showTimings) {
                    showTimingComboBox.addItem(showTiming);
                    System.out.println(showTiming);
                }
                showTimingComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // This code will be executed when a show timing is selected

//                        String selectedMovie = (String) movieComboBox.getSelectedItem();
                        String selectedMovieId = fetchMovieId(selectedMovie);
                        String selectedShowTiming = (String) showTimingComboBox.getSelectedItem();

                        // Fetch and display available seats based on the selected show timing
                        String selectedShowId = fetchShowId(selectedShowTiming, selectedMovieId);

                        ArrayList<String> availableSeats = fetchAvailableSeats(selectedShowId);
                        seatPanel.removeAll(); // Clear previous checkboxes

                        for (String seat : availableSeats) {
                            JCheckBox seatCheckbox = new JCheckBox(seat);
                            seatCheckboxes.add(seatCheckbox);
                            seatPanel.add(seatCheckbox);
                        }

                        seatPanel.revalidate();
                        seatPanel.repaint();
                    }
                });
            }
        });

        // Add a button to confirm the booking
        JButton bookNowButton = new JButton("Book Now");
        bookNowButton.setBounds(seatLabel.getX() + 40, 450, 100, 40);
        bookNowButton.setBackground(Color.decode("#1c1515"));
        bookNowButton.setForeground(Color.WHITE);
        bookNowButton.setFont(buttonFont);
        bookNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfSeats = 0;
                String selectedMovie = (String) movieComboBox.getSelectedItem();
                String selectedMovieId = fetchMovieId(selectedMovie);
                String selectedShowTiming = (String) showTimingComboBox.getSelectedItem();
                String selectedShowId = fetchShowId(selectedShowTiming,selectedMovieId);

                for (JCheckBox seatCheckbox : seatCheckboxes) {
                    if (seatCheckbox.isSelected()) {
                        numberOfSeats++;
                    }
                }

                int moviePrice = fetchMoviePrice(selectedMovieId);


                int totalPrice = numberOfSeats * moviePrice;


                String bookingId = insertBookingInfo(numberOfSeats, selectedShowId, "C010",totalPrice);
                updateSeatTable(selectedShowId, seatCheckboxes, bookingId);

                // Display a confirmation message
//                JOptionPane.showMessageDialog(jFrame, "Seats booked successfully!");
                generateReceipt(selectedMovie, selectedShowId, selectedShowTiming, numberOfSeats, totalPrice);

            }
        });
        bookingPanel.add(bookNowButton);

        // Add the booking panel to the frame
        addRepaintRevalidate(bookingPanel);
    }

    private ArrayList<String> fetchAvailableSeats(String showId) {
        System.out.println(showId);
        ArrayList<String> availableSeats = new ArrayList<>();

        try {
            String query = "SELECT SEAT_NUMBER FROM seat WHERE BOOKING_ID IS NULL AND SHOW_ID = ?";
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            preparedStatement.setString(1, showId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String seatNumber = resultSet.getString("SEAT_NUMBER");
                System.out.println(seatNumber);
                availableSeats.add(seatNumber);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return availableSeats;
    }




    private ArrayList<String> fetchShowTimings(String selectedMovieId) {
        ArrayList<String> showTimings = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT START_TIME FROM show WHERE MOVIE_ID = ?";
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            preparedStatement.setString(1, selectedMovieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Timestamp timestamp = resultSet.getTimestamp("START_TIME");
                String formattedShowTiming = timestamp.toString();
                showTimings.add(formattedShowTiming);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return showTimings;
    }

    private String fetchMovieId(String movieTitle) {
        String movieId = "";

        try {
            String query = "SELECT MOVIE_ID FROM movies WHERE TITLE = ?";
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            preparedStatement.setString(1, movieTitle);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                movieId = resultSet.getString("MOVIE_ID");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return movieId;
    }
    private String fetchShowId(String ShowTiming, String movieId) {
        String showId = "";

        try {
            Timestamp timestamp = convertStringToTimestamp(ShowTiming);
            String query = "SELECT SHOW_ID FROM show WHERE MOVIE_ID = ? AND START_TIME = ? ";
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            preparedStatement.setString(1, movieId);
            preparedStatement.setTimestamp(2, timestamp);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                showId = resultSet.getString("SHOW_ID");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return showId;
    }

    private int fetchMoviePrice(String movieId) {
        int moviePrice = 0;

        try {
            String query = "SELECT PRICE FROM movies WHERE MOVIE_ID = ?";
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            preparedStatement.setString(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                moviePrice = resultSet.getInt("PRICE");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return moviePrice;
    }

    private String insertBookingInfo(int numberOfSeats, String showId, String customerId, int totalPrice) {
        try {

            System.out.println(numberOfSeats);

            Statement countStatement = Globals.connection.createStatement();
            ResultSet countResult = countStatement.executeQuery("SELECT COUNT(*) FROM booking");
            countResult.next();
            int bookingCount = countResult.getInt(1) + 1; // Increment by 1 for the new booking
            countStatement.close();

            // Format the BOOKING ID as "B001", "B002", etc.
            String formattedBookingId = String.format("B%03d", bookingCount);
            System.out.println(formattedBookingId);

            String query = "INSERT INTO booking (BOOKING_ID, NOOFSEATS, SHOW_ID, CUSTOMER_ID, PRICE) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            preparedStatement.setString(1, formattedBookingId);
            preparedStatement.setInt(2, numberOfSeats);
            preparedStatement.setString(3, showId);
            preparedStatement.setString(4, customerId);
            preparedStatement.setInt(5, totalPrice);
            preparedStatement.executeUpdate();

            return formattedBookingId;
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    private Timestamp convertStringToTimestamp(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        return new Timestamp(parsedDate.getTime());
    }

    private void submitReview() {
        String reviewText = reviewTextArea.getText();
        JOptionPane.showMessageDialog(jFrame, "Review submitted successfully!");
        reviewTextArea.setText("");
    }

    private void generateReceipt(String movie, String showId, String showTiming, int numberOfSeats, int totalPrice) {
        receiptFrame = new JFrame("Booking Receipt");
        receiptFrame.setSize(400, 350);
        receiptFrame.setLayout(null);
        receiptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(null);
        receiptPanel.setBounds(10, 10, 360, 300);
        receiptPanel.setBackground(new Color(240, 240, 240));

        JLabel receiptLabel = new JLabel("Booking Receipt");
        receiptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        receiptLabel.setBounds(80, 10, 200, 30);
        receiptPanel.add(receiptLabel);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        JLabel dateLabel = new JLabel("Date: " + dateFormat.format(new Date()));
        dateLabel.setBounds(10, 40, 200, 20);
        receiptPanel.add(dateLabel);

        JLabel movieLabel = new JLabel("Movie: " + movie);
        movieLabel.setBounds(10, 70, 300, 20);
        receiptPanel.add(movieLabel);

        JLabel showIdLabel = new JLabel("Show ID: " + showId);
        showIdLabel.setBounds(10, 100, 300, 20);
        receiptPanel.add(showIdLabel);

        JLabel showTimingLabel = new JLabel("Show Timing: " + showTiming);
        showTimingLabel.setBounds(10, 130, 300, 20);
        receiptPanel.add(showTimingLabel);

        JLabel seatsLabel = new JLabel("Number of Seats: " + numberOfSeats);
        seatsLabel.setBounds(10, 160, 300, 20);
        receiptPanel.add(seatsLabel);

        JLabel totalLabel = new JLabel("Total Price: $" + totalPrice);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setBounds(10, 190, 300, 20);
        receiptPanel.add(totalLabel);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(130, 240, 100, 30);
        closeButton.addActionListener(e -> receiptFrame.dispose());
        receiptPanel.add(closeButton);

        receiptFrame.add(receiptPanel);
        receiptFrame.setVisible(true);
    }
    private void updateSeatTable(String showId, ArrayList<JCheckBox> seatCheckboxes, String bookingId) {
        try {
            // Start a transaction
            Globals.connection.setAutoCommit(false);

            for (JCheckBox seatCheckbox : seatCheckboxes) {
                if (seatCheckbox.isSelected()) {
                    String seatName = seatCheckbox.getText();
                    // Update the seat table to mark the seat as booked
                    String updateQuery = "UPDATE seat SET STATUS = 'Booked' , BOOKING_ID = ?  WHERE SHOW_ID = ? AND SEAT_NUMBER = ?";
                    try (PreparedStatement updateStatement = Globals.connection.prepareStatement(updateQuery)) {
                        updateStatement.setString(1, bookingId);
                        updateStatement.setString(2, showId);
                        updateStatement.setString(3, seatName);
                        updateStatement.executeUpdate();
                    }
                }
            }

            // Commit the transaction
            Globals.connection.commit();
            Globals.connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                // Rollback the transaction in case of an exception
                Globals.connection.rollback();
                Globals.connection.setAutoCommit(true);
                throw new RuntimeException("Error updating seat table: " + ex.getMessage(), ex);
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error rolling back transaction: " + rollbackEx.getMessage(), rollbackEx);
            }
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Globals.createConnection();
        new CustomerWindow();
    }

}