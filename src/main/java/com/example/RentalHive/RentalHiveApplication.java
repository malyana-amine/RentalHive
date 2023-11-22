package com.example.RentalHive;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.DemandedEquipment;
import com.example.RentalHive.entity.Equipment;
import com.example.RentalHive.repository.DemandEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RentalHiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalHiveApplication.class, args);
    }


}

@Configuration
class DummyConfig implements CommandLineRunner {
    private final DemandEquipmentRepository repository;

    @Autowired
    public DummyConfig(DemandEquipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 2; i++) {
            LocalDate startDate = generateRandomDate();  // Implement this method to generate valid start dates
            LocalDate endDate = generateEndDate(startDate); // Implement this method to generate valid end dates

            DemandedEquipment demandedEquipment = DemandedEquipment.builder()
                    .startDate(startDate)
                    .endDate(endDate)
                    .demand(Demand.builder().id(generateRandomId()).build())
                    .equipment(Equipment.builder().id(generateRandomId()).build())
                    .build();

            repository.save(demandedEquipment);
        }
    }

    private long generateRandomId() {
        return ThreadLocalRandom.current().nextInt(1, 8); // Adjust the range as needed
    }

    private LocalDate generateRandomDate() {
        long minDay = LocalDate.of(2022, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2024, 1, 1).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        Instant instant = LocalDate.ofEpochDay(randomDay).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return instant.atZone(ZoneId.of("UTC")).toLocalDate();
    }

    private LocalDate generateEndDate(LocalDate startDate) {
        // Add a random number of days to the start date to get the end date
        int randomDays = ThreadLocalRandom.current().nextInt(1, 30); // Adjust the range as needed
        return startDate.plusDays(randomDays);
    }
}