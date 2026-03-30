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
        Scanner s = new Scanner(System.in);
        System.out.println("월 저축액 입력 : ");
        int money = s.nextInt();
        System.out.println("원하는 만기일자 입력(개월) : ");
        int endPeriod = s.nextInt();


        List<Product> list = readCsv("KBbank.csv");
        //필터링
        List<Product> filtered = new ArrayList<>();

        for (Product p : list) {
            if (endPeriod == p.period) {
                filtered.add(p);
            }}

        filtered.sort((a, b) -> Double.compare(b.maxRate, a.maxRate));

        System.out.println("=== 추천 상품 ===");


        for (int i = 0; i < 3; i++) {
            Product p = filtered.get(i);
            double rate = p.baseRate /100;
            System.out.println(p.name + " | " + p.period + "개월 | "
                    + p.baseRate + "% | " + p.maxRate + "%");
            for (int j = 1; j <= p.period; j++){
                double interest = money * (rate/12) * j;
                System.out.printf("%d개월차 이자 : %.2f\n ", j , interest);
            }
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
