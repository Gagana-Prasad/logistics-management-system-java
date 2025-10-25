/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java;
import java.util.*;
import java.io.*;

public class MainJava {
    static final int MAX_CITIES = 30;
    static int cityCount = 0;
    static int[][] distance = new int[MAX_CITIES][MAX_CITIES];
    static java.util.Scanner x = new java.util.Scanner(System.in);
    static String[] cityNames = new String[MAX_CITIES];
    
    static int[][] dist = new int[MAX_CITIES][MAX_CITIES];
    static int safeInt() {
    while (true) {
        String s = SC.nextLine().trim(); 
        try {
            return Integer.parseInt(s); 
        } catch (Exception e) {
            System.out.print("Enter integer: "); 
        }
    }
}
    static int findCity(String name) {
    for (int i = 0; i < cityCount; i++)
        if (cityNames[i].equalsIgnoreCase(name)) return i;
    return -1;
}
    static boolean validCity(int idx) {
    return idx >= 0 && idx < cityCount;
}

    
    
    
    
   static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        initMatrix();
        
        boolean exe = true;

        while (exe) {
            System.out.println("\t\tLogistics Management System\t\t");
            System.out.println("1-> City Management");
            System.out.println("2-> Distance Management");
            System.out.println("3) Vehicle Reference");
            System.out.println("3-> Delivery");
            System.out.println("4-> Reports");
            System.out.println("5-> Exit");
            System.out.print(">> Choose: ");
            System.out.print("choice:");
            int choice=safeInt() ;
            switch (choice) {
                case 1 :
                    cityMenu();

            
            
                
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
    static void cityMenu() {
        while (true) {
            System.out.println("\n-- City Management --");
            System.out.println("1) Add City");
            System.out.println("2) Rename City");
            System.out.println("3) Remove City");
            System.out.println("4) List Cities");
            System.out.println("5) Back");
            System.out.print("Choose: ");
            int ch=safeInt() ;
            switch (ch) {
                case 1 :
                    addCity();
                break;    
                case 2:
                    renameCity();
                break;
                case 3:
                    removeCity();
                break;    
                case 4:
                    listCities();
                break;    
                case 5:
                { return; }
                default:
                    System.out.println("Invalid.");
            }
        }
    }
    static void addCity() {
        if (cityCount >= MAX_CITIES) { System.out.println("Max cities reached."); return; }
        System.out.print("Enter city name: ");
        String name = SC.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Empty name."); return; }
        if (findCity(name) != -1) { System.out.println("City already exists."); return; }

        cityNames[cityCount] = name;
        // init row/col
        for (int i = 0; i <= cityCount; i++) {
            dist[cityCount][i] = (cityCount == i) ? 0 : -1;
            dist[i][cityCount] = (cityCount == i) ? 0 : -1;
        }
        cityCount++;
        System.out.println("Added.");
    }

    static void renameCity() {
        if (cityCount == 0) { System.out.println("No cities."); return; }
        listCities();
        System.out.print("Index to rename: ");
        int idx = safeInt();
        if (!validCity(idx)) { System.out.println("Bad index."); return; }

        System.out.print("New name: ");
        String n = SC.nextLine().trim();
        if (n.isEmpty()) { System.out.println("Empty name."); return; }
        if (findCity(n) != -1) { System.out.println("Name exists."); return; }

        cityNames[idx] = n;
        System.out.println("Renamed.");
    }

    static void removeCity() {
        if (cityCount == 0) { System.out.println("No cities."); return; }
        listCities();
        System.out.print("Index to remove: ");
        int idx = safeInt();
        if (!validCity(idx)) { System.out.println("Bad index."); return; }

        // shift names
        for (int i = idx; i < cityCount - 1; i++) cityNames[i] = cityNames[i + 1];
        cityNames[cityCount - 1] = null;

        // shift matrix rows
        for (int i = idx; i < cityCount - 1; i++)
            for (int j = 0; j < cityCount; j++) dist[i][j] = dist[i + 1][j];
        // shift matrix cols
        for (int j = idx; j < cityCount - 1; j++)
            for (int i = 0; i < cityCount; i++) dist[i][j] = dist[i][j + 1];

        // clean tail
        for (int i = 0; i < cityCount; i++) { dist[cityCount - 1][i] = -1; dist[i][cityCount - 1] = -1; }
        dist[cityCount - 1][cityCount - 1] = 0;

        cityCount--;
        System.out.println("Removed.");
    }

    static void listCities() {
        System.out.println("Cities (" + cityCount + "):");
        for (int i = 0; i < cityCount; i++) System.out.println("  [" + i + "] " + cityNames[i]);
    }
    
    
    
    
}

