package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private static final Logger logger = LogManager.getLogger(FileProcessor.class);

    public static class Student {
        private String lastName;
        private String firstName;
        private int grade;

        public Student(String lastName, String firstName, String middleName, int grade) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.grade = grade;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public int getGrade() {
            return grade;
        }
    }

    public List<Student> parseExcel(String filePath) throws IOException {
        logger.info("Начало обработки файла: {}", filePath);
        List<Student> students = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            logger.info("Файл успешно открыт: {}", filePath);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            logger.info("Обнаружено {} строк (включая заголовки)", rowCount);

            for (int i = 1; i < rowCount; i++) { // Пропускаем заголовок
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell nameCell = row.getCell(0);
                Cell gradeCell = row.getCell(1);

                if (nameCell == null || gradeCell == null) continue;

                String[] nameParts = nameCell.getStringCellValue().split(" ");
                int grade = (int) gradeCell.getNumericCellValue();

                String lastName = nameParts.length > 0 ? nameParts[0] : "";
                String firstName = nameParts.length > 1 ? nameParts[1] : "";
                String middleName = nameParts.length > 2 ? nameParts[2] : "";

                students.add(new Student(lastName, firstName, middleName, grade));
            }
        } catch (IOException e) {
            logger.error("Ошибка чтения файла: {}", filePath, e);
            throw e;
        }

        logger.info("Обработка файла завершена. Обработано {} студентов.", students.size());
        return students;
    }
}
