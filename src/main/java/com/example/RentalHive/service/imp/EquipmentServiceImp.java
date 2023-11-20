package com.example.RentalHive.service.imp;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


import static com.example.RentalHive.Helpers.EquipementValidation.validateEquipmentForSave;

@Component
@RequiredArgsConstructor
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

      Optional<Equipment> optionalExistingEquipment = repository.findById(equipment.getId());

      if (optionalExistingEquipment.isPresent()) {
          Equipment existingEquipment = optionalExistingEquipment.get();

          existingEquipment.setName(equipment.getName());
          existingEquipment.setStatus(equipment.getStatus());
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

    @Override
    public boolean removeById(Long id){
        Optional<Equipment> optionalEquipment = repository.findById(id);

        if (optionalEquipment.isPresent()) {
            Equipment equipment = optionalEquipment.get();
            repository.delete(equipment);
            return true;
        } else {
//            throw new IllegalArgumentException("Equipment not found with ID: " + id);
            return false;
        }
    }


}
