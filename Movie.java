import java.net.UnknownHostException;
import java.sql.SQLException;


public class Movie {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, UnknownHostException {
        Globals.createConnection();
        Globals.mongoConnection();

//        Globals.displayDataFromCollection("Movies");
//        Globals.loadDataFromMongoDb();
//        System.out.println(Globals.getPrimaryKey("movies"));
        new FrontPage();
//        new LoginPanelAdmin();

//        new LogInPanel() ;
//        new CustomerWindow();
//        new AdminWindow();
//        Globals.mongoSearch();
//            Globals.insertMongo("Movies");



    }

}
