package com.anmol.week1.IntroductionToSpringBoot.controllers;

import com.anmol.week1.IntroductionToSpringBoot.DTO.EmployeeDTO;
import com.anmol.week1.IntroductionToSpringBoot.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//when the service_layer was not used as bridge then we used this code where we have injected the repository directly inside
// the controller and this was working perfectly but its not recommended

//    private final EmployeeRepository employeeRepository;
//
//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//
//
//    @GetMapping(path = "/{employeeId}")
//    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
//    // return new EmployeeDTO(employeeId,"anmol","abc@gmail.com",22,LocalDate.of(2020,1,2),true);
//        return employeeRepository.findById(employeeId).orElse(null);
//
//    }
//
//    @GetMapping
//    public List<EmployeeEntity> getAllEmployees(){
//        return employeeRepository.findAll();
//    }
//
//    @PostMapping
//    public EmployeeEntity CreateNewEmployee(@RequestBody EmployeeEntity inputEmployee){
//        return employeeRepository.save(inputEmployee);
//    }


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
       Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);

// this below code will also work but it will not show the meaningful information in postman if we try to found a user whose ID did not exist
//       return employeeDTO.
//               map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).
//               orElse(ResponseEntity.notFound().build());

        return employeeDTO.
               map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).
               orElseThrow(()-> new NoSuchElementException("No employee exists with given Id "+ employeeId ));
    }


    //Getting All the Employees
    @GetMapping(path = "/GetAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
         List<EmployeeDTO> ans = employeeService.getAllEmployees();
         if(ans.isEmpty()){
             return ResponseEntity.notFound().build();
         }
         else{
             return ResponseEntity.ok(ans);
         }
    }


    @PostMapping
    //@Valid annotation , this is used to validate all the fields of the EmployeeDTO
    //how this works , as soon as the control of the program counters @Valid it will NOT go to the EmployeeDTO employeeDTO = employeeService.CreateNewEmployee(inputEmployee);
    //it will go to the EmployeeDTO for checking the validation of the fields and then the control will come to EmployeeDTO employeeDTO = emp..

    public ResponseEntity<EmployeeDTO> CreateNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO employeeDTO = employeeService.CreateNewEmployee(inputEmployee);
        // write either this line ->  return ResponseEntity.ok(employeeDTO); OR down one
        // The below one is used to define different types of status codes
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }


    @PutMapping(path = "/Update/{employeeId}")
    public EmployeeDTO UpdateEmployeeById(EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return employeeService.UpdateEmployeeById(employeeDTO,employeeId);
    }

    @PatchMapping(path = {"/UpdatePartial/{employeeId}"})
    public EmployeeDTO UpdatePartialEmployeeById(Map<String,Object> updates , @PathVariable Long employeeId){
        // we have taken a Map of string,object but you could have also taken <string,string>
        return employeeService.UpdatePartialEmployeeById(updates,employeeId);
    }


    //done by own , each and every logic of this below delete method
    @DeleteMapping(path = "/delete/{employeeId}")
    public ResponseEntity<Boolean> DeleteEmployeeById(@PathVariable Long employeeId){
        boolean ans = employeeService.DeleteEmployeeById(employeeId);
        if(ans){
           return ResponseEntity.ok(true);
        }
        else{
          return  ResponseEntity.notFound().build();
        }
    }

    //done by own
    //used to delete all the employees from the DB
    @DeleteMapping(path = "/deleteAll")
    public Boolean deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return true;
    }

}
