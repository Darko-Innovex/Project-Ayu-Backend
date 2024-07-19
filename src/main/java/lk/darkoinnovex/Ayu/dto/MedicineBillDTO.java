package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineBillDTO {
    private Long id;
    private Timestamp timestamp;
    private Long doctorId;
    private Long appointmentId;
    private List<MedicineDTO> medicineList;
}
