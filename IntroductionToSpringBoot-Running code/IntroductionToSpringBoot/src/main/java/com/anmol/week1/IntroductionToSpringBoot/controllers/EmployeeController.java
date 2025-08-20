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

    
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    //Fetching a particular employee 
    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
       Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);
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


    
    //Adding an employee
    @PostMapping
    //@Valid annotation is used to validate all the fields of the EmployeeDTO
    public ResponseEntity<EmployeeDTO> CreateNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO employeeDTO = employeeService.CreateNewEmployee(inputEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }


    
    //Updating an existing employee
    @PutMapping(path = "/Update/{employeeId}")
    public EmployeeDTO UpdateEmployeeById(EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return employeeService.UpdateEmployeeById(employeeDTO,employeeId);
    }


    
    //Updating some details of an existing employee
    @PatchMapping(path = {"/UpdatePartial/{employeeId}"})
    public EmployeeDTO UpdatePartialEmployeeById(Map<String,Object> updates , @PathVariable Long employeeId){
        return employeeService.UpdatePartialEmployeeById(updates,employeeId);
    }


    
    //Deleting an employee
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

  
    //Deleting all the employees 
    @DeleteMapping(path = "/deleteAll")
    public Boolean deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return true;
    }

    
}
