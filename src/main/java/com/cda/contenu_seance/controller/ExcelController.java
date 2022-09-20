package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.model.Activite;
import com.cda.contenu_seance.model.Seance;
import com.cda.contenu_seance.model.Session;
import com.cda.contenu_seance.service.Excel.ExcelExportXlsxService;
import com.cda.contenu_seance.service.Excel.ExcelReadService;
import com.cda.contenu_seance.service.Excel.ExelService;
import com.cda.contenu_seance.service.Excel.ExportActiviteService;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Validated
@Controller
@RequestMapping(value = "/Coordinateur")
public class ExcelController {

    @Autowired
    ExelService exelService;

    @Autowired
    FicheSuivieService ficheSuivieService;

    @Autowired
    ExcelReadService excelReadService;

    @Autowired
    ReferentielService referentielService;

    @GetMapping(value = {"/export/excel","/export/excel/session/{id}"})
    public void exportToExcel(@PathVariable(required = false) Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String currentDateTime = dateFormatter.format(new Date());


        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Fiches_Suivi_" + currentDateTime + ".xlsx";


        List<Seance> seanceList = ficheSuivieService.getSeanceNonEmpty();
        if(null != id){
             seanceList = ficheSuivieService.getSeanceByIdSessionAttIsEmpty(id);
             Session session = ficheSuivieService.getSession(id);
             String formationBySession = session.getFormation().getTypeFormation();
             DateTimeFormatter unFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

             String dateSession = session.getDateDebut().format(unFormat)+" | "+ session.getDateFin().format(unFormat);
             headerValue = "attachment; filename=Fiches_Suivi_Session_" + formationBySession + dateSession+ ".xlsx";
        }
        response.setHeader(headerKey, headerValue);

        ExcelExportXlsxService excelExportXlsxService = new ExcelExportXlsxService(seanceList);

        excelExportXlsxService.export(response);
    }

    @GetMapping("/exportFormateur")
    public String exportFormateur(RedirectAttributes redirectAttributes) throws IOException, ClassNotFoundException {
        exelService.creatSheetForEntity();
        redirectAttributes.addFlashAttribute("message", "export envoyer");

        return "redirect:/Coordinateur/Formateurs";
    }
    @GetMapping("/export/activitées")
    public void exportToExcel2(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Activite_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Activite>activiteList = referentielService.getActivites();
        ExportActiviteService excelExportService = new ExportActiviteService(activiteList);

        excelExportService.export(response);
    }

    //TODO export date des Seance Competence/Savoir Faire formateur

//-------------------------------------------- Import ---------------------------------------------------



//    @PostMapping("read")
//    public String exelRead(@RequestParam("file") MultipartFile file, @Validated ReacDTO reacDTO) throws IOException {
//
////        ExcelReadService excelReadService = new ExcelReadService();
//
//        Reac reac = new Reac();
//        reac.setNom("test");
//        reac.setLien("bob");
//
//        //excelReadService.generateDtoByExel("C:/Users/pc1/Documents/florian/CDA/Base_de_donnees_Contenu_seance/Exel/"+ file.getOriginalFilename());
//        excelReadService.generateDtoByExel(file.getInputStream(),reac);
//
//        System.out.println(reac);
//        return "redirect:/Coordinateur/Reac";
//    }

}
