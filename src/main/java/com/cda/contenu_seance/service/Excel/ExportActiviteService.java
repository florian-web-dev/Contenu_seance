package com.cda.contenu_seance.service.Excel;

import com.cda.contenu_seance.model.Activite;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportActiviteService {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

//    private List <Competence> competenceList; + savoirfaire + seance et eval
    private List<Activite> activiteList;
    private CellStyle headerStyle;

    public ExportActiviteService(List<Activite> activiteList) {
//        this.seanceList = seanceList;
        this.activiteList = activiteList;
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
        sheet = workbook.createSheet("Activite");

        Row row = sheet.createRow(0);
        createCell(row, 0, "Activites Types", headerStyle);
        createCell(row, 1, "Ordre", headerStyle);
        createCell(row, 2, "Nom reac", headerStyle);

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

//        for (Seance unSeance : seanceList) {
//            Row row = sheet.createRow(rowCount++);
//            int columnCount = 0;
//
////            createCell(row, columnCount++, unSeance.getDateDuJour(), style);
//            LocalDate dateSeance = unSeance.getDateDuJour();
//            DateTimeFormatter unFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//            createCell(row, columnCount++, dateSeance.format(unFormat), style);
//            createCell(row, columnCount++, unSeance.getDurer(), style);
//            createCell(row, columnCount++, unSeance.getSupportUse(), style);
//            createCell(row, columnCount++, unSeance.getDeroulement(), style);
//            createCell(row, columnCount++, unSeance.getObjectifPeda(), style);
//            createCell(row, columnCount++, unSeance.getMethodeEnvisagee(), style);
//            createCell(row, columnCount++, unSeance.getFormateur().getNom(), style);
//            createCell(row, columnCount++, unSeance.getSession().getDurerTotal(), style);
//            createCell(row, columnCount++, unSeance.getEvaluation().getModalite(), style);
//
//        }
        for (Activite uneActivite : activiteList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

//            createCell(row, columnCount++, unSeance.getDateDuJour(), style);
//            LocalDate dateSeance = unSeance.getDateDuJour();
//            DateTimeFormatter unFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

//            createCell(row, columnCount++, dateSeance.format(unFormat), style);
            createCell(row, columnCount++, uneActivite.getActivitesTypes(), style);
            createCell(row, columnCount++, uneActivite.getNumOdre(), style);
            createCell(row, columnCount++, uneActivite.getReac().getNom(), style);

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
