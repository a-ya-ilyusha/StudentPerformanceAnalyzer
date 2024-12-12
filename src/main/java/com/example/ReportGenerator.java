package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    private static final Logger logger = LogManager.getLogger(ReportGenerator.class);

    public void generateReport(String outputPath, DataAnalyzer.GroupStatistics stats) throws IOException {
        logger.info("Начало генерации отчета. Путь к файлу: {}", outputPath);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Статистика");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Категория");
        header.createCell(1).setCellValue("Количество");
        header.createCell(3).setCellValue("Отличники");
        header.createCell(4).setCellValue("Хорошисты");
        header.createCell(5).setCellValue("Троечники");
        header.createCell(6).setCellValue("Не допущены");

        logger.debug("Создан заголовок таблицы.");

        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("Отличники");
        row1.createCell(1).setCellValue(stats.excellentCount);

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Хорошисты");
        row2.createCell(1).setCellValue(stats.goodCount);

        Row row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("Троечники");
        row3.createCell(1).setCellValue(stats.satisfactoryCount);

        Row row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("Не допущены");
        row4.createCell(1).setCellValue(stats.failedCount);

        logger.debug("Заполнены основные категории статистики.");

        Row row6 = sheet.createRow(6);
        row6.createCell(0).setCellValue("Средний балл");
        row6.createCell(1).setCellValue(stats.averageGrade);

        Row row7 = sheet.createRow(7);
        row7.createCell(0).setCellValue("Максимальный балл");
        row7.createCell(1).setCellValue(stats.maxGrade);

        logger.debug("Заполнены средний и максимальный баллы.");

        fillColumn(sheet, stats.excellents, 3, "Отличники");
        fillColumn(sheet, stats.goods, 4, "Хорошисты");
        fillColumn(sheet, stats.satisfactories, 5, "Троечники");
        fillColumn(sheet, stats.failed, 6, "Не допущены");

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            workbook.write(fos);
            logger.info("Отчет успешно записан в файл: {}", outputPath);
        }
        catch (IOException e) {
            logger.error("Ошибка при записи отчета в файл: {}", e.getMessage());
            throw e;
        }
        finally {
            workbook.close();
            logger.debug("Рабочая книга Excel закрыта.");
        }
    }

    private void fillColumn(Sheet sheet, List<FileProcessor.Student> students, int columnIndex, String categoryName) {
        logger.info("Заполнение колонки для категории '{}'. Количество студентов: {}", categoryName, students.size());

        int rowIndex = 1; // Начало с первой строки данных
        for (FileProcessor.Student student : students) {
            Row row = sheet.getRow(rowIndex++) == null ? sheet.createRow(rowIndex - 1) : sheet.getRow(rowIndex - 1);
            row.createCell(columnIndex).setCellValue(student.getLastName() + " " + student.getFirstName());
            logger.debug("Добавлен студент '{}' в колонку '{}'.", student.getLastName() + " " + student.getFirstName(), categoryName);
        }
    }
}


