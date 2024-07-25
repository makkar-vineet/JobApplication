package com.example.company.AbstractService;

import com.example.company.entity.Company;
import com.example.firstJobApp.jobEntity.Job;

import java.util.List;

public interface CompanyService {
     List<Company> getAll() ;

    void createCompany(Company company);

    Company getByCompanyId(Long id);

    boolean deleteCompByID(Long id);

    Company updateJob(Long id, Company company);

}
