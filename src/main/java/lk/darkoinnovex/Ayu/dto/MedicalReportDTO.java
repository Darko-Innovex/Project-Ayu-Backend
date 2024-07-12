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
    private String file;
    private Long doctorId;
    private String reportType = "text";

    public MedicalReportDTO(Long id, Timestamp timestamp, String file, Long doctorId) {
        this.id = id;
        this.timestamp = timestamp;
        this.file = file;
        this.doctorId = doctorId;
    }
}
