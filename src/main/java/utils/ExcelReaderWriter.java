package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExcelReaderWriter {

    public static void readFromXlsx() throws IOException {
        FileInputStream file = new FileInputStream(new File("C:\\Users\\Artem_Berdnik\\IdeaProjects\\PerformanceMeasurements\\src\\Main\\resources\\testFile.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        for (Row row : sheet) {
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                }
            }
            System.out.println("");
        }
        file.close();
    }


    public static void writeToXlsx(Object[] o) throws IOException {

        File myFile = new File("C:\\Users\\Artem_Berdnik\\IdeaProjects\\PerformanceMeasurements\\src\\Main\\resources\\testFile.xlsx");

        FileInputStream fis = new FileInputStream(myFile);

        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        int rows = mySheet.getPhysicalNumberOfRows();

        Map<String, Object[]> data = new TreeMap<>();

        data.put("1", o);

//        // Set to Iterate and add rows into XLS file
//        Set<String> newRows = data.keySet();

        // get the last row number to append new data
        int rownum = mySheet.getLastRowNum();

            // Creating a new Row in existing XLSX sheet
            Row row = mySheet.createRow(rownum+1);
            Object[] objArr = data.get("1");
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }


        // open an OutputStream to save written data into XLSX file
        FileOutputStream os = new FileOutputStream(myFile);
        myWorkBook.write(os);
        System.out.println("Writing on XLSX file Finished ...");
    }
}
