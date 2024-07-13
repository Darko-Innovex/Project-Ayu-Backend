package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.LabReportDTO;
import lk.darkoinnovex.Ayu.entity.LabReport;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.LabReportRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabReportServiceImpl implements LabReportService {
    @Autowired
    private LabReportRepository labReportRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<LabReportDTO> getAllLabReports() {
        List<LabReport> reports = labReportRepository.findAll();
        return reports.stream().map(labReport -> new LabReportDTO(labReport.getId(), labReport.getType(), labReport.getTimestamp(), labReport.getFile(), labReport.getPatient().getId())).toList();
    }

    @Override
    public LabReportDTO createLabReport(LabReportDTO dto) {
        Patient patient =patientRepository.findById(dto.getPatientId()).orElse(null);

        if (patient != null) {
            LabReport labReport = new LabReport();
            labReport.setType(dto.getType());
            labReport.setTimestamp(dto.getTimestamp());
            labReport.setFile(dto.getFile());
            labReport.setPatient(patient);

            LabReport savedLabReport = labReportRepository.save(labReport);
            return new LabReportDTO(savedLabReport.getId(), savedLabReport.getType(), savedLabReport.getTimestamp(), savedLabReport.getFile(), savedLabReport.getPatient().getId());
        }
        return null;
    }

    @Override
    public LabReportDTO updateLabReport(LabReportDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId()).orElse(null);

        if (patient != null) {
            LabReport labReport = new LabReport();
            labReport.setType(dto.getType());
            labReport.setTimestamp(dto.getTimestamp());
            labReport.setFile(dto.getFile());
            labReport.setPatient(patient);

            LabReport savedLabReport = labReportRepository.save(labReport);
            return new LabReportDTO(savedLabReport.getId(), savedLabReport.getType(), savedLabReport.getTimestamp(), savedLabReport.getFile(), savedLabReport.getPatient().getId());
        }
        return null;
    }

    @Override
    public LabReportDTO getLabReportById(Long id) {
        LabReport labReport = labReportRepository.findById(id).orElse(null);
        if (labReport != null) {
            return new LabReportDTO(labReport.getId(), labReport.getType(), labReport.getTimestamp(), labReport.getFile(), labReport.getPatient().getId());
        }
        return null;
    }

    @Override
    public List<LabReportDTO> getReportsByPatientId(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            List<LabReport> reports = labReportRepository.findByPatientId(patient).orElse(null);
            return reports.stream().map(labReport -> new LabReportDTO(labReport.getId(), labReport.getType(), labReport.getTimestamp(), labReport.getFile(), labReport.getPatient().getId())).toList();
        }
        return null;
    }

    @Override
    public Integer getLabReportsCountOfPatient(Long id) {

        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {
            return labReportRepository.countLabReportOfPatient(patient).orElse(0);
        }

        return null;
    }
}
