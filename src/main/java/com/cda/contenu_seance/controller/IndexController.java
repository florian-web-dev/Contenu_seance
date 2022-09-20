package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.model.Formateur;
import com.cda.contenu_seance.service.Excel.ExcelExportXlsxService;
import com.cda.contenu_seance.service.Excel.ExelService;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.IntervenantService;
import com.cda.contenu_seance.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@Controller
public class IndexController {

    @Autowired
    public ExelService exelService;

    @Autowired
    FicheSuivieService ficheSuivieService;

    @Autowired
    ExcelExportXlsxService excelExportXlsxService;

    @Autowired
    ReferentielService referentielService;

//    IntervenantRepository intervenantRepository;

    @Autowired
    IntervenantService intervenantService;

    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }


    @GetMapping("/Formateur/index")
    public String getIndex(Model model){

        List <Formateur> formateurList = intervenantService.getFormateurs();

        model.addAttribute("formateurList",formateurList);
        return "formateurs/index";
    }
    @GetMapping(value = { "login"})
    public String getHome(){
        return "login";
    }

    @PostMapping(value = {"/login"})
    public String getLogin(){
        return "login";
    }

//    @PostMapping("/logout-m")
//    public String postLogout(Model model){
//        model.addAttribute("bob","bob");
//        return "redirect:/login";
//    }


    @GetMapping("/bob/click")
    public String bobClick() throws IOException, ClassNotFoundException {

        exelService.creatSheetForEntity();
        return "redirect:/bob";
    }





    @GetMapping("/bob")
    public String getIndexBob(Model model) throws ClassNotFoundException, IOException {
        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = datetime1.format(format);


        LocalDate date1 = LocalDate.now();
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDate = datetime1.format(format2);

        model.addAttribute("datetime1",datetime1);
        model.addAttribute("formatDateTime",formatDateTime);
        model.addAttribute("formatDate",formatDate);
        model.addAttribute("date1",date1);

        //System.out.println(formatDateTime);


        Class<?> clazz = Class.forName("com.cda.contenu_seance.model.Intervenant") ;

//        Method[] champMethod = clazz.getMethods();

        Stream <Method> champMethod = Arrays.stream(clazz.getDeclaredMethods())
                .filter(bob->bob.getName()
                        .contains(new StringBuffer("get")));
        List<Method> methodList = champMethod.collect(Collectors.toList());

       // Annotation[] champMethod = clazz.getAnnotations();
        model.addAttribute("champMethod",methodList);

        Field[] fieldClass = clazz.getDeclaredFields();
//        Field[] fieldClass = clazz.getFields();
        model.addAttribute("fieldClass",fieldClass);


        return "bob";
    }









}
