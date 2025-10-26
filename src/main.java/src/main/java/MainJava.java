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
    static class Delivery {
        int from, to, vehType, distKm, weightKg;
        double timeHr, baseCost, fuelUsed, fuelCost, opCost, profit, charge;
        String path;
    }
    static Delivery[] deliveries = new Delivery[MAX_DELIVERIES];
    static int deliveryCount = 0;
    static final String ROUTES_FILE = "routes.txt";
    static final String DELIVERIES_FILE = "deliveries.txt";


    
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
        loadRoutesIfExists();
        loadDeliveriesIfExists();
        
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
                case 4:
                    newdelivery();
                    break;
                case 5:
                    showreports();
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
     static void newdelivery() {
        if (cityCount < 2) { System.out.println("Add cities first."); return; }

        listCities();
        System.out.print("From index: ");
        int s = safeInt();
        System.out.print("To index: ");
        int t = safeInt();
        if (!validCity(s) || !validCity(t) || s == t) { System.out.println("Invalid source/destination."); return; }

        System.out.print("Weight (kg): ");
        int w = safeInt();
        if (w <= 0) { System.out.println("Invalid weight."); return; }

        showVehicleTable();
        System.out.print("Vehicle (0=Van,1=Truck,2=Lorry): ");
        int v = safeInt();
        if (v < 0 || v > 2) { System.out.println("Invalid vehicle."); return; }
        if (w > capacity[v]) { System.out.println("Weight exceeds vehicle capacity."); return; }

        DijkstraResult res = dijkstra(s, t);
        if (!res.reachable) { System.out.println("No route found."); return; }

        int D = res.distance;
        int R = rateperKM[v], S = averagespeed[v], E = effKMperl[v];

        double deliveryCost = D * R * (1.0 + (w / 10000.0));
        double timeHr = (double) D / S;
        double fuelUsed = (double) D / E;
        double fuelCost = fuelUsed * fullprice;
        double opCost = deliveryCost + fuelCost;
       
        double profit = opCost * 0.25;
        double charge = opCost + profit;

        System.out.println("\n======================================================");
        System.out.println("DELIVERY COST ESTIMATION");
        System.out.println("------------------------------------------------------");
        System.out.println("From: " + cityNames[s]);
        System.out.println("To: " + cityNames[t]);
        System.out.println("Path: " + res.pathString);
        System.out.println("Minimum Distance: " + D + " km");
        System.out.println("Vehicle: " + vehiclenames[v]);
        System.out.println("Weight: " + w + " kg");
        System.out.println("------------------------------------------------------");
        System.out.printf(Locale.US, "Base Cost: %d × %d × (1 + %d/10000) = %, .2f LKR%n", D, R, w, deliveryCost);
        System.out.printf(Locale.US, "Fuel Used: %.2f L%n", fuelUsed);
        System.out.printf(Locale.US, "Fuel Cost: %, .2f LKR%n", fuelCost);
        System.out.printf(Locale.US, "Operational Cost: %, .2f LKR%n", opCost);
        System.out.printf(Locale.US, "Profit: %, .2f LKR%n", profit);
        System.out.printf(Locale.US, "Customer Charge: %, .2f LKR%n", charge);
        System.out.printf(Locale.US, "Estimated Time: %.2f hours%n", timeHr);
        System.out.println("======================================================");

       
        if (deliveryCount < MAX_DELIVERIES) {
            Delivery d = new Delivery();
            d.from = s; d.to = t; d.vehType = v; d.weightKg = w; d.distKm = D;
            d.timeHr = timeHr; d.baseCost = deliveryCost; d.fuelUsed = fuelUsed;
            d.fuelCost = fuelCost; d.opCost = opCost; d.profit = profit; d.charge = charge;
            d.path = res.pathString;
            deliveries[deliveryCount++] = d;
            
        } else {
            System.out.println("WARNING: Delivery store full; not saved in memory.");
        }

        
        System.out.print("Compare the speed of all 3 cars? (y/n): ");
        String ans = SC.nextLine().trim().toLowerCase(Locale.ROOT);
        if (ans.equals("y") || ans.equals("yes")) {
            compareVehiclesFor(s, t, w);
        }
    }

    static void showVehicleTable() {
        System.out.println("\nType   Cap(kg)  Rate/km  AvgSpeed  Km/L");
        for (int v = 0; v < 3; v++) {
            System.out.printf("%-6s %7d %8d %9d %6d%n",
                vehiclenames[v],capacity[v], rateperKM[v], averagespeed[v], effKMperl[v]);
        }
    }
    static void showreports() {
        if (deliveryCount == 0) { System.out.println("No deliveries yet."); return; }

        int totalDeliveries = deliveryCount;
        long totDistance = 0;
        double tottime = 0.0, totalRevenue = 0.0, totalProfit = 0.0;

        int longest = Integer.MIN_VALUE, shortest = Integer.MAX_VALUE;
        int idxLongest = -1, idxShortest = -1;

        for (int i = 0; i < deliveryCount; i++) {
            Delivery d = deliveries[i];
            totDistance += d.distKm;
            tottime += d.timeHr;
            totalRevenue += d.charge;
            totalProfit += d.profit;

            if (d.distKm > longest) { longest = d.distKm; idxLongest = i; }
            if (d.distKm >= 0 && d.distKm < shortest) { shortest = d.distKm; idxShortest = i; }
        }

        double avgtime = tottime / totalDeliveries;

        System.out.println("\n--- REPORTS ---");
        System.out.println("Total Deliveries Completed: " + totalDeliveries);
        System.out.println("Total Distance Covered: " + totDistance + " km");
        System.out.printf(Locale.US, "Average Delivery Time: %.2f hours%n", avgtime);
        System.out.printf(Locale.US, "Total Revenue (charges): %, .2f LKR%n", totalRevenue);
        System.out.printf(Locale.US, "Total Profit: %, .2f LKR%n", totalProfit);

        if (idxLongest != -1) {
            Delivery d = deliveries[idxLongest];
            System.out.println("Longest Route: " + cityNames[d.from] + " -> " + cityNames[d.to] + " (" + d.distKm + " km)");
        }
        if (idxShortest != -1) {
            Delivery d = deliveries[idxShortest];
            System.out.println("Shortest Route: " + cityNames[d.from] + " -> " + cityNames[d.to] + " (" + d.distKm + " km)");
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
    static void loadRoutesIfExists() {
        File f = new File(ROUTES_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            if (line == null) return;
            cityCount = Integer.parseInt(line.trim());
            for (int i = 0; i < cityCount; i++) cityNames[i] = br.readLine();

            String marker = br.readLine();
            if (marker == null || !marker.trim().equals("MATRIX")) return;

            for (int i = 0; i < cityCount; i++) {
                String[] parts = br.readLine().trim().split("\\s+");
                for (int j = 0; j < cityCount; j++) dist[i][j] = Integer.parseInt(parts[j]);
            }
            System.out.println("[Loaded routes.txt]");
        } catch (Exception e) {
            System.out.println("Failed to load routes.txt: " + e.getMessage());
        }
    }
    static void saveRoutes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ROUTES_FILE))) {
            pw.println(cityCount);
            for (int i = 0; i < cityCount; i++) pw.println(cityNames[i]);
            pw.println("MATRIX");
            for (int i = 0; i < cityCount; i++) {
                for (int j = 0; j < cityCount; j++) {
                    pw.print(dist[i][j]);
                    if (j < cityCount - 1) pw.print(" ");
                }
                pw.println();
            }
        } catch (Exception e) {
            System.out.println("Failed to save routes.txt: " + e.getMessage());
        }
    }

    static void loadDeliveriesIfExists() {
        File f = new File(DELIVERIES_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null && deliveryCount < MAX_DELIVERIES) {
                
                String[] p = splitCsv(line);
                if (p.length < 13) continue;

                Delivery d = new Delivery();
                d.from = parseIntSafe(p[0], -1);
                d.to = parseIntSafe(p[1], -1);
                d.weightKg = parseIntSafe(p[2], 0);
                d.vehType = parseIntSafe(p[3], 0);
                d.distKm = parseIntSafe(p[4], 0);
                d.timeHr = parseDoubleSafe(p[5], 0);
                d.baseCost = parseDoubleSafe(p[6], 0);
                d.fuelUsed = parseDoubleSafe(p[7], 0);
                d.fuelCost = parseDoubleSafe(p[8], 0);
                d.opCost = parseDoubleSafe(p[9], 0);
                d.profit = parseDoubleSafe(p[10], 0);
                d.charge = parseDoubleSafe(p[11], 0);
                d.path = p[12];
                deliveries[deliveryCount++] = d;
            }
            System.out.println("[Loaded deliveries.txt]");
        } catch (Exception e) {
            System.out.println("Failed to load deliveries.txt: " + e.getMessage());
        }
    }
    
    static int parseIntSafe(String s, int def) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return def; }
    }
    static double parseDoubleSafe(String s, double def) {
        try { return Double.parseDouble(s.trim()); } catch (Exception e) { return def; }
    }
     static void saveDeliveries() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DELIVERIES_FILE))) {
            for (int k = 0; k < deliveryCount; k++) {
                Delivery d = deliveries[k];
                // CSV safe for path (no commas in path " -> ")
                pw.printf(Locale.US,
                    "%d,%d,%d,%d,%d,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%s%n",
                    d.from, d.to, d.weightKg, d.vehType, d.distKm,
                    d.timeHr, d.baseCost, d.fuelUsed, d.fuelCost, d.opCost, d.profit, d.charge,
                    d.path
                );
            }
        } catch (Exception e) {
            System.out.println("Failed to save deliveries.txt: " + e.getMessage());
        }
    }
     static void appendDeliveryToFile(Delivery d) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DELIVERIES_FILE, true))) {
            pw.printf(Locale.US,
                "%d,%d,%d,%d,%d,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%s%n",
                d.from, d.to, d.weightKg, d.vehType, d.distKm,
                d.timeHr, d.baseCost, d.fuelUsed, d.fuelCost, d.opCost, d.profit, d.charge,
                d.path
            );
        } catch (Exception e) {
            System.out.println("Failed to append deliveries.txt: " + e.getMessage());
        }
    }

    static void saveAll() {
        saveRoutes();
        saveDeliveries();
    }
    static void initmatrix() {
        for (int i = 0; i < MAX_CITIES; i++)
            for (int j = 0; j < MAX_CITIES; j++)
                dist[i][j] = (i == j) ? 0 : -1;
    }

    static int findcity(String name) {
        for (int i = 0; i < cityCount; i++)
            if (cityNames[i].equalsIgnoreCase(name)) return i;
        return -1;
    }

    static boolean validcity(int idx) { return idx >= 0 && idx < cityCount; }

    static int safeint() {
        while (true) {
            String s = SC.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (Exception e) { System.out.print("Enter integer: "); }
        }
    }
    static String[] splitCsv(String line) {
        return line.split(",", -1);
    }
    
    
    
    
    
    
}

