package lk.darkoinnovex.Ayu.service;


import lk.darkoinnovex.Ayu.dto.LabReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabReportService {

    List<LabReportDTO> getAllLabReports();

    LabReportDTO createLabReport(LabReportDTO dto);

    LabReportDTO updateLabReport(LabReportDTO dto);

    LabReportDTO getLabReportById(Long id);

    List<LabReportDTO> getReportsByPatientId(Long id);
    Integer getLabReportsCountOfPatient(Long id);
}
