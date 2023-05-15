package com.pfa.microMap.service;

import com.pfa.microMap.model.Call;
import com.pfa.microMap.model.MyNode;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
  @Autowired
  CallService callService;

  @Autowired
  NodeService nodeService;

  /**
   * Insert Calls from an Excel file
   *
   * @param inputStream
   * @throws IOException
   */
  public void insertExcelFileCalls(InputStream inputStream) throws IOException {
    Workbook workbook = new XSSFWorkbook(inputStream);
    Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
    // Iterate through each row of the sheet
    for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);

      // Read the values from each cell by index
      String issuer = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null;
      String target = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null;
      String type = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null;
      String topic = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null;
      String eventProduced = row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null;
      String api = row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null;
      String description = row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null;

      // Create a new Relationship object and save it to the database
      this.callService.addCall(issuer, target, type, topic, eventProduced, api, description);
    }
  }

  public void insertExcelFileNodes(InputStream inputStream) throws IOException {
    Workbook workbook = new XSSFWorkbook(inputStream);
    Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
    // Iterate through each row of the sheet
    for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);

      // Read the values from each cell by index
      String name = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null;
      String type = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null;


      // Create a new Relationship object and save it to the database
      nodeService.add(name, type);
    }
  }

  public byte[] exportCallsToExcel() throws IOException {
    // Create a new workbook
    List<Call> calls = this.callService.findAllCalls();
    Workbook workbook = new XSSFWorkbook();

    // Create a new sheet
    Sheet sheet = workbook.createSheet("Calls");

    // Create header row
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Initiator/Producer");
    headerRow.createCell(1).setCellValue("Target/Consumer");
    headerRow.createCell(2).setCellValue("Type");
    headerRow.createCell(3).setCellValue("Topic");
    headerRow.createCell(4).setCellValue("Event Produced");
    headerRow.createCell(5).setCellValue("API");
    headerRow.createCell(6).setCellValue("Description");

    // Populate the sheet with data from the list of calls
    int rowNum = 1;
    for (Call call : calls) {
      Row row = sheet.createRow(rowNum++);
      row.createCell(0).setCellValue(call.getIssuer());
      row.createCell(1).setCellValue(call.getTarget());
      row.createCell(2).setCellValue(call.getType());
      row.createCell(3).setCellValue(call.getTopic());
      row.createCell(4).setCellValue(call.getEventProduced());
      row.createCell(5).setCellValue(call.getApi());
      row.createCell(6).setCellValue(call.getDescription());
    }

    // Write the workbook to a ByteArrayOutputStream
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    workbook.write(out);
    byte[] data = out.toByteArray();

    // Set the content type and headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentDispositionFormData("attachment", "calls.xlsx");
    headers.setContentLength(data.length);

    // Return a ResponseEntity with the Excel file contents
    return data;
  }

  public byte[] exportNodesToExcel() throws IOException {
    // Create a new workbook
    List<MyNode> nodes = this.nodeService.getAll();
    Workbook workbook = new XSSFWorkbook();

    // Create a new sheet
    Sheet sheet = workbook.createSheet("Calls");

    // Create header row
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Name");
    headerRow.createCell(1).setCellValue("Type");


    // Populate the sheet with data from the list of calls
    int rowNum = 1;
    for (MyNode node : nodes) {
      Row row = sheet.createRow(rowNum++);
      row.createCell(0).setCellValue(node.getName());
      row.createCell(1).setCellValue(node.getType());

    }

    // Write the workbook to a ByteArrayOutputStream
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    workbook.write(out);
    byte[] data = out.toByteArray();

    // Set the content type and headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentDispositionFormData("attachment", "calls.xlsx");
    headers.setContentLength(data.length);

    // Return the Excel file contents
    return data;
  }
}








