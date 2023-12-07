package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.Equipment;
import com.example.RentalHive.entity.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


import static com.example.RentalHive.helpers.EquipmentValidation.validateEquipmentForSave;

@Component @RequiredArgsConstructor
public class EquipmentServiceImp implements EquipmentService {
    private final EquipmentRepository repository;
    @Override
    public List<Equipment> findByStatusTypeName(Type type, String name) {
         return repository.findByTypeAndName(type, name);
    }

    @Override
     public Equipment save(Equipment equipment, MultipartFile imageFile) throws IOException {
        List<Equipment> existingEquipments = repository.findAll();
        validateEquipmentForSave(equipment,existingEquipments);

        String imgName = imageFile.getOriginalFilename();

        File upl = new File("imagesSpring/" + imgName);
        upl.createNewFile();
        FileOutputStream fout = new FileOutputStream(upl);
        fout.write(imageFile.getBytes());
        fout.close();

        equipment.setImage(imgName);
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

    public Optional<Equipment> findByIdWithTypeInfo(Long id) {
        return repository.findByIdWithTypeInfo(id);
    }
}
