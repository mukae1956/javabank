package Project.Weekend;

import java.io.*;
import java.util.*;

public class CsvReader {

    public static List<Product> readCsv(String filePath) {
        List<Product> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), "CP949")
            );

            String line;
            br.readLine(); // 헤더 제거

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String bankName = data[0];
                String name = data[1];
                int period = Integer.parseInt(data[2]);
                double baseRate = Double.parseDouble(data[3]);
                double maxRate = Double.parseDouble(data[4]);

                list.add(new Product(bankName, name, period, baseRate, maxRate));
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}