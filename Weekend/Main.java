package Project.Weekend;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("월 저축액 입력 : ");
        int money = s.nextInt();

        System.out.println("원하는 만기일자 입력(개월) : ");
        int endPeriod = s.nextInt();

        List<Product> list = CsvReader.readCsv("Bank_data.csv");

        // 필터링
        List<Product> filtered = new ArrayList<>();
        for (Product p : list) {
            if (p.period == endPeriod) {
                filtered.add(p);
            }
        }

        // 금리 높은 순 정렬
        filtered.sort((a, b) -> Double.compare(b.baseRate, a.baseRate));

        // 상위 6개 선택
        List<Product> topProducts = filtered.subList(0, Math.min(6, filtered.size()));

        // 데이터 계산
        Map<Product, double[]> dataMap = new LinkedHashMap<>();

        for (Product p : topProducts) {
            double[] interests = InterestCalculator.calculate(money, p);
            dataMap.put(p, interests);

            System.out.println(p.bankName + " | " + p.name + " | " + p.baseRate + "%");
        }

        // 그래프 출력
        Chart.drawMultiGraph(dataMap);
    }
}