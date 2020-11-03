package com.zschnebli.formfiller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormFillerController
{
    @GetMapping(value = "/form")
    public String getFormView()
    {
        return "index";
    }
}
