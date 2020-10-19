package com.zschnebli.formfiller.controller;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormFillerController
{
    @GetMapping(value = "/form")
    public String getFormView()
    {
        return "index";
    }

    @PostMapping(value = "/fill")
    public void fillBlankPDFs(Model model)
    {
        try {
            PDDocument pDDocument = PDDocument.load(FormFillerController.class.getResourceAsStream("/pdf/anexa33_2.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("full_name");
            field.setValue("Schnebli Zoltan");
            pDDocument.save("anexa2_result.pdf");
            pDDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
