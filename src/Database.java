import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private String url = "jdbc:mysql://localhost/airline management system";
    private String user = "user";
    private String pass = "airline61";
    private static Statement statement;

    public Database() throws SQLException {
       Connection connection =  DriverManager.getConnection(url, user, pass);
       statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
              ResultSet.CONCUR_READ_ONLY );
    }
    public void AddPassenger(Passenger p) throws SQLException {
        String insert = "INSERT INTO passenger" +
                "s (id, firstName, lastName, Tel, email) VALUES (" +
                p.getId() + ", '" + p.getFirstName() + "', '" + p.getLastName() +
                "', '" + p.getTel() + "', '" + p.getEmail() + "');";

        statement.execute(insert);

    }
    public Passenger getPassenger(int id) throws SQLException {
        String get = "SELECT `id`, `firstName`, `lastName`, `Tel`, `email` FROM `passengers` WHERE`id` = "+id+";";
        ResultSet rs = statement.executeQuery(get);
        Passenger p = new Passenger();
        rs.next();
        p.setId(Integer.parseInt(rs.getString("id")));
        p.setFirstName(rs.getString("firstName"));
        p.setLastName(rs.getString("lastName"));
        p.setTel(rs.getString("Tel"));
        p.setEmail(rs.getString("email"));
        return p;
    }

    public Passenger getPassenger(String firstName, String lastName) throws SQLException {
        String get = "SELECT `id`, `firstName`, `lastName`, `Tel`, `email` FROM `passengers` WHERE`firstName' = "+firstName+";";
        ResultSet rs = statement.executeQuery(get);
        Passenger passenger = new Passenger();
        while (rs.next()) {
            Passenger p = new Passenger();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setTel(rs.getString("Tel"));
            p.setEmail(rs.getString("email"));

            if (p.getLastName().equals(lastName)) passenger = p; break;
        }
        return passenger;
    }

        public static void editPassenger(Passenger p) throws SQLException {
        String update = "UPDATE `passengers` SET `id`='"+p.getId()+"',`firstName`='"+p.getFirstName()+"',`lastName`='"+p.getLastName()+"',`Tel`='"+p.getTel()+"',`email`='"+p.getEmail()+"' WHERE `id`='"+p.getId()+"';";
        statement.execute(update);
        }
    public ArrayList<Passenger> getAllPassengers() throws SQLException {
        String get = "SELECT * FROM 'passengers';";
        ResultSet rs = statement.executeQuery(get);
        ArrayList<Passenger> passengers = new ArrayList<>();

        while(rs.next()) {
            Passenger p = new Passenger();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setTel(rs.getString("Tel"));
            p.setEmail(rs.getString("email"));
            passengers.add(p);

        }
        return passengers;
    }
}
