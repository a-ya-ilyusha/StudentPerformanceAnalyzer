package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ChartGenerator {
    private static final Logger logger = LogManager.getLogger(ChartGenerator.class);

    public void generateChart(DataAnalyzer.GroupStatistics stats, String filePath) {
        logger.info("Начало генерации графика. Путь к сохранению: {}", filePath);

        // Создание набора данных для графика
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Добавляем данные для столбчатой диаграммы
        dataset.addValue(stats.excellentCount, "Студенты", "Отличники");
        dataset.addValue(stats.goodCount, "Студенты", "Хорошисты");
        dataset.addValue(stats.satisfactoryCount, "Студенты", "Троечники");
        dataset.addValue(stats.failedCount, "Студенты", "Не допущены");

        logger.debug("Данные для графика успешно подготовлены: "
                        + "Отличники - {}, Хорошисты - {}, Троечники - {}, Не допущены - {}",
                stats.excellentCount, stats.goodCount, stats.satisfactoryCount, stats.failedCount);

        // Создание диаграммы
        JFreeChart barChart = ChartFactory.createBarChart(
                "Статистика успеваемости студентов",   // Заголовок графика
                "Категория",                           // Подпись оси X
                "Количество студентов",                // Подпись оси Y
                dataset,                               // Данные
                PlotOrientation.VERTICAL,              // Ориентация (вертикальная)
                true,                                  // Легенда
                true,                                  // Интерактивность
                false                                  // URL
        );
        logger.info("График успешно создан.");

        // Сохранение графика в файл
        try {
            File chartFile = new File(filePath);
            org.jfree.chart.ChartUtils.saveChartAsPNG(chartFile, barChart, 800, 600);
            logger.info("График успешно сохранен в файл: {}", filePath);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении графика: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Произошла неожиданная ошибка при создании графика: {}", e.getMessage());
        }
    }
}
