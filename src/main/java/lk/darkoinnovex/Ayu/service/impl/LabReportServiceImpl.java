package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.LabReportDTO;
import lk.darkoinnovex.Ayu.entity.Hospital;
import lk.darkoinnovex.Ayu.entity.LabReport;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.HospitalRepository;
import lk.darkoinnovex.Ayu.repository.LabReportRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.LabReportService;
import lk.darkoinnovex.Ayu.util.PdfFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabReportServiceImpl implements LabReportService {

    @Autowired
    private LabReportRepository labReportRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<LabReportDTO> getAllLabReports() {
        List<LabReport> reports = labReportRepository.findAll();
        return reports.stream().map(labReport -> new LabReportDTO(
                labReport.getId(),
                labReport.getType(),
                labReport.getTimestamp(),
                new PdfFile(labReport.getFile()),
                labReport.getPatient().getId(),
                labReport.getHospital().getId(),
                labReport.getStatus()
        )).toList();
    }

    @Override
    public LabReportDTO createLabReport(LabReportDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId()).orElse(null);
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId()).orElse(null);

        if (patient != null && hospital != null) {
            try {
                LabReport labReport = new LabReport();
                labReport.setType(dto.getType());
                labReport.setFile(dto.getFile().getBytes());
                labReport.setPatient(patient);
                labReport.setHospital(hospital);

                LabReport savedLabReport = labReportRepository.save(labReport);
                return new LabReportDTO(
                        savedLabReport.getId(),
                        savedLabReport.getType(),
                        savedLabReport.getTimestamp(),
                        new PdfFile(labReport.getFile()),
                        savedLabReport.getPatient().getId(),
                        savedLabReport.getHospital().getId(),
                        labReport.getStatus()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LabReportDTO updateLabReport(LabReportDTO dto) {
        LabReport report = labReportRepository.findById(dto.getId()).orElse(null);

        if (report != null) {
            try {
                report.setFile(dto.getFile().getBytes());
                report.setStatus("Completed");

                LabReport savedLabReport = labReportRepository.save(report);

                if (savedLabReport != null) {
                    return new LabReportDTO();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LabReportDTO getLabReportById(Long id) {
        LabReport labReport = labReportRepository.findById(id).orElse(null);
        if (labReport != null) {
            return new LabReportDTO(
                    labReport.getId(),
                    labReport.getType(),
                    labReport.getTimestamp(),
                    new PdfFile(labReport.getFile()),
                    labReport.getPatient().getId(),
                    labReport.getHospital().getId(),
                    labReport.getStatus()
            );
        }
        return null;
    }

    @Override
    public List<LabReportDTO> getReportsByPatientId(Long id, Integer page, Integer count) {

        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {

            Pageable pageable = PageRequest.of(page, count);

            Page<LabReport> reports = labReportRepository.findByPatientId(patient, pageable);

            List<LabReportDTO> list = reports.getContent().stream().
                    map(labReport -> new LabReportDTO(
                                    labReport.getId(),
                                    labReport.getType(),
                                    labReport.getTimestamp(),
                                    new PdfFile(labReport.getFile()),
                                    labReport.getPatient().getId(),
                                    labReport.getHospital().getId(),
                            labReport.getStatus()
                            )
                    ).toList();

            if (!list.isEmpty()) {
                return list;
            }
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

    @Override
    public List<LabReportDTO> getAllLabReportsOfPatientOnDate(Long pId, LocalDate date, Integer page, Integer count) {
        Patient patient = patientRepository.findById(pId).orElse(null);

        if (patient != null) {

            Pageable pageable = PageRequest.of(page, count);

            Timestamp start = Timestamp.valueOf(date.atStartOfDay());
            Timestamp end = Timestamp.valueOf(date.atTime(LocalTime.MAX));

            Page<LabReport> labReports = labReportRepository.findLabReportsByPatientIdAndDateRange(
                    patient, start, end, pageable);

            List<LabReportDTO> dtos = labReports.getContent().stream()
                    .map(labReport -> new LabReportDTO(
                            labReport.getId(),
                            labReport.getType(),
                            labReport.getTimestamp(),
                            new PdfFile(labReport.getFile()),
                            labReport.getPatient().getId(),
                            labReport.getHospital().getId(),
                            labReport.getStatus()
                    )).collect(Collectors.toList());

            if (!dtos.isEmpty()) {
                return dtos;
            }
        }
        return null;
    }

    @Override
    public List<LabReportDTO> findLabReportsByPatientIdAndDateRange(Long pId, LocalDate startDate, LocalDate endDate, Integer page, Integer count) {
        Patient patient = patientRepository.findById(pId).orElse(null);

        if (patient != null) {

            Pageable pageable = PageRequest.of(page, count);

            Page<LabReport> labReports = labReportRepository.findLabReportsByPatientIdAndDateRange(
                    patient, Timestamp.valueOf(startDate.atStartOfDay()),
                    Timestamp.valueOf(endDate.atTime(LocalTime.MAX)), pageable);

            List<LabReportDTO> dtos = labReports.getContent().stream()
                    .map(labReport -> new LabReportDTO(
                            labReport.getId(),
                            labReport.getType(),
                            labReport.getTimestamp(),
                            new PdfFile(labReport.getFile()),
                            labReport.getPatient().getId(),
                            labReport.getHospital().getId(),
                            labReport.getStatus()
                    )).collect(Collectors.toList());

            if (!dtos.isEmpty()) {
                return dtos;
            }
        }
        return null;
    }

    @Override
    public List<LabReportDTO> getReportsByHospitalId(Long id, Integer page, Integer count) {

        Pageable pageable = PageRequest.of(page, count);

        Page<LabReport> reports = labReportRepository.findByHospitalId(id, pageable);

        List<LabReportDTO> list = reports.getContent().stream().
                map(labReport -> new LabReportDTO(
                                labReport.getId(),
                                labReport.getType(),
                                labReport.getTimestamp(),
                                new PdfFile(labReport.getFile()),
                                labReport.getPatient().getId(),
                                labReport.getHospital().getId(),
                                labReport.getStatus()
                        )
                ).toList();

        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public List<LabReportDTO> getLabReportsOfHospital(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElse(null);

        if (hospital != null) {
//            return labReportRepository.countLabReportOfHospital(hospital).orElse(null);
        }

        return null;
    }
}
