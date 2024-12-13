# StudentPerformanceAnalyzer

**StudentPerformanceAnalyzer** — это приложение на языке Java, разработанное для анализа данных об успеваемости студентов. Программа обрабатывает файлы Excel, вычисляет статистические показатели, создает визуализации и генерирует подробные отчеты, чтобы помочь преподавателям и администраторам в оценке учебных достижений.

---
# Multilanguage README

[![en](https://img.shields.io/badge/lang-en-red.svg)](./README.en.md)
[![ru](https://img.shields.io/badge/lang-ru-green.svg)](./README.md)
---


## Содержание

1. [Особенности](#особенности)  
2. [Начало работы](#начало-работы)  
   - [Необходимые условия](#необходимые-условия)  
   - [Установка](#установка)  
3. [Использование](#использование)  
   - [Формат входных данных](#формат-входных-данных)  
   - [Запуск приложения](#запуск-приложения)  
4. [Примеры результатов](#примеры-результатов)  
5. [Известные проблемы](#известные-проблемы)  
6. [Как внести вклад](#как-внести-вклад)  
7. [Лицензия](#лицензия)  

---

## Особенности

- Парсинг Excel-файлов для извлечения данных об успеваемости студентов.
- Расчет ключевых статистических показателей:
  - Количество отличников, хорошистов, троечников и неуспевающих.
  - Средние баллы.
- Генерация диаграмм (столбчатых графиков), визуализирующих данные.
- Создание структурированного текстового отчета с результатами анализа.

---

## Начало работы

### Необходимые условия

Для работы с **StudentPerformanceAnalyzer** необходимо:
- Установленный Java Development Kit (JDK) версии 11 или выше.
- Apache Maven (для управления зависимостями).
- Git (опционально, для клонирования репозитория).

### Установка

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/yourusername/StudentPerformanceAnalyzer.git
   cd StudentPerformanceAnalyzer
   ```

2. Скомпилируйте проект с помощью Maven:
   ```bash
   ./gradlew clean build
   ```

3. Убедитесь, что все зависимости успешно загружены.

---

## Использование

### Формат входных данных

Входной Excel-файл должен содержать данные в следующем формате:
- **Столбцы**: Имя студента, Предмет, Оценка.
- **Строки**: Каждая строка представляет оценку студента по конкретному предмету.

Пример входного файла `input.xlsx` находится в папке `resources/` репозитория.

### Запуск приложения

1. Запустите приложение из командной строки:
   ```bash
   java -jar target/StudentPerformanceAnalyzer.jar <path_to_input_file>
   ```

2. Параметры:
   - `<path_to_input_file>`: путь к Excel-файлу с данными.

3. Пример запуска:
   ```bash
   java -jar target/StudentPerformanceAnalyzer.jar resources/example_data.xlsx output/
   ```

4. Результаты:
   - Текстовый отчет: `output/output.txt`.
   - График в формате PNG: `output/chart.png`.

---

## Примеры результатов

После обработки файла `input.xlsx` приложение сгенерирует:
1. Отчет с подсчетом количества отличников, хорошистов, троечников и неуспевающих.
2. Столбчатую диаграмму, показывающую распределение категорий студентов.

Образцы результатов доступны в папке `resources/` репозитория.

---

## Известные проблемы

Приложение успешно прошло тестирование, но возможны следующие проблемы:
- Ошибки при обработке файлов с некорректными или неполными данными.
- Обработка больших объемов данных может занять больше времени или вызвать проблемы с памятью.

Будущие версии программы будут включать улучшения в обработке ошибок и оптимизации.
