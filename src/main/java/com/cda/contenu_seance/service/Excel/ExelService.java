package com.cda.contenu_seance.service.Excel;

import com.cda.contenu_seance.model.Formateur;
import com.cda.contenu_seance.service.IntervenantService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExelService {

    @Autowired
    IntervenantService intervenantService;

//    @Autowired
//    FormateurRepository formateurRepository;

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void creatSheetForEntity() throws ClassNotFoundException, IOException {


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Formateurs sheet");

        List<Formateur> formateurs = intervenantService.getFormateurs();

        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        //header of tab
        Class<?> clazz = Class.forName("com.cda.contenu_seance.model.Intervenant");
        Field[] fieldClass = clazz.getFields();

        int iter = 0;
//        for (int i = 0; i < fieldClass.length; i++) {
//        }
            for (Field unAttribut : fieldClass) {
                cell = row.createCell(iter++, CellType.STRING);
                cell.setCellValue(unAttribut.getName());
                cell.setCellStyle(style);
            }


//        cell = row.createCell(1, CellType.STRING);
//        cell.setCellValue("EmpNo");
//        cell.setCellStyle(style);
//----------------------------------------------------------------
        Stream<Method> champMethod = Arrays.stream(clazz.getDeclaredMethods())
                .filter(bob->bob.getName()
                        .contains(new StringBuffer("get")));
        List<Method> methodList = champMethod.collect(Collectors.toList());


        // Data

        for (Formateur unFormateur : formateurs) {
            rownum++;
            row = sheet.createRow(rownum);
//            int iter2=0;
//            for (Method uneMethode:methodList) {
////                String bob = unFormateur+"."+ uneMethode.getName()+"()";
//                cell = row.createCell(iter2++, CellType.STRING);
////                cell.setCellValue(bob);
//                cell.setCellValue(uneMethode.getName());
//            }
            // ID (A)
//            cell = row.createCell(0, CellType.NUMERIC);
//            cell.setCellValue(unFormateur.getId());
            // NOM (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(unFormateur.getNom());
//             Prenom (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(unFormateur.getPrenom());
            // Email (C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(unFormateur.getEmail());
            // Adresse (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(unFormateur.getAdresse());
            // Salary (E)
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(unFormateur.getVille());
            // Code postal (F)
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(unFormateur.getCodePostal());
            // Tel (G)
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(unFormateur.getTel());


//            // Bonus (G)
//            String formula = "0.1*C" + (rownum + 1) + "*D" + (rownum + 1);
//            cell = row.createCell(4, CellType.FORMULA);
//            cell.setCellFormula(formula);
        }
        File file = new File("C:/Users/pc1/Documents/florian/CDA/Base_de_donnees_Contenu_seance/demo/Formateurs.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        outFile.close();
        System.out.println("Created file: " + file.getAbsolutePath());

    }
}
