import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PassengersController {

    public static void AddNewPassenger(Database database, Scanner s) throws SQLException {
        System.out.println("Enter first name: ");
        String firstName = s.next();
        System.out.println("Enter last name: ");
        String lastName = s.next();
        System.out.println("Enter tel: ");
        String Tel = s.next();
        System.out.println("Enter email: ");
        String email = s.next();

        Passenger passenger = new Passenger();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setTel(Tel);
        passenger.setEmail(email);
        ArrayList<Passenger> passengers = database.getAllPassengers();
        int id;
        if (passengers.size()!=0) {
            id = passengers.get(passengers.size()-1).getId()+1;
        }else  {
            id = 0;
        }
        passenger.setId(id);
        database.AddPassenger(passenger);
        System.out.println("Passenger added successfully!");
    }
    public static void EditPassenger(Database database, Scanner s) throws SQLException {
        System.out.println("Enter passenger id(int): \n(-1 to get passenger by name)");
        int id = s.nextInt();
        Passenger passenger;
        if (id == - 1) {
            passenger = getPassengerByName(database, s);
        }else {
            passenger = database.getPassenger(id);
        }


        System.out.println("Enter first name: \n(-1 to keep old value)");
        String firstName = s.next();
        if (firstName.equals("-1")) firstName = passenger.getFirstName();

        System.out.println("Enter last name: \n(-1 to keep old value)");
        String lastName = s.next();
        if (lastName.equals("-1")) lastName = passenger.getLastName();

        System.out.println("Enter Tel: \n(-1 to keep old value)");
        String Tel = s.next();
        if (Tel.equals("-1")) Tel = passenger.getTel();

        System.out.println("Entere Email: \n(-1 to keep old value)");
        String email = s.next();
        if (email.equals("-1")) email = passenger.getEmail();

        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setTel(Tel);
        passenger.setEmail(email);
        database.editPassenger(passenger);
        System.out.println("Passenger edited successfully!");

    }
    public static void findPassengerByName(Database database, Scanner s) throws SQLException {
        System.out.println("Enter first name: ");
        String firstName = s.next();
        System.out.println("Enter last name: ");
        String lastName = s.next();
        Passenger p = database.getPassenger(firstName, lastName);
        p.print();

    }
    public static Passenger getPassengerByName(Database database, Scanner s) throws SQLException {
        System.out.println("Enter first name: ");
        String firstName = s.next();
        System.out.println("Enter last name: ");
        String lastName = s.next();
        Passenger p = database.getPassenger(firstName, lastName);
        return p;

    }
    public static void printAllPassengers(Database database) throws SQLException{
        ArrayList<Passenger> passengers = database.getAllPassengers();
        System.out.println("\n---------------------------");
        for (Passenger p : passengers) {
            p.print();
        }
        System.out.println("---------------------------");


    }

}
