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

   
    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
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
                    System.out.println("(City menu – develop next)"); 
                break;
                case "2": 
                    System.out.println("(Distance menu – develop next)"); 
                break;
                case "3": 
                    System.out.println("(Delivery – develop next)");
                break;
                case "4": 
                    System.out.println("(Reports – develop next)"); 
                break;
                case "5": 
                    exe = false; 
                break;
                default: 
                    System.out.println("Invalid option.");
            }
        }
        System.out.println("Bye!");
        x.close();
    }
    
}
