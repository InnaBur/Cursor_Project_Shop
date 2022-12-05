package com.cursor.project.shop;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        //map for users data
        Map<UUID, User> usersData = new HashMap<>();
        Map<String, String> usersDataNick = new HashMap<>();

        // map for the products with prices
        Map<String, Integer> productsData = new HashMap<>();
        Map<String, String> toysData = new HashMap<>();
        Map<String, String> booksData = new HashMap<>();

        Admin admin = new Admin();
        Toy toy = new Toy();
        Book book = new Book();
        User user = new User();
        System.out.println("Admin: " + admin.getNickname());


        user.showStartMenu();

        makeChoice(usersData, usersDataNick, admin, toysData, booksData);

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
    private static void makeChoice(Map<UUID, User> usersData, Map<String, String> usersDataNick,
                                   Admin admin, Map<String, String> toysData, Map<String, String> booksData) throws IOException {

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
                        addAdminMenu(admin, user, admin.toy, admin.book, toysData, booksData);
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
            user.showStartMenu();
        }
    }

    private static void addAdminMenu(Admin admin, User user, Toy toy, Book book,
                                     Map<String, String> toysData, Map<String, String> booksData) throws IOException {
        int num;
        Scanner scanner = new Scanner(System.in);
        admin.showMenu(admin);

        while (scanner.hasNextInt()) {

            num = scanner.nextInt();

            switch (num) {
                case 1:

                    /* reading txt file with toys database into a map
                     * which will be supplemented with products that will be provided by the admin
                     */
                    readFileToysData(toysData);

                    admin.addToy();
                    toysData.put(toy.getName(), toy.getPrice());
                    System.out.println("Toys data: " + toysData.toString());
                    break;
                case 2:
                    admin.blockUser(user);
                    break;
                case 3:
                case 4:
                case 5:
            }
            admin.showMenu(admin);
        }
    }

    /** In the method we read file with the name and the price of product (toy)
     *
     * @param toysData - into this collection data from the file is reading
     */
    private static void readFileToysData(Map<String, String> toysData) {
        String line;
        // adding the words and digits into array
        try {
            BufferedReader br = new BufferedReader(new FileReader("file/Products.txt"));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                String name = words[0]; // the name
                String price = words[1];
                toysData.put(name, price);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(("File is not found " + e.getMessage()));
        } catch (IOException e) {
            System.out.println(("Something went wrong " + e.getMessage()));
        }
    }

    private static void addUserMenu(User user) {
        int num;
        Scanner scanner = new Scanner(System.in);
        user.showMenu(user);

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
            System.out.println(user.getNickname() + " You are logging! Congrats!");
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



