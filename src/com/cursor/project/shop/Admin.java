package com.cursor.project.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin extends People {

    Product product = new Product();

    @Override
    public String getNickname() {
        return "Inna";
    }

    @Override
    public String getPassword() {
        return "abcd";
    }

    Map<String, Integer> productsData = new HashMap<>();

    public void addProduct() {

        Scanner scanner = new Scanner(System.in);
        //String[] array = new String[2];
        while (scanner.hasNextInt()) {

            // int num = scanner.nextInt();
            // switch (num) {
            //  case (1):
            Scanner productData = new Scanner(System.in);
            System.out.println("Input product name: ");
            String p = productData.nextLine();
            product.name = p;
            //   case (2):
            Scanner productPrice = new Scanner(System.in);
            System.out.println("Input product price: ");
            int pr = Integer.parseInt(productPrice.nextLine());
            product.price = pr;


        }

    }
}
