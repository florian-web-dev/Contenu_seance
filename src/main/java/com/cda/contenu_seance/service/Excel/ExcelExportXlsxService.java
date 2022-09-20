package com.cda.contenu_seance.service.Excel;

import com.cda.contenu_seance.model.Seance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelExportXlsxService {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Seance> seanceList;
    private CellStyle headerStyle;

    public ExcelExportXlsxService(List<Seance> seanceList) {
        this.seanceList = seanceList;
        workbook = new XSSFWorkbook();
        //Création d'un style pour les entetes
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        headerStyle = style;
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Fiche de suivi");

        Row row = sheet.createRow(0);
        createCell(row, 0, "Date Du Jour", headerStyle);
        createCell(row, 1, "Durée", headerStyle);
        createCell(row, 2, "Support", headerStyle);
        createCell(row, 3, "Déroulement", headerStyle);
        createCell(row, 4, "Objectif pédagogique", headerStyle);
        createCell(row, 5, "Méthode envisagée", headerStyle);
        createCell(row, 6, "Formateurs", headerStyle);
        createCell(row, 7, "Sessions durée total", headerStyle);
        createCell(row, 8, "Évaluations", headerStyle);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        Cell cell;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);


        for (Seance unSeance : seanceList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

//            createCell(row, columnCount++, unSeance.getDateDuJour(), style);
            LocalDate dateSeance = unSeance.getDateDuJour();
            DateTimeFormatter unFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            createCell(row, columnCount++, dateSeance.format(unFormat), style);
            createCell(row, columnCount++, unSeance.getDurer(), style);
            createCell(row, columnCount++, unSeance.getSupportUse(), style);
            createCell(row, columnCount++, unSeance.getDeroulement(), style);
            createCell(row, columnCount++, unSeance.getObjectifPeda(), style);
            createCell(row, columnCount++, unSeance.getMethodeEnvisagee(), style);
            createCell(row, columnCount++, unSeance.getFormateur().concatNomPrenom(), style);
            createCell(row, columnCount++, unSeance.getSession().getDurerTotal(), style);
            createCell(row, columnCount++, unSeance.getEvaluation(), style);

        }

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
