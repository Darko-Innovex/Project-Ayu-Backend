package lk.darkoinnovex.Ayu.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalReportDTO {

    private Long id;
    private Timestamp timestamp;
    private byte[] file;
    private Long doctorId;

}
