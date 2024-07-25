package com.example.company.companyServiceImpl;


import com.example.company.AbstractService.CompanyService;
import com.example.company.entity.Company;
import com.example.company.repository.CompanyRepository;
import com.example.firstJobApp.jobEntity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
         companyRepository.saveAndFlush(company);
    }

    @Override
    public Company getByCompanyId(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompByID(Long id) {
       try {
           companyRepository.deleteById(id);
           return true;
       }catch (Exception e){
           return false;
       }

    }

    @Override
    public Company updateJob(Long id, Company company) {
        Optional<Company> compOptional= companyRepository.findById(id);
        if (compOptional.isPresent()) {
            Company c = compOptional.get();
            c.setAddress(company.getAddress());
            c.setName(company.getName());
            c.setLocation(company.getLocation());
            c.setId(company.getId());
            c.setJobs(company.getJobs());
            c.setReviews(company.getReviews());
            return companyRepository.save(c);
        }
        return null;
    }
}
