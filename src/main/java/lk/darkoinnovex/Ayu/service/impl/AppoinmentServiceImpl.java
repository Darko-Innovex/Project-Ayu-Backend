package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.AppointmentDTO;
import lk.darkoinnovex.Ayu.entity.*;
import lk.darkoinnovex.Ayu.repository.*;
import lk.darkoinnovex.Ayu.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppoinmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    @Autowired
    private MedicineBillRepository medicineBillRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDTO> dtos = appointments.stream().map(appointment -> new AppointmentDTO(appointment.getId(), appointment.getAppointmentNo(), appointment.getTimestamp(), appointment.getDoctor().getId(), appointment.getHospital().getId(), appointment.getMedicalReport().getId(), appointment.getMedicineBill().getId(), appointment.getPatient().getId())).toList();
        return dtos;
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId()).orElse(null);
        Hospital hospital = hospitalRepository.findById(appointmentDTO.getHospitalId()).orElse(null);
        MedicalReport medicalReport = medicalReportRepository.findById(appointmentDTO.getMedicalReportId()).orElse(null);
        MedicineBill medicineBill = medicineBillRepository.findById(appointmentDTO.getMedicineBillId()).orElse(null);
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId()).orElse(null);

        if (doctor != null || hospital != null || medicalReport != null || medicineBill != null || patient != null) {
            Appointment appointment = new Appointment();
            appointment.setAppointmentNo(appointmentDTO.getAppointmentNo());
            appointment.setTimestamp(appointmentDTO.getTimestamp());
            appointment.setDoctor(doctor);
            appointment.setHospital(hospital);
            appointment.setMedicalReport(medicalReport);
            appointment.setMedicineBill(medicineBill);
            appointment.setPatient(patient);
            Appointment savedAppointment = appointmentRepository.save(appointment);
            return new AppointmentDTO(savedAppointment.getId(), savedAppointment.getAppointmentNo(), savedAppointment.getTimestamp(), savedAppointment.getDoctor().getId(), savedAppointment.getHospital().getId(), savedAppointment.getMedicalReport().getId(), savedAppointment.getMedicineBill().getId(), savedAppointment.getPatient().getId());
        }
        return null;
    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(appointmentDTO.getId()).orElse(null);

        if (appointment != null) {
            Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId()).orElse(null);
            Hospital hospital = hospitalRepository.findById(appointmentDTO.getHospitalId()).orElse(null);
            MedicalReport medicalReport = medicalReportRepository.findById(appointmentDTO.getMedicalReportId()).orElse(null);
            MedicineBill medicineBill = medicineBillRepository.findById(appointmentDTO.getMedicineBillId()).orElse(null);
            Patient patient = patientRepository.findById(appointmentDTO.getPatientId()).orElse(null);

            if (doctor != null || hospital != null || medicalReport != null || medicineBill != null || patient != null) {
                appointment.setAppointmentNo(appointmentDTO.getAppointmentNo());
                appointment.setTimestamp(appointmentDTO.getTimestamp());
                appointment.setDoctor(doctor);
                appointment.setHospital(hospital);
                appointment.setMedicalReport(medicalReport);
                appointment.setMedicineBill(medicineBill);
                appointment.setPatient(patient);
                Appointment savedAppointment = appointmentRepository.save(appointment);
                return new AppointmentDTO(savedAppointment.getId(), savedAppointment.getAppointmentNo(), savedAppointment.getTimestamp(), savedAppointment.getDoctor().getId(), savedAppointment.getHospital().getId(), savedAppointment.getMedicalReport().getId(), savedAppointment.getMedicineBill().getId(), savedAppointment.getPatient().getId());
            }
        }
        return null;
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            return new AppointmentDTO(appointment.getId(), appointment.getAppointmentNo(), appointment.getTimestamp(), appointment.getDoctor().getId(), appointment.getHospital().getId(), appointment.getMedicalReport().getId(), appointment.getMedicineBill().getId(), appointment.getPatient().getId());
        }
        return null;
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient != null) {
            List<Appointment> appointments = appointmentRepository.findByPatientId(patient).orElse(null);
            List<AppointmentDTO> dtos = appointments.stream().map(appointment -> new AppointmentDTO(appointment.getId(), appointment.getAppointmentNo(), appointment.getTimestamp(), appointment.getDoctor().getId(), appointment.getHospital().getId(), appointment.getMedicalReport().getId(), appointment.getMedicineBill().getId(), appointment.getPatient().getId())).toList();
        }
        return null;
    }
}
