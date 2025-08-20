package com.anmol.week1.IntroductionToSpringBoot.services;

import com.anmol.week1.IntroductionToSpringBoot.DTO.EmployeeDTO;
import com.anmol.week1.IntroductionToSpringBoot.entities.EmployeeEntity;
import com.anmol.week1.IntroductionToSpringBoot.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository ;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }




    public  Optional<EmployeeDTO> getEmployeeById(Long id){
// we are commenting below line because it might return null value and if we are using ResponseEntity so it will not show the correct status
// code when we will try to get an employee whose id is not in DB so it will through error in console that source cannot be null in modelmapper

//      EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);

        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1 ,EmployeeDTO.class));
    }


    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        //As we have to return the EmployeeDTO list so converting each EmployeeEntity into EmployeeDTO
        //converting the list of entity into Stream by .stream then mapping/converting each EmployeeEntity into EmployeeDTO by
        //.map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
        //at last we will collect all the conversion by.collect(Collectors.toList()); then return it

        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }



    public EmployeeDTO CreateNewEmployee(@RequestBody EmployeeDTO inputEmployee){

        EmployeeEntity e = modelMapper.map(inputEmployee,EmployeeEntity.class);

        //Repository only accept entity as its input parameter
        EmployeeEntity employeeEntity = employeeRepository.save(e);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }


    //done by own
    public boolean DeleteEmployeeById(@PathVariable Long employeeId){
        if(!employeeRepository.existsById(employeeId)) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }


    //done by own
    public ResponseEntity<Boolean> deleteAllEmployees(){
         employeeRepository.deleteAll();
         return ResponseEntity.ok(true);
    }



    //it works as hashmap means if already present then put that value else do the required mapping
    public EmployeeDTO UpdateEmployeeById(EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity employeeEntity1 =  employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity1,EmployeeDTO.class);
    }


    public EmployeeDTO UpdatePartialEmployeeById(Map<String,Object> updates , @PathVariable Long employeeId){

        if(!employeeRepository.existsById(employeeId)){
            return null ;
        }
       //finding the entity which needs to be updated
      EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
      //traversing over the map to get the fields which need to be updated
      updates.forEach( (field,value) -> {
          //we only have to update the fields that are present inside the Map and not the fields that are not present inside
          //the map so we have used Reflection concept. It's a concept where we can go to the object and can update the fields
          //of that object directly .
          Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class , field);
          fieldToBeUpdated.setAccessible(true);
          ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
      });
      //saving the data inside the DB after updating the fields and then returning it
      return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }


}
