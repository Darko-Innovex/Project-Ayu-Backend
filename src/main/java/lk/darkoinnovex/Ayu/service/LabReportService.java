package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.LabReportDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface LabReportService {

    List<LabReportDTO> getAllLabReports();

    LabReportDTO createLabReport(LabReportDTO dto);

    LabReportDTO updateLabReport(LabReportDTO dto);

    LabReportDTO getLabReportById(Long id);

    List<LabReportDTO> getReportsByPatientId(Long id, Integer page, Integer count);

    Integer getLabReportsCountOfPatient(Long id);

    List<LabReportDTO> getAllLabReportsOfPatientOnDate(Long pId, LocalDate date, Integer page, Integer count);

    List<LabReportDTO> findLabReportsByPatientIdAndDateRange(Long pId, LocalDate startDate, LocalDate endDate, Integer page, Integer count);

    List<LabReportDTO> getLabReportsOfHospital(Long id);
}
