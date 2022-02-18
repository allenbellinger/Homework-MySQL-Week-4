package Application;

import DAO.UserDAO;
import Entity.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final UserDAO userDao = new UserDAO();
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> options = Arrays.asList("Display All Users", "Display A User", "Create User", "Update User", "Delete User");

    public void start() throws SQLException {
        String selection = "";
        while (true){
            printMenu();
            selection = scanner.nextLine();

            if (selection.equals("1")) {
                displayUsers();
            } else if (selection.equals("2")) {
                displayUser();
            } else if (selection.equals("3")) {
                createUser();
            } else if (selection.equals("4")) {
                updateUser();
            } else if (selection.equals("5")) {
                deleteUser();
            } else if (selection.equals("-1")) {
                break;
            }
        }
    }

    private void printMenu() {
        System.out.println("Select an option:\n-----------------------------------------------");
        for(int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ") " + options.get(i));
        }
        System.out.println("-1) Exit");
    }

    private void displayUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        for(User user : users) {
            System.out.println("User ID: " + user.getUserId() + ", First Name: " + user.getFirstName() + ", Last Name: " + user.getLastName());
        }
        System.out.println("Press enter to continue: ");
        scanner.nextLine();
    }

    private void displayUser() throws SQLException {
        System.out.println("Please enter the ID of the user you would like to display: ");
        int input = Integer.parseInt(scanner.nextLine());
        User user = userDao.getUser(input);
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Press enter to continue: ");
        scanner.nextLine();
    }

    private void createUser() throws SQLException {
        System.out.println("Please enter the ID of the new user: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the First Name of the new user: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter the Last Name of the new user: ");
        String lastName = scanner.nextLine();

        userDao.createUser(id, firstName, lastName);
        System.out.println("New user created with ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName);
        System.out.println("Press enter to continue: ");
        scanner.nextLine();
    }

    private void updateUser() throws SQLException {
        System.out.println("Please enter the ID of the user you want to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the new First Name of the user: ");
        String newFirstName = scanner.nextLine();
        System.out.println("Please enter the new Last Name of the user: ");
        String newLastName = scanner.nextLine();

        userDao.updateUser(id, newFirstName, newLastName);
        System.out.println("User " + id + " has been updated to have First Name: " + newFirstName + " and Last Name: " + newLastName);
        System.out.println("Press enter to continue: ");
        scanner.nextLine();
    }

    private void deleteUser() throws SQLException {
        System.out.println("Please enter the ID of the user you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        userDao.deleteUser(id);
        System.out.println("User with ID: " + id + " has been deleted.");
        System.out.println("Press enter to continue: ");
        scanner.nextLine();
    }
}
