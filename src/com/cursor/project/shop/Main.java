package com.cursor.project.shop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {

        //map for users data
        Map<UUID, User> usersData = new HashMap<>();
        Map<String, String> usersDataNick = new HashMap<>();

        // map for the products with prices
        Map<String, Integer> productsData = new HashMap<>();

        Admin admin = new Admin();
        System.out.println("Admin: " + admin.getNickname());
        
        
        System.out.println("Hi, there! Make your choice! If you want to register new user press 1 " +
                "\n If you want to login - press 2 " +
                "\n If you want to login as admin - press 3!" +
                "\n If you want to exit - press 4!");

        makeChoice(usersData, usersDataNick, admin);

        // printing all users list
        System.out.println(usersData);
        System.out.println("You press any key or exit! See you next time!");



    }

    /**
     * In the method user can make his choise: register, login,
     * find user by id or exit
     *
     * @param usersData     - database for finding by id
     * @param usersDataNick - database for login user
     */
    private static void makeChoice(Map<UUID, User> usersData, Map<String, String> usersDataNick, Admin admin) throws IOException {
        FileWriter fileWriter = null;
        Scanner scanner = new Scanner(System.in);
        int num;
        User user = null;

        while (scanner.hasNextInt()) {

            num = scanner.nextInt();

            switch (num) {
                case 1:
                    user = new User();
                    user.addUser();
                    if (!usersDataNick.containsKey(user.getUsername())) {
                        addUserIntoCollection(user, usersData, usersDataNick);
                        break;

                    } else {
                        System.out.println("User is already exist! Try once more!");
                        break;
                    }
                    
                case 2:
                    try {
                        loginUserOrAdmin(usersDataNick, admin, user);
                        System.out.println("User menu!");
                        addUserMenu(user);
                        break;
                    } catch (UserNameOrPasswIsWrong e) {
                        System.out.println("" + e.getMessage());
                        break;
                    }
                case 3:
                    try {
                        loginUserOrAdmin(usersDataNick, admin, user);
                        addAdminMenu(admin);
                        break;
                    } catch (UserNameOrPasswIsWrong e) {
                        System.out.println("" + e.getMessage());
                        break;
                    }
                case 4:
                    System.exit(0);
                case 5:
                    System.out.println("Incorrect choice! Good luck next time you run the program");
                    System.exit(0);
            }
            System.out.println("Make your choice! If you want to register new user press 1 " +
                    "\n If you want to login - press 2 " +
                    "\n If you want to login as admin - press 3!" +
                    "\n If you want to exit - press 4!");
        }
    }

    private static void addAdminMenu(Admin admin) {
        System.out.println("Ammin menu!!!!!");
    }

    private static void addUserMenu(User user) {
        int num;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make your choice! If you want to see products list - press 1 " +
                "\n If you want to find the product - press 2 " +
                "\n If you want to add the product into your order - press 3!" +
                "\n If you want to remove the product from your order - press 4" +
                "\n If you want to see your order - press 5!");

        while (scanner.hasNextInt()) {

            num = scanner.nextInt();

            switch (num) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
            }
        }
    }

    private static void addUserIntoCollection(User user, Map<UUID, User> usersData, Map<String, String> usersDataNick) throws IOException {
        FileWriter fileWriter;
        usersData.put(user.getId(), user);
        usersDataNick.put(user.getUsername(), user.getPassword());

        fileWriter = new FileWriter("Users.txt");
        fileWriter.write(usersDataNick.toString() + user.getId());
        fileWriter.close();

        System.out.println(user.getNickname() + "! You are registered!");

    }


    /**
     * The method is used for user login
     *
     * @param usersDataNick - database where the key is username
     * @throws UserNameOrPasswIsWrong - exeption
     */
    private static void loginUserOrAdmin(Map<String, String> usersDataNick, Admin admin, User user) throws UserNameOrPasswIsWrong {
        System.out.println("Input your nickname: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        System.out.println("Input your password: ");
        Scanner pas = new Scanner(System.in);
        String passw = pas.nextLine();

        if ((usersDataNick.containsKey(name)) && (usersDataNick.containsValue(passw))) {
            System.out.println(user.getNickname()+ " You are logging! Congrats!");
        } else if ((admin.getNickname().equals(name)) && (admin.getPassword().equals(passw))) {
            System.out.println(admin.getNickname() + " Admin! You are logging! Congrats!");
        } else {
            throw new UserNameOrPasswIsWrong("Wrong nickname or password!");

        }

    }

    /**
     * The method allows to find user by id
     *
     * @param usersData -  database where the key is user id
     * @param k         - is value from user input
     * @throws UserIsNotFoundException - the exception
     */
    private static void findUserById(Map<UUID, User> usersData, String k) throws UserIsNotFoundException {
        if ((!usersData.isEmpty()) && (usersData.containsKey(k))) {
            System.out.println("Find user for id! The user is: " + usersData.get(k).getName());
        } else {
            throw new UserIsNotFoundException("There is not user with this ID!");
        }
    }
}



