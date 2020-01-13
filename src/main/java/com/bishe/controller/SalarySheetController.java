package com.bishe.controller;

import com.bishe.service.SalarySheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SalarySheet")
public class SalarySheetController {

    @Autowired
    SalarySheetService salarySheetService;

}
