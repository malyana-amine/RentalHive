package com.example.RentalHive.DTO;

import com.example.RentalHive.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandDTO {
    private Long id;
    private List<Long> equipmentIds;
    private List<LocalDate> startDateList;
    private List<LocalDate> endDateList;
    private Status status;
    private UsersDTO user;
}