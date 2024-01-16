import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Globals {
    static  Color blackColor = Color.decode("#1c1515") ;
    static  Font headingFont = new Font("Consolas", Font.BOLD, 24);
    static Font buttonFont = new Font("Consolas", Font.BOLD, 18);
    static  Font titleFont = new Font("Copperplate Light", Font.BOLD, 20);

    static Statement statement;
    static MongoCollection<Document> collection ;
    static DBCollection dbCollection ;
    static MongoDatabase database ;
    static  MongoClient mongoClient ;
    static Connection connection;
    public static void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "sadia", "admin");
        statement = connection.createStatement();
    }
    public  static  void closeConnection () throws SQLException {
        statement.close();
        connection.close();
    }
    public static ArrayList<String> getColumnNames(String tableName) throws SQLException {
        ArrayList<String> columnNames = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(resultSetMetaData.getColumnName(i));
        }
        resultSet.close();
        return columnNames;
    }
    public static ArrayList<ArrayList<String>> getColumnValues(String tableName) throws SQLException {
        ArrayList<ArrayList<String>> columnValues = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("Select * from " + tableName);

        ArrayList<String> columnNames = getColumnNames(tableName);

        while (resultSet.next()) {
            ArrayList<String> row = new ArrayList<>();
            for (String column : columnNames) {
                row.add(resultSet.getString(column));
            }
            columnValues.add(row);
        }
        resultSet.close();
        return columnValues;
    }

    public static DefaultTableModel buildDefaultTable(ResultSet resultSet) throws SQLException {
        DefaultTableModel defaultTableModel = new DefaultTableModel() ;
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        //Column Names
        int columnCount = resultSetMetaData.getColumnCount();
        ArrayList<String> columnNames = new ArrayList<>();
        for (int i = 0 ;  i < columnCount ; i++){
            columnNames.add(i,resultSetMetaData.getColumnName(i+1));
            defaultTableModel.addColumn(columnNames.get(i));
        }
        //Rows
        while (resultSet.next()){
            Object[] data = new Object[columnCount];
            for (int i =  0 ; i< columnCount ; i++){
                data[i] = resultSet.getObject(i+1) ;
            }
            defaultTableModel.addRow(data);
        }

        return defaultTableModel ;
    }
    public static ArrayList<String> fetchKeysMongo(String tableName){
        ArrayList<String> keys = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection(tableName);
        MongoCursor<Document> cursor = collection.find().iterator();
        if(cursor.hasNext()){
            Document document = cursor.next();
            for (String key  : document.keySet()){
                if(!key.equals("_id")){
                    keys.add(key);
                }
            }
        }
        return keys ;
    }
    public static DefaultTableModel loadDataFromMongoDb() {
        DefaultTableModel model = new DefaultTableModel();
        database = mongoClient.getDatabase("CINEMA");
        MongoCollection<Document> collection = database.getCollection("Show");
        MongoCursor<Document> cursor = collection.find().iterator();
        String value;
        ArrayList<String> columns = new ArrayList<>() ;

        if (cursor.hasNext()) {
            Document document = cursor.next();

            // Add columns to the model
            for (String key : document.keySet()) {
                if (!key.equals("_id")) {
                    model.addColumn(key);
                    columns.add(key);
                }
            }

            do {
                Object[] data = new Object[model.getColumnCount()];


                int dataIndex = 0;
                for (String key : columns) {
                    if (!key.equals("_id")) {
                        data[dataIndex++] = document.get(key);
                    }
                }

                model.addRow(data);

                if (cursor.hasNext()) {
                    document = cursor.next();
                }
            } while (cursor.hasNext());
        }

        return model;
    }

    public static void mongoSearch(){
        String userInput = "Frozen";
        collection = database.getCollection("Movies");
        // Create a regular expression for the search (case-insensitive)
        Pattern regexPattern = Pattern.compile(userInput, Pattern.CASE_INSENSITIVE);
        Document regexFilter = new Document("title", new Document("$regex", regexPattern));

        // Perform the search
        FindIterable<Document> result = collection.find(regexFilter);

        // Display the search results
        MongoCursor<Document> cursor = result.iterator();
        while (cursor.hasNext()) {

            System.out.println(cursor.next());
        }
        System.out.println("Nothing");

    }
    public static void insertData(String tableName, String[] values) {
//        SQL INSERTION
        try {

            ArrayList<String> columnNames = getColumnNames(tableName);
            StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
            StringBuilder valuesBuilder = new StringBuilder(" VALUES (");
            for (String columnName : columnNames) {
                queryBuilder.append(columnName).append(",");
                valuesBuilder.append("?,");
            }
            queryBuilder.setLength(queryBuilder.length() - 1);
            valuesBuilder.setLength(valuesBuilder.length() - 1);
            queryBuilder.append(")").append(valuesBuilder).append(")");
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("Added successfully.");
            } else {
                System.out.println("Failed to add the record.");
            }
            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

//        MONGO INSERTION


    }

    public static String getMongoPrimaryKey(String tableName){
        String attribute = "";
        if(tableName.toUpperCase().equals("MOVIES")) {
            attribute+=tableName.substring(0,tableName.length()-1) +"_id";
        }
        else{
            attribute +=tableName+"_id";
        }
        MongoCollection<Document> collection = database.getCollection(tableName);
        Document lastMovieId = collection.find().sort(Sorts.descending(attribute.toUpperCase())).limit(1).first();
        System.out.println(lastMovieId);
        String primaryKey = "";
        if(lastMovieId == null){
            primaryKey =tableName.substring(0,1)+"001";
        }
        else{
            primaryKey = lastMovieId.getString(attribute.toUpperCase());
            System.out.println("Last id : "+primaryKey);
            primaryKey = tableName.charAt(0) +  formatTo3Digits(Integer.parseInt(primaryKey.substring(1))+1);
            System.out.println("New id : "+primaryKey);
        }
        return primaryKey ;
    }


    public  static  void insertMongo(String tableName ,String[] values){

        MongoCollection<Document> collection = database.getCollection(tableName);
        Document document = new Document();
        for(int i = 0 ; i<fetchKeysMongo(tableName).size() ; i++){
            document.append(fetchKeysMongo(tableName).get(i),values[i]);
            System.out.println(values[i]);
        }
        try {
            collection.insertOne(document);
            System.out.println("Inserted");
        } catch (Exception e) {
            System.err.println("Error inserting document: " + e.getMessage());
            e.printStackTrace();
        }


    }

    public static String getPrimaryKeyFromName ( String tableName, String name , String conditionalAttribute) throws SQLException{
        String attribute = "";
        if(tableName.toUpperCase().equals("MOVIES")) {
            attribute+=tableName.substring(0,tableName.length()-1) +"_id";
        }
        else{
            attribute +=tableName+"_id";
        }
        String query = "SELECT "+attribute+" from "+tableName+" where "+conditionalAttribute +" = " +" '"+name +"'";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        String id = null;

        if (resultSet.next()) {
            id = resultSet.getString(1);
        } else {
            System.out.println("No records found in the table");
        }

        return id ;
    }
    public static String getMongoPrimaryKeyFromNameMongo(String tableName , String attributeName , String attributeValue){
        String attribute = "";
        if(tableName.toUpperCase().equals("MOVIES")) {
            attribute+=tableName.substring(0,tableName.length()-1) +"_id";
        }
        else{
            attribute +=tableName+"_id";
        }
        MongoCollection<Document> collection = database.getCollection(tableName);
        Document lastMovieId = collection.find(Filters.eq(attributeName, attributeValue))
                .sort(Sorts.descending(attribute))
                .limit(1)
                .first();
        System.out.println(lastMovieId);
        String primaryKey = lastMovieId.getString(attribute.toUpperCase());

        return primaryKey ;
    }
    public static String getPrimaryKey(String tableName) throws SQLException {
        String attribute = "";
        if(tableName.toUpperCase().equals("MOVIES")) {
            attribute+=tableName.substring(0,tableName.length()-1) +"_id";
        }
        else{
            attribute +=tableName+"_id";
        }
        String query = "SELECT "+attribute+" from "+tableName+" order by "+attribute+" desc";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        String highestId = null;

        if (resultSet.next()) {
            highestId = resultSet.getString(1);
        } else {
            System.out.println("No records found in the table");
        }

        resultSet.close();
        preparedStatement.close();
        String PrimaryKey = (formatTo3Digits(Integer.parseInt(
                highestId.substring(1))+1));
        String primaryKey = highestId.substring(0,1)+PrimaryKey;

        return  primaryKey ;

    }


    private static String formatTo3Digits(int number) {
        return String.format("%03d", number);
    }
    public static int countPrimaryKey(String tableName) throws SQLException {
        String attribute = "";
        if(tableName.toUpperCase().equals("MOVIES")) {
            attribute+=tableName.substring(0,tableName.length()-1) +"_id";
        }
        else{
            attribute +=tableName+"_id";
        }
        String query = "SELECT "+attribute+" from "+tableName ;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        int i = 0 ;
        while (resultSet.next()){
            i++ ;
        }
        return i ;
    }

    public static void updateInTable(String tableName, String updateAttribute,
                                     String value, String conditionalAttribute, String conditionalValue) throws SQLException {

            System.out.println("Entered");
            String query = "UPDATE " + tableName + " SET " + updateAttribute + " = " + value +
                    " WHERE " + conditionalAttribute + " LIKE '%" + conditionalValue + "'";
            System.out.println("Generated Query: " + query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Executed");
            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            } else {
                System.out.println("Failed to add the record.");
                JOptionPane.showMessageDialog(null, "NOT Successful");
            }

    }
    public static void updateInTableMongo(String tableName , String updateAttribute , String value , String conditionalAttribute , String conditinalValue){
        MongoCollection<Document> collection = database.getCollection(tableName);

        Document filter = collection.find(Filters.eq(conditionalAttribute , conditinalValue)).first();
        Document update = new Document("$set", new Document(updateAttribute, value));
        // Perform the update
        collection.updateOne(filter, update);
        JOptionPane.showMessageDialog(null, "Document updated successfully");
    }

    public  static  String formatMongoPrimaryKey(String table , int key){
        return table.charAt(0)+formatTo3Digits(key);
    }
    public static ArrayList<String> fetchMovieNames(String attribute , String tableName) {
        ArrayList<String> movieNames = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT "+attribute+" FROM "+tableName;
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String movieName = resultSet.getString(attribute);
                movieNames.add(movieName);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return movieNames;
    }

    public static String fetchMovieId(String movieTitle) {
        String movieId = "";

        try {
            String query = "SELECT MOVIE_ID FROM Movies WHERE TITLE = ?";
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
    public static String fetchGenreId(String genreName) {
        String genreId = "";

        try {
            String query = "SELECT GENRE_ID FROM genre WHERE GENRE_NAME = ?";
            PreparedStatement preparedStatement = Globals.connection.prepareStatement(query);
            preparedStatement.setString(1, genreName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                genreId = resultSet.getString("GENRE_ID");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return genreId;
    }
 public static boolean updateDeleteId(String tableName, String ID, String nameID) {
        boolean updated = true;

        try {
            if ("moviegenre".equalsIgnoreCase(tableName) || "movies".equalsIgnoreCase(tableName) || "genre".equalsIgnoreCase(tableName) || "show".equalsIgnoreCase(tableName) || "booking".equalsIgnoreCase(tableName)) {
                // If the table is moviegenre, genre or movies, delete the whole row
                String deleteSql = "DELETE FROM " + tableName + " WHERE " + nameID + " = ?";
                try (PreparedStatement deleteStatement = Globals.connection.prepareStatement(deleteSql)) {
                    deleteStatement.setString(1, ID);
                    int rowsDeleted = deleteStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        System.out.println("Deleted " + rowsDeleted + " rows from " + tableName + " table.");
                        updated = true;
                    } else {
                        System.out.println("No rows were deleted. " +nameID+ " not found or already null.");
                        updated = false;
                    }
                }
            } else {

                String updateSql = "UPDATE " + tableName + " SET " + nameID + " = NULL WHERE " + nameID+ " = ?";
                try (PreparedStatement preparedStatement = Globals.connection.prepareStatement(updateSql)) {
                    preparedStatement.setString(1, ID);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Updated " + rowsAffected + " rows in " + tableName + " table.");
                        updated = true;
                    } else {
                        System.out.println("No rows were updated. "+ nameID+" not found or already null.");
                        updated = false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }

        return updated;
    }

    public static  boolean updatedMongoDeleteId(String tableName, String ID, String nameID){
        boolean updated = true;

        MongoCollection<Document> collection = database.getCollection(tableName);

        if ("Movies".equalsIgnoreCase(tableName) || "Genre".equalsIgnoreCase(tableName) || "MovieGenre".equalsIgnoreCase(tableName)) {
            // Delete the document based on the provided ID and nameID
            Document filter = new Document(nameID, ID);
            DeleteResult deleteResult = collection.deleteOne(filter);


        } else {
            // Handle other cases if needed
            // For example, you might want to update a field instead of deleting the entire document
            // You can use collection.updateOne(...) with appropriate update operators
        }

        return updated;
    }

    public static void mongoConnection(){
         mongoClient = new MongoClient("localhost",27017);
        System.out.println("Connected");
         database = mongoClient.getDatabase("CINEMA");
//
//        DBCursor cursor = dbCollection.find();
//        while (cursor.hasNext()){
//            System.out.println(cursor.next());
//
//        }
    }
    public static void displayDataFromCollection(String collectionName){
        collection = database.getCollection("Movies");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()){
            Document document = cursor.next();

        }

    }

        }
