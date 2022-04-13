package dev.gonevski.services;

import dev.gonevski.api.Main;
import dev.gonevski.data.DAOXYZUserImplementation;
import dev.gonevski.entities.XYZUser;
import dev.gonevski.data.DAOXYZUser;
import dev.gonevski.utilities.XYZLinkedList;

import java.util.Objects;
import java.util.Scanner;

public class XYZUserServiceImplementation implements XYZUserService {

    private DAOXYZUser userDAO;

    // Dependency Injection. Building an object that uses another object within it
    public XYZUserServiceImplementation(DAOXYZUser userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void logIn() {
        Scanner input = new Scanner(System.in);
        int option;

        System.out.println("==========" +
                "\nUSER LOGIN" +
                "\n==========");
        System.out.println("Enter your username:");
        String userLogin = input.nextLine();
        XYZUser user = userDAO.getUserByName(userLogin);

        while (user == null) {
            System.out.println("ERROR: User not found.");
            option = Main.userNotFoundMenu();

            if (option == 1) {
                System.out.println("Enter your username:");
                userLogin = input.nextLine();
                user = userDAO.getUserByName(userLogin);
            } else {
                break;
            }
        }

        if (user != null) {
            System.out.println("Enter your password:");
            String userPassword = input.nextLine();

            while (!Objects.equals(user.getUserPassword(), userPassword)) {
                System.out.println("ERROR: Invalid password. Please try again.");
                option = Main.passwordNotFoundMenu();

                if (option == 1) {
                    System.out.println("Enter your password:");
                    userPassword = input.nextLine();
                } else {
                    break;
                }
            }

            if (Objects.equals(user.getUserPassword(), userPassword)) {
                System.out.println("Login successful! Welcome.");
                Main.loggedInUserId = user.getUserId();
                Main.loggedIn = true;
            }
        }
    }

    @Override
    public void register() {
        Scanner input = new Scanner(System.in);

        System.out.println("========" +
                "\nNEW USER" +
                "\n========");
        System.out.println("Enter a username:");
        String newLogin = input.nextLine();

        //Populate a list of users from the db to perform unique username checking.
        XYZLinkedList<XYZUser> userList = userDAO.getAllUsers();
        boolean userExists = userList.checkIfExists(newLogin);

        while (userExists) {
            System.out.println("ERROR: Username unavailable. Please choose a different one.");
            newLogin = input.nextLine();
            userExists = userList.checkIfExists(newLogin);
        }

        System.out.println("Thank you. Now, create a password:");
        String newPassword = input.nextLine();
        System.out.println("Password confirmation. Type your password again:");
        String newPasswordConfirm = input.nextLine();

        while (!Objects.equals(newPasswordConfirm, newPassword)) {
            System.out.println("ERROR: Passwords do not match. Try again.");
            newPasswordConfirm = input.nextLine();
        }

        XYZUser newXYZUser = new XYZUser(0, newLogin, newPassword);
        userDAO.addUser(newXYZUser);
        System.out.println("Account successfully created!");
    }
}
