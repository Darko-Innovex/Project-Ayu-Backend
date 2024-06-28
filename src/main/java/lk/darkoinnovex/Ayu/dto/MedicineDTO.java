package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineDTO {

    private Long id;

    private Timestamp timestamp;

    private int dayCount;

    private String medicineName;

    private String medicineBrand;

    private double medicineWeight;

    private double dose;

    private String dosesPerDay;
}
