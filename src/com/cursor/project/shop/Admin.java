package com.cursor.project.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin extends People {

    Product product = new Product();
    Toy toy = new Toy();
    Book book = new Book();
    User user = new User();

    @Override
    public String getNickname() {
        return "Inna";
    }

    @Override
    public String getPassword() {
        return "abcd";
    }

    Map<String, Integer> productsData = new HashMap<>();

    public void addToy() {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            Scanner productData = new Scanner(System.in);
            System.out.println("Input product name: ");
            String p = productData.nextLine();
            toy.name = p;
            //   case (2):
            Scanner productPrice = new Scanner(System.in);
            System.out.println("Input product price: ");
            String pr = productPrice.nextLine();
            toy.price = pr;
            break;
        }

    }
    
    public void blockUser(User user) {
        user.isBlocked();
        System.out.println(user.getNickname() + " is blocked");
    }

    @Override
    public void showMenu(Admin admin) {
        System.out.println(admin.getNickname() + " Make your choice! If you want to add product to the list - press 1 " +
                "\n If you want to block user - press 2 " +
                "\n If you want to unblock user - press 3!" +
                "\n If you want to confirm order - press 4");
    }

    @Override
    public void showMenu(User user) {

    }


}
