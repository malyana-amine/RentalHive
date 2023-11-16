package com.example.RentalHive.Service;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.StatusEnum;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.Repositories.EquipmentRepository;
import com.example.RentalHive.Service.Impl.EquipmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;




@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EquipmentServiceImplTest {

    private EquipmentServiceImpl equipmentServiceImpl;
    private EquipmentRepository equipmentRepository;

    @BeforeEach
    public void setUp() {
        equipmentRepository = mock(EquipmentRepository.class);
        equipmentServiceImpl = new EquipmentServiceImpl(equipmentRepository);
    }

    public Type CreateValidType(){
        Type type = new Type();
        type.setId(1L);
        type.setName("type1");
        return type;
    }

    public Equipment CreateValidEquipment(){
        Equipment equipment = new Equipment();
        equipment.setId(1L);
        equipment.setName("equipment1");
        equipment.setStatus(StatusEnum.dispo);
        equipment.setPrice(10.0);
        return equipment;
    }


    @Test
    void testSaveEquipmentValidWithSuccess() {
           Type type = CreateValidType();
           Equipment equipment = CreateValidEquipment();
           equipment.setType(type);
           Mockito.when(equipmentRepository.save(equipment)).thenReturn(equipment);
           Equipment savedEquipment = equipmentServiceImpl.save(equipment);

           assertEquals(equipment, savedEquipment);
    }



    @Test
    void testSaveEquipmentWithNullName(){
        Type type = CreateValidType();
        Equipment equipment = new Equipment();
        equipment.setId(1L);
        equipment.setName(null);
        equipment.setType(type);
        equipment.setStatus(StatusEnum.dispo);
        equipment.setPrice(10.0);

        Mockito.when(equipmentRepository.save(equipment)).thenReturn(equipment);
        Equipment savedEquipment = equipmentServiceImpl.save(equipment);

        assertThrows(IllegalArgumentException.class, () -> equipmentServiceImpl.save(equipment));
    }

    @Test
    void testSaveValidEquipmentWithNullType() {

        Equipment equipmentWithNullType = CreateValidEquipment();
        equipmentWithNullType.setType(null);


        Mockito.when(equipmentRepository.save(equipmentWithNullType)).thenReturn(equipmentWithNullType);


        assertThrows(IllegalArgumentException.class, () -> equipmentServiceImpl.save(equipmentWithNullType));

    }

    @Test
    void testSaveEquipmentWithNegativePrice() {

        Equipment equipmentWithNegativePrice = CreateValidEquipment();
        equipmentWithNegativePrice.setPrice(-10.0);


        assertThrows(IllegalArgumentException.class, () -> equipmentServiceImpl.save(equipmentWithNegativePrice));

    }




    @Test
    void update() {
    }

    @Test
    void findAll() {
    }
}