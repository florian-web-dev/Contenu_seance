package com.cda.contenu_seance.service.Excel;

import com.cda.contenu_seance.model.*;
import com.cda.contenu_seance.repositories.CompetenceRepository;
import com.cda.contenu_seance.repositories.FormationRepository;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.ReferentielService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ExcelReadService {

//    HSSFWorkbook hssfWorkbook;
//    HSSFSheet hssfSheet;
//    HSSFRow hRow;
//    HSSFCell hCell;
//
//    XSSFWorkbook xssfWorkbook;
//    XSSFSheet xssfSheet;
//    XSSFRow xRow;
//    XSSFCell xCell;
//
//    ExcelDTOReferentiel excelDTOReferentiel;

    FicheSuivieService ficheSuivieService;

    FormationRepository formationRepository;

    ReferentielService referentielService;

    CompetenceRepository competenceRepository;

    @Autowired
    public ExcelReadService(FicheSuivieService ficheSuivieService, FormationRepository formationRepository, ReferentielService referentielService, CompetenceRepository competenceRepository) {
        this.ficheSuivieService = ficheSuivieService;
        this.formationRepository = formationRepository;
        this.referentielService = referentielService;
        this.competenceRepository = competenceRepository;
    }


//    /**
//     * format .xls
//     * @param hssfWorkbook
//     * @param hssfSheet
//     * @param hRow
//     * @param hCell
//     */
//    public ExcelReadService(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, HSSFRow hRow, HSSFCell hCell) {
//        this.hssfWorkbook = hssfWorkbook;
//        this.hssfSheet = hssfSheet;
//        this.hRow = hRow;
//        this.hCell = hCell;
//
//    }
//    /**
//     * format .xlsx
//     * @param xssfWorkbook
//     * @param xssfSheet
//     * @param xRow
//     * @param xCell
//     */
//    public ExcelReadService(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet, XSSFRow xRow, XSSFCell xCell) {
//        this.xssfWorkbook = xssfWorkbook;
//        this.xssfSheet = xssfSheet;
//        this.xRow = xRow;
//        this.xCell = xCell;
//    }

    public void generateDtoByExel(InputStream fileLocation, Reac reac) throws IOException {

        InputStream fichier = fileLocation;

        XSSFWorkbook workbook = new XSSFWorkbook(fichier);
        //une fois le ficher recupéré je vais chercher la feuille a l'index 0
        XSSFSheet sheet = workbook.getSheetAt(0);


        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//        FormulaEvaluator formulaEvaluator2 =workbook.createEvaluationWorkbook();

//        ExcelDTOReferentiel excelDTOReferentiel = new ExcelDTOReferentiel();

        int j = 0;
        int i = 0;

//        Column.evaluate(formulaEvaluator,i,0);
        Reac exReac = null;
        Activite exactivite = null;
        Competence exCompetence = null;


        Formation formation = new Formation();
        Session session = new Session();
        Centre centre = new Centre();
        Centre exCentre = null;


        for (Row ligne : sheet) {
            Activite activite = new Activite();
            Competence competence = new Competence();

            for (Cell cell : ligne) {

                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:
//                    System.out.print(cell.getNumericCellValue() + "\t\t" + "|| num || ");
                        break;
                    case STRING:
//                    System.out.print(cell.getStringCellValue() + "\t" + "|| String || ");

//              ------------------------------------------------------------------------------------------------------------
//                   ---------------------------------------- Centre ------------------------------------------------------------
//              ------------------------------------------------------------------------------------------------------------
//
                        if (cell.getStringCellValue().contains("Centre de formation :")) {
                            String[] centretab = cell.getStringCellValue().split(" : ");

                            centre.setNom(centretab[1]);
                            centre.setCodesPostal("vide");
                            centre.setVille("vide");
                            centre.setAdresse("vide");
                            ficheSuivieService.saveEntityCentreExcel(centre);
                            exCentre = centre;
//                            System.out.println("Centre :" + centre.getNom());
//                            System.out.println("Centre ID :" + centre.getId());
                        }
//              ------------------------------------------------------------------------------------------------------------
//                 ----------------------------------------- Formation -------------------------------------------------------------------
//              -----------------------------------------------------------------------------------------------------------
                        if (cell.getStringCellValue().contains("SUIVI PEDAGOGIQUE DE LA FORMATION")) {
                            String[] formationStr = cell.getStringCellValue().split(" : ");

                            formation.setTypeFormation(formationStr[1]);
                            ficheSuivieService.saveEntityFormationExel(formation);
                            reac.setFormation(formation);
//                            Reac reac1 =  referentielService.saveReacEntityExcel(reac);
//                            exReac = reac1;

                        }
//              ------------------------------------------------------------------------------------------------------------
//                 ----------------------------------------- Session -------------------------------------------------------------------
//              ------------------------------------------------------------------------------------------------------------
                        if (cell.getStringCellValue().contains("Dates : ")) {
                            String[] dateSession = cell.getStringCellValue().split(" : ");
                            String[] date = dateSession[1].split(" ");//tabSize4

//                            System.out.println(date[1] + " | DateSession | " + date[3]);
                            String date1 = date[0];
                            String date2 = date[2];

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                            LocalDate dateDebut = LocalDate.parse(date1, formatter);
                            LocalDate dateFin = LocalDate.parse(date2, formatter);

                            session.setDateDebut(dateDebut);
                            session.setDateFin(dateFin);

                            session.setFormation(formation);
//                            session.setCentre(centre);

                            ficheSuivieService.saveSessionEntityExcel(session);
                            System.out.println("exCentre : " + exCentre);

                        }


//              ------------------------------------------------------------------------------------------------------------
//                   ----------------------------------------- Activite -----------------------------------------------------------
//              -----------------------------------------------------------------------------------------------------------
                        if (cell.getStringCellValue().contains("CCP")) {
                            String[] ccp1 = cell.getStringCellValue().split(" : ");
                            i++;

                            referentielService.saveReacEntityExcel(reac);

                            activite.setActivitesTypes(ccp1[1]);
                            activite.setNumOdre(i);
                            activite.setReac(reac);
                            System.out.println(activite);
                            activite = referentielService.creatActiviteEntityExcel(activite);

                            exactivite = activite;

                        }

//              ------------------------------------------------------------------------------------------------------------
//                   ----------------------------------------- COMPETENCE -----------------------------------------------------------
//              -----------------------------------------------------------------------------------------------------------
                        if (cell.getStringCellValue().contains("--") && cell.getStringCellValue().contains("COMPETENCE")) {
                            String[] tabCompetance = cell.getStringCellValue().split("--");
                            String[] compTab = tabCompetance[0].split(" : ");
                            String competenceStr = compTab[1];

                            j++;
                            competence.setNom(competenceStr);
                            competence.setNumOdre(j);
                            competence.setActivite(exactivite);
                            referentielService.createCompetenceEntity(competence);
                            exCompetence = competence;

                        }
//              ------------------------------------------------------------------------------------------------------------
//                   ----------------------------------------- SavoirFaire -----------------------------------------------------------
//              -----------------------------------------------------------------------------------------------------------

                        if (cell.getStringCellValue().contains("COMPETENCE") && cell.getStringCellValue().contains("- ")) {

                            if (exCompetence == null) {
                                System.out.println("exCompetence : Null" + exCompetence);
                            }
                            String[] competenceSkills = cell.getStringCellValue().split("- ");
                            String[] compTab = competenceSkills[0].split(" : ");
                            String skills = competenceSkills[1];

                            SavoirFaire savoirFaire = new SavoirFaire();
                            savoirFaire.setNom(skills);

                            savoirFaire.setCompetence(exCompetence);

                            referentielService.saveSavoirFaireEntity(savoirFaire);

                        }


//                        CellReference ref = new CellReference("C10");
//                        int column = cell.getColumnIndex();
//                        int row = cell.getRowIndex();
//                        if (column == 2 && row == 9){
//
//                            String sessionTHeur = cell.getStringCellValue();
//                            System.out.println("sessionTHeur : " + sessionTHeur);
//                        }
//                        if (cell.getStringCellValue().contains("H")){
//                            String sessionTHeur1 = cell.getStringCellValue();
//                            System.out.println("sessionTHeur1 : " + sessionTHeur1);
//                        }

//                        System.out.println("column : " + column);
//                        System.out.println("row : " + row);


                        break;
                }
            }
        }


    }

//
//    FileInputStream fichier = new FileInputStream(new File(fileLocation));
//    //créer une instance workbook qui fait référence au fichier xlsx
//    XSSFWorkbook wb = new XSSFWorkbook(fichier);
//    XSSFSheet sheet = wb.getSheetAt(0);
//
//    FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
//
//    for (Row ligne : sheet) {//parcourir les lignes
//
//
//        for (Cell cell : ligne) {//parcourir les colonnes
//            //évaluer le type de la cellule
//            switch (formulaEvaluator.evaluateInCell(cell).getCellType())
//            {
//                case NUMERIC:
//                    System.out.print(cell.getNumericCellValue() + "\t\t");
//                    break;
//                case STRING:
//                    System.out.print(cell.getStringCellValue() + "\t");
//                    break;
//            }
//        }
//        System.out.println();
//    }
//
//    public ExcelReadService() throws IOException {
//    }
}
