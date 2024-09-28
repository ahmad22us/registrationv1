package com.api.services;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDTO;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
//    private KYCRepository kycRepository;

    @Autowired
    private ModelMapper modelMapper;

//    public RegistrationService(RegistrationRepository registrationRepository, KYCRepository kycRepository) {  // constructor injection
    public RegistrationService(RegistrationRepository registrationRepository) {  // constructor injection
        this.registrationRepository = registrationRepository;
//        this.kycRepository = kycRepository;
    }

    public List<RegistrationDTO> getRegistrations(){
//        List<Registration> registrations = registrationRepository.findAll();
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDTO> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }


    public RegistrationDTO createRegistration(RegistrationDTO registrationDTO){

        Registration registration = mapToEntity(registrationDTO); // put the DTO data in the Entity

        Registration savedEntity = registrationRepository.save(registration); // insert in registration table and
        // returns the info about saved entity

//        KYC kyc = new KYC();
//        kyc.setPanNumber(registrationDTO.getPanNumber()); // get from DTO and set in kyc Entity
//        KYC saveedKYC = kycRepository.save(kyc);// insert KYC in kyc table

        RegistrationDTO dto = mapToDto(savedEntity);// return selective data not all Entity
        return dto;
    }

    public void deleteRegistration(long id) {

        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {

        Registration reg = registrationRepository.findById(id).get();
        reg.setName(registration.getName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());

        Registration savedEntity = registrationRepository.save(reg);

        return savedEntity;
    }

   Registration mapToEntity(RegistrationDTO registrationDTO){
       Registration registration =  modelMapper.map(registrationDTO,Registration.class); // source = registrationDTO, dest = Registration.class
//       Registration registration = new Registration();  // put the DTO data in the Entity
//       registration.setName(registrationDTO.getName());
//       registration.setEmail(registrationDTO.getEmail());
//       registration.setMobile(registrationDTO.getMobile());

       return registration;
    }

    RegistrationDTO mapToDto(Registration registration){
       RegistrationDTO dto =  modelMapper.map(registration, RegistrationDTO.class);
//        RegistrationDTO dto = new RegistrationDTO();
//        dto.setName(registration.getName());  // copy the data from Entity to DTO
//        dto.setEmail(registration.getEmail());
//        dto.setMobile(registration.getMobile());
//        dto.setPanNumber(saveedKYC.getPanNumber());

        return dto;
    }

    public RegistrationDTO getRegistrationsById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Record not Found")
        );
        return mapToDto(registration);
    }
}
