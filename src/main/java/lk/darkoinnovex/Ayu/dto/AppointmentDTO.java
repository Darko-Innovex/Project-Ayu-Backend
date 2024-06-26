package lk.darkoinnovex.Ayu.dto;

import lk.darkoinnovex.Ayu.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private int appointmentNo;
    private Timestamp timestamp;
    private Long patientId;
    private Long doctorId;
    private Long hospitalId;
    private Long medicalReportId;
    private Long medicineBillId;
}
