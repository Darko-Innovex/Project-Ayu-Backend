package lk.darkoinnovex.Ayu.service;


import lk.darkoinnovex.Ayu.dto.MedicalReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MedicalReportService {
    List<MedicalReportDTO> getAllReports();

    MedicalReportDTO createReport(MedicalReportDTO dto);

    MedicalReportDTO updateReport(MedicalReportDTO dto);

    MedicalReportDTO getReportById(Long id);

    MedicalReportDTO getReportByAppoinmentId(Long id);

    List<MedicalReportDTO> getReportsByPatientId(Long id);
}
