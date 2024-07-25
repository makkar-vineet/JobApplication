package com.example.company.controllers;

import com.example.company.AbstractService.CompanyService;
import com.example.company.entity.Company;
import com.example.firstJobApp.AbstractService.JobService;
import com.example.firstJobApp.jobEntity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/compaines")
    public List<Company> findAll() {
        return this.companyService.getAll();
    }

    @PostMapping("/company")
    public ResponseEntity<String> createcomp(@RequestBody Company comp) {
        this.companyService.createCompany(comp);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getByComapanyId(@PathVariable("id") Long id) {
        Company j = this.companyService.getByCompanyId(id);
        if (j != null) {
            return new ResponseEntity<>(j, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteCompByID/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean result = companyService.deleteCompByID(id);
        if (result)
            return ResponseEntity.ok("Delete Successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateComp")
    public ResponseEntity<Company> updateJob(@RequestBody Company comp) {
        Company updateCompany = companyService.updateJob(comp.getId(), comp);
        if (updateCompany != null)
            return new ResponseEntity<>(updateCompany, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
