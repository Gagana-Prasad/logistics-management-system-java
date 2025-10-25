/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class MainJava {
    static final int MAX_CITIES = 30;
    static int[][] distance = new int[MAX_CITIES][MAX_CITIES];
    static java.util.Scanner x = new java.util.Scanner(System.in);
    
   
    public static void main(String[] args) {
        initMatrix();
        boolean exe = true;

        while (exe) {
            System.out.println("\t\tLogistics Management System\t\t");
            System.out.println("1-> City Management");
            System.out.println("2-> Distance Management");
            System.out.println("3-> Delivery");
            System.out.println("4-> Reports");
            System.out.println("5-> Exit");
            System.out.print(">> Choose: ");

            String choice = x.nextLine().trim();
            switch (choice) {
                case "1": 
                    System.out.println("(City menu)"); 
                break;
                case "2": 
                    System.out.println("(Distance menu)"); 
                break;
                case "3": 
                    System.out.println("(Delivery)");
                break;
                case "4": 
                    System.out.println("(Reports )"); 
                break;
                case "5": 
                    exe = false; 
                break;
                default: 
                    System.out.println("Invalid option.");
            }
        }   
    }
    static void initMatrix() {
        for (int i = 0; i < MAX_CITIES; i++) {
            for (int j = 0; j < MAX_CITIES; j++) {
                distance[i][j] = (i == j) ? 0 : -1;
            }
        }
    }
    
    
    
}

