package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class DataAnalyzer {
    private static final Logger logger = LogManager.getLogger(DataAnalyzer.class);

    public static class GroupStatistics {
        public int excellentCount;
        public int goodCount;
        public int satisfactoryCount;
        public int failedCount;
        public double averageGrade;
        public int maxGrade;

        public List<FileProcessor.Student> excellents = new ArrayList<>();
        public List<FileProcessor.Student> goods = new ArrayList<>();
        public List<FileProcessor.Student> satisfactories = new ArrayList<>();
        public List<FileProcessor.Student> failed = new ArrayList<>();
    }

    public GroupStatistics analyze(List<FileProcessor.Student> students) {
        logger.info("Начало анализа данных группы. Количество студентов: {}", students.size());
        GroupStatistics stats = new GroupStatistics();
        for (FileProcessor.Student student : students) {
            if (student.getGrade() == 5) {
                stats.excellentCount++;
                stats.excellents.add(student);
                logger.debug("Студент '{}' получил '5' (отличник).", student.getLastName() + " " + student.getFirstName());
            } else if (student.getGrade() == 4) {
                stats.goodCount++;
                stats.goods.add(student);
                logger.debug("Студент '{}' получил '4' (хорошист).", student.getLastName() + " " + student.getFirstName());
            } else if (student.getGrade() == 3) {
                stats.satisfactoryCount++;
                stats.satisfactories.add(student);
                logger.debug("Студент '{}' получил '3' (удовлетворительно).", student.getLastName() + " " + student.getFirstName());
            } else {
                stats.failedCount++;
                stats.failed.add(student);
                logger.debug("Студент '{}' не допущен к экзамену (оценка ниже 3).", student.getLastName() + " " + student.getFirstName());
            }
        }
        stats.averageGrade = students.stream()
                .mapToInt(FileProcessor.Student::getGrade)
                .average()
                .orElse(0);

        stats.maxGrade = students.stream()
                .mapToInt(FileProcessor.Student::getGrade)
                .max()
                .orElse(0);

        logger.info("Анализ завершён. Итоги:");
        logger.info("Отличников: {}", stats.excellentCount);
        logger.info("Хорошистов: {}", stats.goodCount);
        logger.info("Троечников: {}", stats.satisfactoryCount);
        logger.info("Не допущено: {}", stats.failedCount);
        logger.info("Средний балл группы: {}", stats.averageGrade);
        logger.info("Максимальная оценка: {}", stats.maxGrade);

        return stats;
    }
}
