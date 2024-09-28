package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDTO;
import com.api.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping() // convert java object to JSON object
    public ResponseEntity<List<RegistrationDTO>> getAllRegistration(

    ) {  // Generic return
        List<RegistrationDTO> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK); // ResponseEntity helps to set response code
    }

    @PostMapping
//    public ResponseEntity<RegistrationDTO> createRegistration(
    public ResponseEntity<?> createRegistration(  // give ? when returning multiple types
            @Valid @RequestBody RegistrationDTO registrationDTO,
            BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // returns String
        }
        RegistrationDTO reg = registrationService.createRegistration(registrationDTO);
        return new ResponseEntity<>(reg, HttpStatus.CREATED); // should be 201
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam long id) { // identified by ?id=
        registrationService.deleteRegistration(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(
            @PathVariable long id,  // in the postman endpoint use "/" instead of "?"
            @RequestBody Registration registration) {

        Registration updatedRegistration = registrationService.updateRegistration(id, registration);

        return new ResponseEntity<>(updatedRegistration, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<RegistrationDTO> getRegistrationByID(
//            @Pattern(regexp = "\\d+",message = "not numeric")
            @PathVariable long id
//            @RequestHeader Integer id
    ){
       RegistrationDTO dto =  registrationService.getRegistrationsById(id);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}