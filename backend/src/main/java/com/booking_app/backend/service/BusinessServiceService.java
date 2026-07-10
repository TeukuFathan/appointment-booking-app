package com.booking_app.backend.service;

import com.booking_app.backend.dto.BusinessServiceRequest;
import com.booking_app.backend.dto.BusinessServiceResponse;
import com.booking_app.backend.entity.BusinessService;
import com.booking_app.backend.entity.User;
import com.booking_app.backend.repository.BusinessServiceRepository;
import com.booking_app.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceService {

    private UserRepository userRepository;
    private BusinessServiceRepository businessServiceRepository;

    public BusinessServiceService(UserRepository userRepository, BusinessServiceRepository businessServiceRepository){
        this.userRepository = userRepository;
        this.businessServiceRepository = businessServiceRepository;
    }
    
    private User getLoggedInUser() {
    Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

    String email = authentication.getName();

    return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public BusinessServiceResponse createBusinessService(BusinessServiceRequest serviceRequest){

        BusinessService service = new BusinessService();
        service.setName(serviceRequest.getName());
        service.setUser(getLoggedInUser());
        service.setDescription(serviceRequest.getDescription());
        service.setDurationMinutes(serviceRequest.getDurationMinutes());
        service.setPrice(serviceRequest.getPrice());
        service.setActive(true);

        BusinessService savedService = businessServiceRepository.save(service);

        return mapToResponse(savedService);
    }

    public List<BusinessServiceResponse> getListBusinessService(){
        List<BusinessServiceResponse> listbsResponse = new ArrayList<>();
        User user = getLoggedInUser();
        // so this return list of businessService
        List<BusinessService>  bsRepo = businessServiceRepository.findByUser_Id(user.getId());
        for(BusinessService bService : bsRepo){
            listbsResponse.add(mapToResponse(bService));
        }

        return listbsResponse;
    }

    public BusinessServiceResponse updateBusinessService(Long id , BusinessServiceRequest businessServiceRequest){
        // So get the user see his services 
        User user = getLoggedInUser();
        // Take the business services on this user

        Optional<BusinessService> OptionalBService = businessServiceRepository.findByIdAndUser_Id(id, user.getId());
        if(OptionalBService.isEmpty()){
            throw new RuntimeException("Business Service Requested Not found");
        }
        BusinessService bService = OptionalBService.get();
        bService.setName(businessServiceRequest.getName());
        bService.setDescription(businessServiceRequest.getDescription());
        bService.setPrice(businessServiceRequest.getPrice());
        bService.setDurationMinutes(businessServiceRequest.getDurationMinutes());

        businessServiceRepository.save(bService);
        return mapToResponse(bService);
    }

    public void deleteBusinessService(Long id){
        User user = getLoggedInUser();
        Optional<BusinessService> businessService = businessServiceRepository.findByIdAndUser_Id(id, user.getId());
        if(businessService.isEmpty()){
            throw new RuntimeException("BusinessService with this id : {} " +  id + " doesn't exist ! ");
        }
        businessServiceRepository.delete(businessService.get());
    }

    private BusinessServiceResponse mapToResponse(BusinessService businessService){
        BusinessServiceResponse serviceResponse = new BusinessServiceResponse();
        serviceResponse.setId(businessService.getId());
        serviceResponse.setName(businessService.getName());
        serviceResponse.setDescription(businessService.getDescription());
        serviceResponse.setDurationMinutes(businessService.getDurationMinutes());
        serviceResponse.setPrice(businessService.getPrice());
        serviceResponse.setActive(businessService.getActive());

        return serviceResponse;
    }


}
