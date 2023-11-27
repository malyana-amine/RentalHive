package com.example.RentalHive.service;

import com.example.RentalHive.entity.DemandedEquipment;
import org.springframework.stereotype.Service;

@Service
public interface DemandedEquipmentService extends CrudService<DemandedEquipment, Long> {
    Boolean isThisDemandedEquipmentAllowed(DemandedEquipment demandedEquipment);
}
