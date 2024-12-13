# StudentPerformanceAnalyzer

**StudentPerformanceAnalyzer** is a Java-based application designed to analyze student performance data from Excel files. The program computes statistical metrics, generates visualizations, and produces detailed reports to help educators and administrators understand and improve student outcomes.

---

## Table of Contents

1. [Features](#features)  
2. [Getting Started](#getting-started)  
   - [Prerequisites](#prerequisites)  
   - [Installation](#installation)  
3. [Usage](#usage)  
   - [Input Format](#input-format)  
   - [Running the Application](#running-the-application)  
4. [Example Outputs](#example-outputs)  
5. [Known Issues](#known-issues)  
6. [Contributing](#contributing)  
7. [License](#license)  

---

## Features

- Parses Excel files to extract student performance data.
- Calculates key statistics such as:
  - Number of excellent, good, satisfactory, and failing students.
  - Average performance scores.
- Generates bar charts visualizing student performance categories.
- Outputs results in a structured report file.

---

## Getting Started

### Prerequisites

To run **StudentPerformanceAnalyzer**, you need the following installed on your system:
- Java Development Kit (JDK) 17 or higher
- Apache Maven (for dependency management)
- Git (optional, for cloning the repository)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/StudentPerformanceAnalyzer.git
   cd StudentPerformanceAnalyzer
   ```

2. Build the project using Maven:
   ```bash
   gradle clean build
   ```

3. Ensure all dependencies are resolved successfully.

---

## Usage

### Input Format

The input Excel file should contain student performance data structured as follows:
- **Columns**: Student Name, Subject, Score
- **Rows**: Each row represents a student's score in a subject.

An example file, `example_data.xlsx`, is provided in the repository under the `resources/` folder.

### Running the Application

1. Run the application from the command line:
   ```bash
   java -jar target/StudentPerformanceAnalyzer.jar <path_to_input_file>
   ```

2. Parameters:
   - `<path_to_input_file>`: Path to the Excel file containing student data.
   - `<output_directory>`: Directory where the generated report and chart will be saved.

3. Example:
   ```bash
   java -jar target/StudentPerformanceAnalyzer.jar resources/input.xlsx
   ```

4. Outputs:
   - A statistical summary in `output/output.txt`.
   - A bar chart visualization saved as `output/chart.png`.

---

## Example Outputs

After running the application with `input.xlsx`, you can expect:
1. A report detailing the count of excellent, good, satisfactory, and failing students.
2. A bar chart showing the distribution of performance categories.

Sample outputs are available in the `resources/` directory for reference.

---

## Known Issues

While the application has been tested extensively, the following issues may occur:
- Input files with missing or improperly formatted data may cause errors. Ensure input files are validated before processing.
- Large datasets might take longer to process or cause memory-related issues.

Future versions aim to address these concerns with better error handling and optimized performance.
