package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.Service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;


import static com.example.RentalHive.Helpers.EquipementValidation.validateEquipmentForSave;
import static com.example.RentalHive.Helpers.EquipmentUpdater.updateEquipment;

@Component @RequiredArgsConstructor
public class EquipmentServiceImp implements EquipmentService {
    private final EquipmentRepository repository;
    @Override
    public List<Equipment> findByStatusTypeName(EquipmentStatus status, Type type, String name) {
//        return repository.findByStatusAndTypeAndName(status, type, name);
        return null;
    }

    @Override
     public Equipment save(Equipment equipment){

        validateEquipmentForSave(equipment);

        return repository.save(equipment);
    }



  @Override
    public Equipment updateEntireEquipment(Equipment equipment){


      validateEquipmentForSave(equipment);

      Optional<Equipment> optionalExistingEquipment = repository.findById(equipment.getId());

      return updateEquipment(optionalExistingEquipment, equipment);
  }


    @Override

    public Optional<Equipment> findById(Long id){
        return repository.findById(id);
    }



}
