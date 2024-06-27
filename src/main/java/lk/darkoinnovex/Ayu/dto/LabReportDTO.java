package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabReportDTO {

    private Long id;
    private String type;
    private Timestamp timestamp;
    private byte[] file;
    private Long patientId;

}
