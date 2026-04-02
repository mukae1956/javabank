package Project.Weekend;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("그래프");
                frame.setContentPane(new ChartPanel(chart));
                frame.setSize(900, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            });
        }
    }

