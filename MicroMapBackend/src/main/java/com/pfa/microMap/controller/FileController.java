package com.pfa.microMap.controller;

import com.pfa.microMap.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
  @Autowired
  private FileService fileService;

  @PostMapping("/insert/calls")
  public void insertExcelFileCalls(@RequestPart("file") MultipartFile file) throws IOException {

    fileService.insertExcelFileCalls(file.getInputStream());


  }

  @PostMapping("/insert/nodes")
  public void insertExcelFileNodes(@RequestPart("file") MultipartFile file) throws IOException {

    fileService.insertExcelFileNodes(file.getInputStream());


  }

  @GetMapping(value = "/download/call", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity<?> exportCallsToExcel(HttpServletResponse response) throws IOException {
    String fileName = "calls-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".xlsx";
    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    // Get the Excel file as a byte array
    byte[] excelBytes = this.fileService.exportCallsToExcel();

    // Write the byte array to the response output stream
    response.getOutputStream().write(excelBytes);
    response.getOutputStream().flush();

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/download/node", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity<?> exportNodesToExcel(HttpServletResponse response) throws IOException {
    String fileName = "nodes-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".xlsx";
    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    // Get the Excel file as a byte array
    byte[] excelBytes = this.fileService.exportNodesToExcel();

    // Write the byte array to the response output stream
    response.getOutputStream().write(excelBytes);
    response.getOutputStream().flush();

    return ResponseEntity.ok().build();
  }
}
