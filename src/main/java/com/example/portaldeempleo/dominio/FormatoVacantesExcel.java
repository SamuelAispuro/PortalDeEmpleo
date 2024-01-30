package com.example.portaldeempleo.dominio;

import com.example.portaldeempleo.entities.Empleador;
import com.example.portaldeempleo.entities.Vacante;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FormatoVacantesExcel {

    public void generateExcelReportVacantes(List<Vacante> vacantes, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte vacantes por estado");

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID Vacante");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Estado");

            // Llenar los datos
            int rowNum = 1;
            for (Vacante vacante : vacantes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(vacante.getId_vacante());
                row.createCell(1).setCellValue(vacante.getNombreVacante()); // Ajusta esto según tu estructura de Usuario
                row.createCell(2).setCellValue(vacante.getEstado().getNombreEstado());
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
