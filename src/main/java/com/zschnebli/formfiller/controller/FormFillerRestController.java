package com.zschnebli.formfiller.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zschnebli.formfiller.dto.UserDTO;

@RestController
public class FormFillerRestController
{
    @PostMapping(value = "/form/fill")
    public ResponseEntity fillBlankPDFs(UserDTO userDTO)
    {
        try {
            PDDocument pDDocument = PDDocument.load(FormFillerController.class.getResourceAsStream("/pdf/anexa666.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("full_name");
            field.setValue(userDTO.getFirstName() + " " + userDTO.getLastName());
            pDDocument.save("anexa666_result.pdf");
            pDDocument.close();

            Path pdfPath = Paths.get("anexa666_result.pdf");
            byte[] letter = Files.readAllBytes(pdfPath);

            return new ResponseEntity<>(letter, getPdfHttpHeaders("file.pdf", letter.length), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static HttpHeaders getPdfHttpHeaders(String filename, int contentLength)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(contentLength);
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + filename);
        return httpHeaders;
    }
}
