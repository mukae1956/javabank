package Project;

import java.io.*;
import java.util.*;

public class Bank1 {

    static class Product {
        String name;
        int period;
        double baseRate;
        double maxRate;

        public Product(String name, int period, double baseRate, double maxRate) {
            this.name = name;
            this.period = period;
            this.baseRate = baseRate;
            this.maxRate = maxRate;
        }
    }

    public static void main(String[] args) {

        List<Product> list = readCsv("KBbank.csv");

        System.out.println("=== 전체 상품 ===");

        for (Product p : list) {
            System.out.println(p.name + " | " + p.period + "개월 | " + p.maxRate + "%");
        }
    }
    //csv 파일 읽기 
    public static List<Product> readCsv(String filePath) {
        List<Product> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), "CP949")
            );

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String name = data[0];
                int period = Integer.parseInt(data[1]);
                double baseRate = Double.parseDouble(data[2]);
                double maxRate = Double.parseDouble(data[3]);

                Product p = new Product(name, period, baseRate, maxRate);
                list.add(p);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
