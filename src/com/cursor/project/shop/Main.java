package com.cursor.project.shop;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // create new objects
        Admin admin = new Admin();
        Toy toy = new Toy();
        Book book = new Book();
        User user;


        //map for users data
        Map<UUID, User> usersData = new HashMap<>();
        Map<String, String> usersDataNick = new HashMap<>();
        Map<String, Boolean> userBool = new HashMap<>();

        // map for the products with prices
        Map<String, Integer> productsData = new HashMap<>();
        Map<String, String> toysData = new HashMap<>();
        Map<String, String> booksData = new HashMap<>();


        System.out.println("Admin: " + admin.getNickname());

        // reading data about products from the txt files
        readFileToysData(toysData, booksData, "file/Toys.txt");
        readFileToysData(toysData, booksData, "file/Books.txt");
        showStartMenu();

        makeChoice(usersData, usersDataNick, admin, toysData, booksData, userBool);

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
                                   Admin admin, Map<String, String> toysData, Map<String, String> booksData,  Map<String, Boolean> userBool) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int num;
        User user = null;

        while (scanner.hasNextInt()) {

            num = scanner.nextInt();

            switch (num) {
                case 1:
                    user = new User();
                    user.addUser();

                    if (!usersDataNick.containsKey(user.getNickname())) {
                        addUserIntoCollection(user, usersData, usersDataNick, userBool);
                        for (Map.Entry entry : usersData.entrySet())
                        {
                            System.out.println("key: " + entry.getKey().toString() + "; value: " + entry.getValue().toString());
                        }

                        break;

                    } else {
                        System.out.println("User is already exist! Try once more!");
                        break;
                    }

                case 2:
                    try {
                        loginUserOrAdmin(usersDataNick, admin, user, usersData, userBool);
                        System.out.println("User menu!");
                        addUserMenu(user, admin, admin.toy, admin.book, toysData, booksData, usersDataNick, usersData, userBool);

                        break;
                    } catch (UserNameOrPasswIsWrong e) {
                        System.out.println("" + e.getMessage());
                        break;
                    }
                    catch (UserIsBlocked e) {
                        System.out.println(" " + e.getMessage());
                    }
                case 3:
                    try {
                        loginUserOrAdmin(usersDataNick, admin, user, usersData, userBool);
                        addAdminMenu(admin, user, admin.toy, admin.book, toysData, booksData, usersDataNick, usersData, userBool);
                        break;
                    } catch (UserNameOrPasswIsWrong e) {
                        System.out.println("" + e.getMessage());
                        break;
                    } catch (UserIsBlocked ex) {
                        System.out.println("Blocked " + ex.getMessage());
                        break;
                    }
                case 4:
                    System.exit(0);
                case 5:
                    System.out.println("Incorrect choice! Good luck next time you run the program");
                    System.exit(0);
            }
            showSecondStartMenu();
        }
    }

    private static void addAdminMenu(Admin admin, User user, Toy toy, Book book,
                                     Map<String, String> toysData, Map<String, String> booksData,
                                     Map<String, String> usersDataNick, Map<UUID, User> usersData, Map<String, Boolean> userBool) throws IOException {
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
                    //readFileToysData(toysData, booksData, );

                    admin.addToy();
                    toysData.put(toy.getName(), toy.getPrice());
                    System.out.println("Toys data: " + toysData.toString());
                    break;
                case 2:
                    admin.blockUser(usersDataNick, user);  //чомусь блокує всіх юзерів
                    break;
                case 3:
                case 4:
                case 9:
                    showSecondStartMenu();
                    makeChoice(usersData, usersDataNick, admin, toysData, booksData, userBool);
                    break;
                case 6:
            }
            admin.showMenu(admin);
        }
    }

    /**
     * In the method we read file with the name and the price of product (toy)
     * @param toysData  into this collection data from the toys file is reading
     * @param booksData - into this collection data from the books file is reading
     * @param filename - the name of the file you need
     */
    private static void readFileToysData(Map<String, String> toysData, Map<String, String> booksData, String filename) {
        String line;
        // adding the words and digits into array
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                String name = words[0]; // the name
                String price = words[1];
                if (filename.equals("file/Toys.txt")) {
                    toysData.put(name, price);
                } else if (filename.equals("file/Books.txt")) {
                    booksData.put(name, price);
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(("File is not found " + e.getMessage()));
        } catch (IOException e) {
            System.out.println(("Something went wrong " + e.getMessage()));
        }
    }

    private static void addUserMenu(User user, Admin admin, Toy toy, Book book,
                                    Map<String, String> toysData, Map<String, String> booksData,
                                    Map<String, String> usersDataNick, Map<UUID, User> usersData, Map<String, Boolean> userBool) throws IOException {
        int num;
        Scanner scanner = new Scanner(System.in);
        user.showMenu(user);

        while (scanner.hasNextInt()) {

            num = scanner.nextInt();

            switch (num) {
                case 1:
                    System.out.println("Toys list: " + toysData.toString());
                    System.out.println("Books list: " + booksData.toString());
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 9:
                    showSecondStartMenu();
                    makeChoice(usersData, usersDataNick, admin, toysData, booksData, userBool);
                    break;
            }
            user.showMenu(user);
        }
    }

    private static void addUserIntoCollection(User user, Map<UUID, User> usersData,
                                              Map<String, String> usersDataNick, Map<String, Boolean> userBool) {
    userBool.put(user.getNickname(), user.isBlocked());
        usersData.put(user.getId(), user);
        usersDataNick.put(user.getNickname(), user.getPassword());
        System.out.println(user.getName() + "! You are registered!");

    }

    /**
     * The method is used for user login
     *
     * @param usersDataNick - database where the key is username
     * @throws UserNameOrPasswIsWrong - exeption
     */
    private static void loginUserOrAdmin(Map<String, String> usersDataNick, Admin admin, User user,
                                         Map<UUID, User> usersData,  Map<String, Boolean> userBool) throws UserNameOrPasswIsWrong, UserIsBlocked {
        //User user = new User();

        System.out.println("Input your nickname: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        System.out.println("Input your password: ");
        Scanner pas = new Scanner(System.in);
        String passw = pas.nextLine();


        if ((usersDataNick.containsKey(name)) && (usersDataNick.containsValue(passw))
                && (userBool.containsValue(false)) && (user != null)) {
            System.out.println(user.getNickname() + " You are logging! Congrats!");
        } else if ((usersDataNick.containsKey(name)) && (usersDataNick.containsValue(passw)) && (user != null)
                && (userBool.containsValue(true))) {
            throw new UserIsBlocked("You are blocked!");
        } else if ((admin.getNickname().equals(name)) && (admin.getPassword().equals(passw))) {
            System.out.println(admin.getNickname() + " Admin! You are logging! Congrats!");
        } else {
            throw new UserNameOrPasswIsWrong("Wrong nickname or password!");

        }

    }

    public static void showStartMenu() {
        System.out.println("Hi, there! Welcome to the toy store! \n Make your choice! If you want to register new user press 1 " +
                "\n If you want to login - press 2 " +
                "\n If you want to login as admin - press 3!" +
                "\n If you want to exit - press 4!");
    }
    public static void showSecondStartMenu() {
        System.out.println("Make your choice! If you want to register new user press 1 " +
                "\n If you want to login - press 2 " +
                "\n If you want to login as admin - press 3!" +
                "\n If you want to exit - press 4!");
    }

}



