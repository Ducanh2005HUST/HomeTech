package hometech.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class XlsxExportUtilTest {
    public static void main(String[] args) {
        // This is just a placeholder for the main method.
        // The actual test cases are run using JUnit.
        // You can run this class as a JUnit test in your IDE or build tool.
        // For example, if using Maven, you can run `mvn test` to execute all tests.
        // If you want to run this specific test, you can use your IDE's test runner.
        XlsxExportUtilTest test = new XlsxExportUtilTest();
        try {
            test.testExportToExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Person {
        String name;
        int age;
        String email;
        boolean active;
        Person(String name, int age, String email, boolean active) {
            this.name = name;
            this.age = age;
            this.email = email;
            this.active = active;
        }
        String getName() { return name; }
        int getAge() { return age; }
        String getEmail() { return email; }
        boolean isActive() { return active; }
    }

    private final String testFile = "C:/Users/ASUS/Downloads/20235822/JAVA/HomeTech/test_people.xlsx";

    @AfterEach
    void cleanup() {
        new File(testFile).delete();
    }

    @Test
    void testExportToExcel() throws IOException {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, "alice@example.com", true));
        people.add(new Person("Bob", 25, "bob@example.com", false));
        String[] headers = {"Name", "Age", "Email", "Active"};

        XlsxExportUtil.exportToExcel(testFile, headers, people, (row, person) -> {
            row.createCell(0).setCellValue(person.getName());
            row.createCell(1).setCellValue(person.getAge());
            row.createCell(2).setCellValue(person.getEmail());
            row.createCell(3).setCellValue(person.isActive());
        });

        // Read back and verify
        try (FileInputStream fis = new FileInputStream(testFile);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Assertions.assertEquals("Name", headerRow.getCell(0).getStringCellValue());
            Assertions.assertEquals("Age", headerRow.getCell(1).getStringCellValue());
            Assertions.assertEquals("Email", headerRow.getCell(2).getStringCellValue());
            Assertions.assertEquals("Active", headerRow.getCell(3).getStringCellValue());

            Row row1 = sheet.getRow(1);
            Assertions.assertEquals("Alice", row1.getCell(0).getStringCellValue());
            Assertions.assertEquals(30, (int)row1.getCell(1).getNumericCellValue());
            Assertions.assertEquals("alice@example.com", row1.getCell(2).getStringCellValue());
            Assertions.assertTrue(row1.getCell(3).getBooleanCellValue());

            Row row2 = sheet.getRow(2);
            Assertions.assertEquals("Bob", row2.getCell(0).getStringCellValue());
            Assertions.assertEquals(25, (int)row2.getCell(1).getNumericCellValue());
            Assertions.assertEquals("bob@example.com", row2.getCell(2).getStringCellValue());
            Assertions.assertFalse(row2.getCell(3).getBooleanCellValue());
        }
    }
}
