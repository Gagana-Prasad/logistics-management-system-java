/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java;
import java.util.*;
import java.io.*;

public class MainJava {
    static final int MAX_CITIES = 30;
    static final int MAX_DELIVERIES = 50;
    static int cityCount = 0;
    static double fullprice = 310.0;
    static int[][] distance = new int[MAX_CITIES][MAX_CITIES];
    static java.util.Scanner x = new java.util.Scanner(System.in);
    static String[] cityNames = new String[MAX_CITIES];
    static final String[] vehiclenames  = {"Van", "Truck", "Lorry"};
    static final int[]    capacity   = {1000,   5000,    10000};
    static final int[]    rateperKM = {30,     40,      45};
    static final int[]    averagespeed  = {60,     50,      40};
    static final int[]    effKMperl= {12,      6,       4};
    

    
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
                    break;
                case 2:
                    distancemenu();
                    break;
                case 3:
                    vehicleref();
                    break;
                
                    

            
            
                
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
        if (cityCount >= MAX_CITIES) 
        { System.out.println("Max cities reached."); return; }
        System.out.print("Enter city name: ");
        String name = SC.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Empty name."); return; }
        if (findCity(name) != -1) 
        { System.out.println("City already exists."); return; }

        cityNames[cityCount] = name;
        
        for (int i = 0; i <= cityCount; i++) {
            distance[cityCount][i] = (cityCount == i) ? 0 : -1;
            distance[i][cityCount] = (cityCount == i) ? 0 : -1;
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

        
        for (int i = idx; i < cityCount - 1; i++) 
            cityNames[i] = cityNames[i + 1];
        cityNames[cityCount - 1] = null;

        
        for (int i = idx; i < cityCount - 1; i++)
            for (int j = 0; j < cityCount; j++) 
                dist[i][j] = dist[i + 1][j];
        
        for (int j = idx; j < cityCount - 1; j++)
            for (int i = 0; i < cityCount; i++) dist[i][j] = dist[i][j + 1];

        
        for (int i = 0; i < cityCount; i++) 
        { distance[cityCount - 1][i] = -1; distance[i][cityCount - 1] = -1; }
        distance[cityCount - 1][cityCount - 1] = 0;

        cityCount--;
        System.out.println("Removed.");
    }

    static void listCities() {
        System.out.println("Cities (" + cityCount + "):");
        for (int i = 0; i < cityCount; i++) 
            System.out.println("  [" + i + "] " + cityNames[i]);
    }
    static void distancemenu() {
        if (cityCount < 2) { System.out.println("Add at least 2 cities first."); return; }
        while (true) {
            System.out.println("\t\tDistance Management\t\t");
            System.out.println("1->Edit distance(i,j)");
            System.out.println("2->Print distance table");
            System.out.println("3->Back");
            System.out.print("Choose: ");
            int ch = safeInt();
            switch (ch) {
                case 1:
                    setDistance();
                    break;
                case 2:
                    distancetable();
                    break;
                case 3:
                { return; }
                default:
                    System.out.println("Invalid.");
            }
        }
    }

    static void setDistance() {
        listCities();
        System.out.print("\tfrom index\t: ");
        int i = safeInt();
        System.out.print("\tto index\t: ");
        int j = safeInt();
        if (!validCity(i) || !validCity(j)) 
        { System.out.println("Bad index."); return; }
        if (i == j) 
        { System.out.println("Distance to self is 0."); return; }

        System.out.print("Enter distance km (>=0, or -1 to clear route): ");
        int d = safeInt();
        if (d < -1) { System.out.println("Invalid."); return; }
        if (d == 0) { System.out.println("0 only valid on diagonal."); return; }

        dist[i][j] = d;
        dist[j][i] = d;
        System.out.println("Updated " + cityNames[i] + "" + cityNames[j] + " = " + d);
    }

