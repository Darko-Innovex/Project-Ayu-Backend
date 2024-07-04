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
    private byte[] file;
    private Long patientId;
    private String reportType = "pdf";

    public LabReportDTO(Long id, String type, Timestamp timestamp, byte[] file, Long patientId) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.file = file;
        this.patientId = patientId;
    }
}
