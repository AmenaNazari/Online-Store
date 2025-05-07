package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }


    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    inventory.add(new Product(id, name, price));
                }

            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }

    // This method should read a CSV file with product information and
    // populate the inventory ArrayList with com.pluralsight.Product objects. Each line
    // of the CSV file contains product information in the following format:
    //
    // id,name,price
    //
    // where id is a unique string identifier, name is the product name,
    // price is a double value representing the price of the product


    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        System.out.println("\nAvailable Products:");
        for (Product p : inventory) {
            System.out.println(p);
        }

        System.out.print("Enter Product ID to add to cart (or 'x' to cancel): ");
        String input = scanner.nextLine();

        if (!input.equalsIgnoreCase("x")) {
            Product selected = findProductById(input, inventory);
            if (selected != null) {
                cart.add(selected);
                System.out.println("Added to cart: " + selected.getName());
            } else {
                System.out.println("Product not found.");
            }
        }
    }



                // This method should display a list of products from the inventory,
                // and prompt the user to add items to their cart. The method should
                // prompt the user to enter the ID of the product they want to add to
                // their cart. The method should
                // add the selected product to the cart ArrayList.


                public static void displayCart (ArrayList < Product > cart, Scanner scanner,double totalAmount){
                    if (cart.isEmpty()) {
                        System.out.println("\nCart is empty.");
                        return;
                    }

                    boolean removing = true;

                    while (removing) {
                        System.out.println("\nYour Cart:");
                        double total = 0.0;
                        for (Product product : cart) {
                            System.out.printf("ID: %s | Name: %s | Price: $%.2f\n",
                                    product.getId(), product.getName(), product.getPrice());
                            total += product.getPrice();
                        }
                        System.out.printf("Total: $%.2f\n", total);

                        System.out.print("Do you want to remove an item by ID? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();

                        if (response.equals("yes")) {
                            System.out.print("Enter the product ID to remove: ");
                            String idToRemove = scanner.nextLine().trim();
                            boolean found = false;

                            for (int i = 0; i < cart.size(); i++) {
                                if (cart.get(i).getId().equalsIgnoreCase(idToRemove)) {
                                    Product removed = cart.remove(i);
                                    System.out.println("Removed: " + removed.getName());
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                System.out.println("Product with ID '" + idToRemove + "' not found in cart.");
                            }
                        } else {
                            removing = false;
                        }
                    }
                }
                // This method should display the items in the cart ArrayList, along
                // with the total cost of all items in the cart. The method should
                // prompt the user to remove items from their cart by entering the ID
                // of the product they want to remove. The method should update the cart ArrayList and totalAmount
                // variable accordingly.

            }

    public static void checkOut(ArrayList<Product> cart, double totalAmount, Scanner scanner) {

            if (cart.isEmpty()) {
                System.out.println("Your cart is empty. Nothing to checkout.");
                return;
            }

            double total = 0.0;
            System.out.println("\n--- Checkout Summary ---");
            for (Product product : cart) {
                System.out.printf("ID: %s | Name: %s | Price: $%.2f\n",
                        product.getId(), product.getName(), product.getPrice());
                total += product.getPrice();
            }
            System.out.printf("Total Amount: $%.2f\n", total);

            System.out.print("Do you want to proceed with the purchase? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes")) {
                System.out.print("Enter payment amount: $");
                double payment = scanner.nextDouble();
                scanner.nextLine(); // consume newline

                if (payment >= total) {
                    double change = payment - total;
                    System.out.printf("Payment accepted. Your change: $%.2f\n", change);
                    cart.clear();
                    System.out.println("Thank you for your purchase!");
                } else {
                    System.out.printf("Insufficient payment. You still owe $%.2f\n", total - payment);
                }
            } else {
                System.out.println("Purchase canceled.");
            }
        }


        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and calculate change and clear the cart
        // if they confirm.


    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null; // If not found
    }


        // This method should search the inventory ArrayList for a product with
        // the specified ID, and return the corresponding com.pluralsight.Product object. If
        // no product with the specified ID is found, the method should return
        // null.





