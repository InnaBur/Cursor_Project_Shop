package com.cursor.project.shop;

import java.util.Scanner;
import java.util.UUID;

public class User extends People {

    private UUID id = UUID.randomUUID();
    private String username;
    private String password;

    private String name;

    private String lastName;


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

    public void addUser() {

        String[] array = new String[4];
        for(int i = 0; i < 1; i++) {
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



    }
}
