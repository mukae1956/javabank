package JavaBank;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Input i = new Input();

        while (true) {
            try {
                System.out.print("월 저축액 입력 : ");
                i.money = s.nextInt();
                i.input(); // 검사
                break; // 정상 입력이면 반복 종료
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요!");
                s.nextLine(); // 입력 버퍼 비우기 (중요!)
            } catch (MinusException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("원하는 만기일자 입력(개월) : ");
                i.endPeriod = s.nextInt();
                i.input2();
                break;
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요!");
                s.nextLine();
            } catch (Minus2Exception e) {
                System.out.println(e.getMessage());
            }
        }

        List<Product> list = CsvReader.readCsv("Bank_data.csv");

        // 필터링
        List<Product> filtered = new ArrayList<>();
        for (Product p : list) {
            if (p.period <= i.endPeriod) {
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
            double[] interests = InterestCalculator.calculate(i.money, p);
            dataMap.put(p, interests);

            System.out.println(p.bankName + " | " + p.name + " | " + p.baseRate + "%");
        }

        // 그래프 출력
        Chart.drawMultiGraph(dataMap);
    }
}