package lk.darkoinnovex.Ayu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class LabReportDTO {

    private Long id;
    private String type;
    private Timestamp timestamp;
    private MultipartFile file;
    private Long patientId;
    private Long hospitalId;
    private String status;
    private String reportType = "pdf";

    public LabReportDTO(Long id, String type, Timestamp timestamp, MultipartFile file, Long patientId, Long hospitalId, String status) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.file = file;
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.status = status;
    }
}
