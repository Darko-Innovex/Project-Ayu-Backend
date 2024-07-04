package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.MedicalReportDTO;
import lk.darkoinnovex.Ayu.entity.Doctor;
import lk.darkoinnovex.Ayu.entity.MedicalReport;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.DoctorRepository;
import lk.darkoinnovex.Ayu.repository.MedicalReportRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.MedicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public List<MedicalReportDTO> getAllReports() {
        List<MedicalReport> reports = medicalReportRepository.findAll();
        return reports.stream().map(medicalReport -> new MedicalReportDTO(medicalReport.getId(), medicalReport.getTimestamp(), medicalReport.getFile(), medicalReport.getDoctor().getId())).toList();
    }

    @Override
    public MedicalReportDTO createReport(MedicalReportDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElse(null);
        if (doctor != null) {
            MedicalReport medicalReport = new MedicalReport();
            medicalReport.setTimestamp(dto.getTimestamp());
            medicalReport.setFile(dto.getFile());
            medicalReport.setDoctor(doctor);

            MedicalReport savedMedicalReport = medicalReportRepository.save(medicalReport);
            return new MedicalReportDTO(savedMedicalReport.getId(), savedMedicalReport.getTimestamp(), savedMedicalReport.getFile(), savedMedicalReport.getDoctor().getId());
        }
        return null;
    }

    @Override
    public MedicalReportDTO updateReport(MedicalReportDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElse(null);

        MedicalReport medicalReport = medicalReportRepository.findById(dto.getId()).orElse(null);
        if (doctor != null || medicalReport != null) {

            medicalReport.setTimestamp(dto.getTimestamp());
            medicalReport.setFile(dto.getFile());
            medicalReport.setDoctor(doctor);

            MedicalReport savedMedicalReport = medicalReportRepository.save(medicalReport);
            return new MedicalReportDTO(savedMedicalReport.getId(), savedMedicalReport.getTimestamp(), savedMedicalReport.getFile(), savedMedicalReport.getDoctor().getId());
        }

        return null;
    }

    @Override
    public MedicalReportDTO getReportById(Long id) {
        MedicalReport medicalReport = medicalReportRepository.findById(id).orElse(null);

        if (medicalReport != null) {
            return new MedicalReportDTO(medicalReport.getId(), medicalReport.getTimestamp(), medicalReport.getFile(), medicalReport.getDoctor().getId());
        }

        return null;
    }

    @Override
    public MedicalReportDTO getReportByAppoinmentId(Long id) {
        MedicalReport medicalReport = medicalReportRepository.findByAppoinmentId(id).orElse(null);
        if (medicalReport != null) {
            return new MedicalReportDTO(medicalReport.getId(), medicalReport.getTimestamp(), medicalReport.getFile(), medicalReport.getDoctor().getId());
        }
        return null;
    }

    @Override
    public List<MedicalReportDTO> getReportsByPatientId(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {
            List<MedicalReport> reports = medicalReportRepository.findByPatientId(patient);
            return reports.stream().map(medicalReport -> new MedicalReportDTO(medicalReport.getId(), medicalReport.getTimestamp(), medicalReport.getFile(), medicalReport.getDoctor().getId())).toList();
        }
        return null;
    }
}
