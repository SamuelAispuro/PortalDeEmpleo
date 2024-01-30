package com.example.portaldeempleo.dominio;

import com.example.portaldeempleo.entities.Empleador;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class FormatoExcel {

    public void generateExcelReport(List<Empleador> empleadores, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte Empleadores");

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID Empleador");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Vacantes Publicadas");

            // Llenar los datos
            int rowNum = 1;
            for (Empleador empleador : empleadores) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(empleador.getId_empleador());
                row.createCell(1).setCellValue(empleador.getUsuario().getNombre()); // Ajusta esto según tu estructura de Usuario
                row.createCell(2).setCellValue(empleador.getVacantes().size());
            }

            // Guardar el archivo
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
