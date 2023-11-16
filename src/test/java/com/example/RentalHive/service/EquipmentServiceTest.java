package com.example.RentalHive.Service;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.StatusEnum;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.Service.imp.EquipmentServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {
    private final List<Equipment> equipments = List.of(
            Equipment.builder().id(1L).name("equipment 1").type(Type.builder().id(1L).name("one").build()).status(StatusEnum.dispo).price(100.12).build(),
            Equipment.builder().id(2L).name("equipment 2").type(Type.builder().id(1L).name("one").build()).status(StatusEnum.indispo).price(30.34).build(),
            Equipment.builder().id(3L).name("equipment 3").type(null).status(StatusEnum.dispo).price(200.45).build(),
            Equipment.builder().id(4L).name("equipment 4").type(null).status(StatusEnum.indispo).price(97.0).build(),
            Equipment.builder().id(5L).name("equipment 5").type(Type.builder().id(2L).name("two").build()).status(StatusEnum.dispo).price(199.99).build(),
            Equipment.builder().id(6L).name("equipment 6").type(Type.builder().id(2L).name("two").build()).status(StatusEnum.indispo).price(333.33).build(),
            Equipment.builder().id(7L).name("equipment 7").type(null).status(StatusEnum.dispo).price(100.12).build(),
            Equipment.builder().id(8L).name("equipment 8").type(null).status(StatusEnum.indispo).price(0.0).build()
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

    @Test
    void TestFindByStatusTypeNameToFilterWithNoAvailableDataMatch() {
        Mockito.doReturn(this.getMocksEquipments(null, null, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(null, null, null);

        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void TestFindByStatusTypeNameToFilterWithName() {
        String name = "8";

        Mockito.doReturn(this.getMocksEquipments(null, null, name)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(null, null, name);

        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("equipment 8", list.get(0).getName());
    }

    @Test
    void TestFindByStatusTypeNameToFilterWithStatusAvailable() {
        StatusEnum status = StatusEnum.dispo;

        Mockito.doReturn(this.getMocksEquipments(status, null, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(status, null, null);

        assertNotNull(list);
        assertEquals(4, list.size());
        assertEquals("equipment 1", list.get(0).getName());
    }

    @Test
    void TestFindByStatusTypeNameToFilterWithStatusNotAvailable() {
        StatusEnum status = StatusEnum.indispo;

        Mockito.doReturn(this.getMocksEquipments(status, null, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(status, null, null);

        assertNotNull(list);
        assertEquals(4, list.size());
        assertEquals("equipment 2", list.get(0).getName());
    }

    @Test
    void TestFindByStatusTypeNameToFilterWithType() {
        Type type = Type.builder().id(1L).name("one").build();

        Mockito.doReturn(this.getMocksEquipments(null, type, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(null, type, null);

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("equipment 1", list.get(0).getName());
        assertEquals("equipment 2", list.get(1).getName());
    }

    @Test
    void TestFindByStatusTypeNameToFilterWithAnotherType() {
        Type type = Type.builder().id(2L).name("two").build();

        Mockito.doReturn(this.getMocksEquipments(null, type, null)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(null, type, null);

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("equipment 5", list.get(0).getName());
        assertEquals("equipment 6", list.get(1).getName());
    }

    @Test
    void TestFindByStatusTypeNameToFilterWithAllFields() {
        String name = "5";
        StatusEnum status = StatusEnum.dispo;
        Type type = Type.builder().id(2L).name("two").build();

        Mockito.doReturn(this.getMocksEquipments(status, type, name)).when(repository).findByStatusAndTypeAndName(any(), any(), any());
        List<Equipment> list = service.findByStatusTypeName(status, type, name);

        assertNotNull(list);
        assertEquals(5, list.size());
    }


    @Test
    void TestSaveEquipmentValidWithSuccess(){
        Equipment newEquipment = Equipment.builder()
                .id(9L)
                .name("New Equipment")
                .type(Type.builder().id(3L).name("three").build())
                .status(StatusEnum.dispo)
                .price(50.0)
                .build();

        when(repository.save(any(Equipment.class))).thenReturn(newEquipment);

        Equipment savedEquipment = service.save(newEquipment);

        assertNotNull(savedEquipment);
    }

    @Test
    void testSaveValidEquipmentWithNullType() {

    }


    @Test
    void testSaveEquipmentWithNullName() {
    }


    @Test
    void testSaveEquipmentWithNegativePrice() {


    }


    private List<Equipment> getMocksEquipments(){
        return equipments;
    }

    private List<Equipment> getMocksEquipments(StatusEnum status, Type type, String string){
        return equipments.stream().filter(e -> (status != null && e.getStatus().equals(status)) || (type != null && e.getType() != null && e.getType().equals(type)) || (string != null && e.getName().contains(string))).collect(Collectors.toList());
    }
}