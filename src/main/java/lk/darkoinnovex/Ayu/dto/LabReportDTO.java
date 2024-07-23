package lk.darkoinnovex.Ayu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class LabReportDTO {

    private Long id;
    private String type;
    private Timestamp timestamp;
    private String file;
    private Long patientId;
    private Long hospitalId;
    private String reportType = "pdf";

    public LabReportDTO(Long id, String type, Timestamp timestamp, String file, Long patientId, Long hospitalId) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.file = file;
        this.patientId = patientId;
        this.hospitalId = hospitalId;
    }
}
