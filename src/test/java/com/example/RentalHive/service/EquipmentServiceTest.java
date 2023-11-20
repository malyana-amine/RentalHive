package com.example.RentalHive.Service;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.Service.imp.EquipmentServiceImp;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {
    private final List<Equipment> equipments = List.of(
            Equipment.builder().id(1L).name("equipment 1").type(Type.builder().id(1L).name("one").build()).status(EquipmentStatus.AVAILABLE).price(100.12).build(),
            Equipment.builder().id(2L).name("equipment 2").type(Type.builder().id(1L).name("one").build()).status(EquipmentStatus.UNAVAILABLE).price(30.34).build(),
            Equipment.builder().id(3L).name("equipment 3").type(null).status(EquipmentStatus.AVAILABLE).price(200.45).build(),
            Equipment.builder().id(4L).name("equipment 4").type(null).status(EquipmentStatus.UNAVAILABLE).price(97.0).build(),
            Equipment.builder().id(5L).name("equipment 5").type(Type.builder().id(2L).name("two").build()).status(EquipmentStatus.AVAILABLE).price(199.99).build(),
            Equipment.builder().id(6L).name("equipment 6").type(Type.builder().id(2L).name("two").build()).status(EquipmentStatus.UNAVAILABLE).price(333.33).build(),
            Equipment.builder().id(7L).name("equipment 7").type(null).status(EquipmentStatus.AVAILABLE).price(100.12).build(),
            Equipment.builder().id(8L).name("equipment 8").type(null).status(EquipmentStatus.UNAVAILABLE).price(0.0).build()
    );
    @InjectMocks
    EquipmentServiceImp service;
    @Mock
    EquipmentRepository repository;

    @Test
    void TestFindByStatusTypeNameToGetWithoutAnyFilter() {
        Mockito.doReturn(this.getMocksEquipments()).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(null, null, null);

        assertNotNull(list);
        assertEquals(8, list.size());
    }
//
//    @Test
//    void TestFindByStatusTypeNameToFilterWithNoAvailableDataMatch() {
//        Mockito.doReturn(this.getMocksEquipments(null, null, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
//        List<Equipment> list = service.findByStatusTypeName(null, null, null);
//
//        assertNotNull(list);
//        assertEquals(0, list.size());
//    }
//
//    @Test
//    void TestFindByStatusTypeNameToFilterWithName() {
//        String name = "8";
//
//        Mockito.doReturn(this.getMocksEquipments(null, null, name)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
//        List<Equipment> list = service.findByStatusTypeName(null, null, name);
//
//        assertNotNull(list);
//        assertEquals(1, list.size());
//        assertEquals("equipment 8", list.get(0).getName());
//    }
//
//    @Test
//    void TestFindByStatusTypeNameToFilterWithStatusAvailable() {
//        EquipmentStatus status = EquipmentStatus.AVAILABLE;
//
//        Mockito.doReturn(this.getMocksEquipments(status, null, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
//        List<Equipment> list = service.findByStatusTypeName(status, null, null);
//
//        assertNotNull(list);
//        assertEquals(4, list.size());
//        assertEquals("equipment 1", list.get(0).getName());
//    }

//    @Test
//    void TestFindByStatusTypeNameToFilterWithStatusNotAvailable() {
//        EquipmentStatus status = EquipmentStatus.UNAVAILABLE;
//
//        Mockito.doReturn(this.getMocksEquipments(status, null, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
//        List<Equipment> list = service.findByStatusTypeName(status, null, null);
//
//        assertNotNull(list);
//        assertEquals(4, list.size());
//        assertEquals("equipment 2", list.get(0).getName());
//    }
//
//    @Test
//    void TestFindByStatusTypeNameToFilterWithType() {
//        Type type = Type.builder().id(1L).name("one").build();
//
//        Mockito.doReturn(this.getMocksEquipments(null, type, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
//        List<Equipment> list = service.findByStatusTypeName(null, type, null);
//
//        assertNotNull(list);
//        assertEquals(2, list.size());
//        assertEquals("equipment 1", list.get(0).getName());
//        assertEquals("equipment 2", list.get(1).getName());
//    }

//    @Test
//    void TestFindByStatusTypeNameToFilterWithAnotherType() {
//        Type type = Type.builder().id(2L).name("two").build();
//
//        Mockito.doReturn(this.getMocksEquipments(null, type, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
//        List<Equipment> list = service.findByStatusTypeName(null, type, null);
//
//        assertNotNull(list);
//        assertEquals(2, list.size());
//        assertEquals("equipment 5", list.get(0).getName());
//        assertEquals("equipment 6", list.get(1).getName());
//    }

//    @Test
//    void TestFindByStatusTypeNameToFilterWithAllFields() {
//        String name = "5";
//        EquipmentStatus status = EquipmentStatus.AVAILABLE;
//        Type type = Type.builder().id(2L).name("two").build();
//
//        Mockito.doReturn(this.getMocksEquipments(status, type, name)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
//        List<Equipment> list = service.findByStatusTypeName(status, type, name);
//
//        assertNotNull(list);
//        assertEquals(5, list.size());
//    }


    @Test
    void TestSaveEquipmentValidWithSuccess(){
        Equipment newEquipment = Equipment.builder()
                .id(9L)
                .name("New Equipment")
                .type(Type.builder().id(3L).name("three").build())
                .status(EquipmentStatus.UNAVAILABLE)
                .price(50.0)
                .build();

        when(repository.save(any(Equipment.class))).thenReturn(newEquipment);

        Equipment savedEquipment = service.save(newEquipment);

        assertNotNull(savedEquipment);
    }

    @Test
    void testSaveValidEquipmentWithNullType() {
        Equipment newEquipment = Equipment.builder()
                .id(9L)
                .name("New Equipment")
                .status(EquipmentStatus.UNAVAILABLE)
                .price(50.0)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.save(newEquipment));
    }


    @Test
    void testSaveEquipmentWithNullName() {
        Equipment newEquipment = Equipment.builder()
                .id(9L)
                .type(Type.builder().id(3L).name("three").build())
                .status(EquipmentStatus.UNAVAILABLE)
                .price(50.0)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.save(newEquipment));
    }


    @Test
    void testSaveEquipmentWithNegativePrice() {
        Equipment newEquipment = Equipment.builder()
                .id(9L)
                .name("New Equipment")
                .type(Type.builder().id(3L).name("three").build())
                .status(EquipmentStatus.UNAVAILABLE)
                .price(-50.0)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.save(newEquipment));

    }

    @Test
    void testUpdateEntireEquipmentSuccess() {

        Equipment existingEquipment = Equipment.builder()
                .id(1L)
                .name("Existing Equipment")
                .status(EquipmentStatus.AVAILABLE)
                .price(100.0)
                .type(Type.builder().id(1L).name("Type1").build())
                .build();

        Equipment updatedEquipment = Equipment.builder()
                .id(1L)
                .name("Updated Equipment")
                .status(EquipmentStatus.UNAVAILABLE)
                .price(150.0)
                .type(Type.builder().id(2L).name("Type2").build())
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(existingEquipment));
        when(repository.save(any(Equipment.class))).thenReturn(updatedEquipment);


        Equipment result = service.updateEntireEquipment(updatedEquipment);


        assertEquals("Updated Equipment", result.getName());
        assertEquals(EquipmentStatus.UNAVAILABLE, result.getStatus());
        assertEquals(150.0, result.getPrice());
        assertEquals("Type2", result.getType().getName());
    }

    @Test
    void testUpdateEntireEquipmentNotFound() {

        Equipment updatedEquipment = Equipment.builder()
                .id(1L)
                .name("Updated Equipment")
                .status(EquipmentStatus.UNAVAILABLE)
                .price(150.0)
                .type(Type.builder().id(2L).name("Type2").build())
                .build();

        when(repository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(IllegalArgumentException.class, () -> service.updateEntireEquipment(updatedEquipment));
    }


    private List<Equipment> getMocksEquipments(){
        return equipments;
    }


    private List<Equipment> getMocksEquipments(EquipmentStatus status, Type type, String string){
    return equipments.stream().filter(e -> (status != null && Objects.equals(e.getStatus(), status)) || (type != null && e.getType() != null && e.getType().equals(type)) || (string != null && e.getName().contains(string))).collect(Collectors.toList());
   }
}