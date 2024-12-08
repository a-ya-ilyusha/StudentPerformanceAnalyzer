package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.io.File;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            logger.error("Ошибка: укажите путь к входному файлу Excel в параметрах запуска.");
            return;
        }

        String inputFilePath = args[0]; // Путь к входному файлу из параметров запуска
        File inputFile = new File(inputFilePath);

        // Проверяем, существует ли файл
        if (!inputFile.exists() || !inputFile.isFile()) {
            logger.error("Ошибка: указанный файл не найден или это не файл. Путь: {}", inputFilePath);
            return;
        }

        logger.info("Используется файл: {}", inputFilePath);
        String outputFilePath = "output.xlsx";
        String chartFilePath = "chart.png";

        FileProcessor processor = new FileProcessor();
        DataAnalyzer analyzer = new DataAnalyzer();
        ReportGenerator generator = new ReportGenerator();

        try {
            List<FileProcessor.Student> students = processor.parseExcel(inputFilePath);
            DataAnalyzer.GroupStatistics stats = analyzer.analyze(students);
            generator.generateReport(outputFilePath, stats);

            ChartGenerator chartGenerator = new ChartGenerator();
            chartGenerator.generateChart(stats, chartFilePath);

            logger.info("Программа успешно завершила выполнение.");
        } catch (Exception e) {
            logger.error("Ошибка выполнения программы: {}", e.getMessage(), e);
        }
    }
}