    static void distancetable() {
        System.out.print("       ");
        for (int j = 0; j < cityCount; j++) System.out.printf("%12s", "[" + j + "]");
        System.out.println();
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%-6s", "[" + i + "]");
            for (int j = 0; j < cityCount; j++) System.out.printf("%12s", dist[i][j]);
            System.out.println("   " + cityNames[i]);
        }
    }
    static void vehicleref() {
        System.out.println("\tType   Cap(kg)  Rate/km  AvgSpeed  Km/L\t");
        for (int g = 0; g < 3; g++) {
            System.out.printf("%-6s %7d %8d %9d %6d%n",
                vehiclenames[g], capacity[g], rateperKM[g], averagespeed[g], effKMperl[g]);
        }
        System.out.printf(Locale.US, "Fuel Price: %, .2f LKR/L%n", fullprice);

        System.out.print("do change fuel price? (y/n): ");
        String ans = SC.nextLine().trim().toLowerCase(Locale.ROOT);
        if (ans.equals("y") || ans.equals("yes")) {
            System.out.print("New fuel price (LKR/L): ");
            try {
                double fp = Double.parseDouble(SC.nextLine().trim());
                if (fp > 0) {
                    fullprice = fp;
                    System.out.println("price updated.");
                } else {
                    System.out.println("Invalid price.");
                }
            } catch (Exception e) {
                System.out.println("Invalid number.");
            }
        }
    }
    
    
    
     static class DijkstraResult {
        boolean reachable;
        int distance;
        String pathString;
    }

    static DijkstraResult dijkstra(int src, int dst) {
        int n = cityCount;
        int[] dmin = new int[n];
        boolean[] vis = new boolean[n];
        int[] prev = new int[n];
        Arrays.fill(dmin, Integer.MAX_VALUE / 4);
        Arrays.fill(prev, -1);
        dmin[src] = 0;

        for (int k = 0; k < n; k++) {
            int u = -1, best = Integer.MAX_VALUE / 4;
            for (int i = 0; i < n; i++)
                if (!vis[i] && dmin[i] < best) { best = dmin[i]; u = i; }
            if (u == -1) break;
            vis[u] = true;
            if (u == dst) break;

            for (int v = 0; v < n; v++) {
                int w = dist[u][v];
                if (w >= 0 && !vis[v]) {
                    if (dmin[u] + w < dmin[v]) {
                        dmin[v] = dmin[u] + w;
                        prev[v] = u;
                    }
                }
            }
        }
        

        DijkstraResult res = new DijkstraResult();
        if (dmin[dst] >= Integer.MAX_VALUE / 8) { res.reachable = false; return res; }
        res.reachable = true;
        res.distance = dmin[dst];

        
        List<Integer> path = new ArrayList<>();
        for (int cur = dst; cur != -1; cur = prev[cur]) path.add(cur);
        Collections.reverse(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(cityNames[path.get(i)]);
            if (i < path.size() - 1) sb.append(" -> ");
        }
        res.pathString = sb.toString();
        return res;
    }
    static void compareVehiclesFor(int s, int t, int w) {
        DijkstraResult res = dijkstra(s, t);
        if (!res.reachable) { System.out.println("No route."); return; }
        int D = res.distance;

        System.out.println("\n--- VEHICLE COMPARISON (" + cityNames[s] + " → " + cityNames[t] + ", " + w + " kg) ---");
        for (int v = 0; v < 3; v++) {
            if (w > capacity[v]) {
                System.out.printf("%-6s : capacity exceeded (>%d kg)%n", vehiclenames[v], capacity[v]);
                continue;
            }
            int R = rateperKM[v], S = averagespeed[v], E = effKMperl[v];
            double deliveryCost = D * R * (1.0 + (w / 10000.0));
            double timeHr = (double) D / S;
            double fuelUsed = (double) D / E;
            double fuelCost = fuelUsed * fullprice;
            double opCost = deliveryCost + fuelCost;
            double profit = opCost * 0.25;
            double charge = opCost + profit;

            System.out.printf(Locale.US,
                "%-6s : Distance=%dkm | Time=%.2fh | Charge=%, .2f LKR (Fuel=%, .2f | Op=%, .2f)%n",
                vehiclenames[v], D, timeHr, charge, fuelCost, opCost);
        }
    }
    
    
    
    
    
}

