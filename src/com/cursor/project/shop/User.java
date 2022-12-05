package com.cursor.project.shop;

import java.util.Scanner;
import java.util.UUID;

public class User extends People {

    private UUID id = UUID.randomUUID();
    private String username;
    private String password;

    private String name;
    private String lastName;

    private boolean isBlocked;

    public boolean isBlocked() {
        return isBlocked = true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public void showMenu(Admin admin) {

    }

    @Override
    public void showMenu(User user) {
        System.out.println("Make your choice! If you want to see products list - press 1 " +
                "\n If you want to find the product - press 2 " +
                "\n If you want to add the product into your order - press 3!" +
                "\n If you want to remove the product from your order - press 4" +
                "\n If you want to see your order - press 5!");
    }

    public void addUser() {

        String[] array = new String[4];
        for (int i = 0; i < 1; i++) {
            switch (i) {
                case (0):
                    Scanner username = new Scanner(System.in);
                    System.out.println("Input your nickname: ");
                    String u = username.nextLine();
                    array[0] = u;
                case (1):
                    Scanner password = new Scanner(System.in);
                    System.out.println("Input your password: ");
                    String p = password.nextLine();
                    array[1] = p;
                case (2):
                    Scanner name = new Scanner(System.in);
                    System.out.println("Input your name: ");
                    String n = name.nextLine();
                    array[2] = n;
                case (3):
                    Scanner lastName = new Scanner(System.in);
                    System.out.println("Input your last name: ");
                    String l = lastName.nextLine();
                    array[3] = l;
            }
        }

        this.username = array[0];
        this.password = array[1];
        this.name = array[2];
        this.lastName = array[3];
        this.isBlocked = false;


    }

    public void showStartMenu() {
        System.out.println("Hi, there! Make your choice! If you want to register new user press 1 " +
                "\n If you want to login - press 2 " +
                "\n If you want to login as admin - press 3!" +
                "\n If you want to exit - press 4!");
    }
}
