package com.booking_app.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking_app.backend.dto.BusinessServiceRequest;
import com.booking_app.backend.dto.BusinessServiceResponse;
import com.booking_app.backend.entity.BusinessService;
import com.booking_app.backend.service.BusinessServiceService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/services")
public class BusinessServiceController {
    private final BusinessServiceService businessServiceService;

    public BusinessServiceController(BusinessServiceService businessServiceService){ 
        this.businessServiceService = businessServiceService;
    }

    @GetMapping()
    public List<BusinessServiceResponse> getBusinessServices() {
        return businessServiceService.getListBusinessService();
    }

    @PostMapping()
    public BusinessServiceResponse postBusinessService(@RequestBody BusinessServiceRequest businessServicesRequest) {
        return businessServiceService.createBusinessService(businessServicesRequest);
    }

    @PutMapping("/{id}")
    public BusinessServiceResponse putBusinessService(@PathVariable Long id, @RequestBody BusinessServiceRequest businessServiceRequest) {        
        return businessServiceService.updateBusinessService(id,businessServiceRequest);
    }
    
    @DeleteMapping("/{id}")
    public void deleteBusinessService(@PathVariable Long id){
        businessServiceService.deleteBusinessService(id);
    }
    
    
}
