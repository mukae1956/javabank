package JavaBank;

import java.awt.Font;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class Chart {

    public static void drawMultiGraph(Map<Product, double[]> dataMap) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<Product, double[]> entry : dataMap.entrySet()) {
            Product p = entry.getKey();
            double[] interests = entry.getValue();

            for (int i = 0; i < interests.length; i++) {
                dataset.addValue(interests[i], p.name, (i + 1) + "개월");
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "상품별 이자 비교",
                "기간",
                "이자",
                dataset
        );

        // 🔥 한글 폰트 설정 추가
        Font font = new Font("Malgun Gothic", Font.PLAIN, 12);

        // 제목
        chart.getTitle().setFont(new Font("Malgun Gothic", Font.BOLD, 16));

        // plot
        CategoryPlot plot = chart.getCategoryPlot();

        // X축
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(font);
        domainAxis.setLabelFont(font);

        // Y축
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickLabelFont(font);
        rangeAxis.setLabelFont(font);

        // 범례
        if (chart.getLegend() != null) {
            chart.getLegend().setItemFont(font);
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("그래프");
            frame.setContentPane(new ChartPanel(chart));
            frame.setSize(900, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}