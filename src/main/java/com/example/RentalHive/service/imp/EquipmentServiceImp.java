package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entity.Equipment;
import com.example.RentalHive.Entity.Type;
import com.example.RentalHive.Repository.EquipmentRepository;
import com.example.RentalHive.Service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;


import static com.example.RentalHive.Helpers.EquipementValidation.validateEquipmentForSave;

@Component @RequiredArgsConstructor
public class EquipmentServiceImp implements EquipmentService {
    private final EquipmentRepository repository;
    @Override
    public List<Equipment> findByStatusTypeName(Type type, String name) {
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

      Optional<Equipment> optionalExistingEquipment = repository.findById(equipment.getId());

      if (optionalExistingEquipment.isPresent()) {
          Equipment existingEquipment = optionalExistingEquipment.get();

          existingEquipment.setName(equipment.getName());
          existingEquipment.setPrice(equipment.getPrice());

          if (equipment.getType() != null) {
              existingEquipment.setType(equipment.getType());
          }

          return repository.save(existingEquipment);
      } else {
          throw new IllegalArgumentException("Equipment not found with ID: " + equipment.getId());
      }
  }


    @Override
    public Optional<Equipment> findById(Long id){
        return repository.findById(id);
    }



}
