package lk.darkoinnovex.Ayu.dto;

import lk.darkoinnovex.Ayu.entity.Medicine;
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

    public Medicine toEntity() {
        Medicine medicine = new Medicine();

        medicine.setId(id);
        medicine.setTimestamp(timestamp);
        medicine.setDayCount(dayCount);
        medicine.setMedicineName(medicineName);
        medicine.setMedicineBrand(medicineBrand);
        medicine.setMedicineWeight(medicineWeight);
        medicine.setDose(dose);
        medicine.setDosesPerDay(dosesPerDay);

        return medicine;
    }
}
