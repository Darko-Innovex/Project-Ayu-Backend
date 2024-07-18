package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.AppointmentDTO;
import lk.darkoinnovex.Ayu.entity.*;
import lk.darkoinnovex.Ayu.repository.*;
import lk.darkoinnovex.Ayu.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

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

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId()).orElse(null);
        Hospital hospital = hospitalRepository.findById(appointmentDTO.getHospitalId()).orElse(null);
        MedicalReport medicalReport = medicalReportRepository.findById(appointmentDTO.getMedicalReportId()).orElse(null);
        MedicineBill medicineBill = medicineBillRepository.findById(appointmentDTO.getMedicineBillId()).orElse(null);
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId()).orElse(null);
        Schedule schedule = scheduleRepository.findById(appointmentDTO.getScheduleId()).orElse(null);

        if (doctor != null || hospital != null || medicalReport != null || medicineBill != null || patient != null || schedule != null) {

            Appointment appointment = new Appointment();

            appointment.setAppointmentNo(appointmentDTO.getAppointmentNo());
            appointment.setTimestamp(appointmentDTO.getTimestamp());
            appointment.setDoctor(doctor);
            appointment.setHospital(hospital);
            appointment.setMedicalReport(medicalReport);
            appointment.setMedicineBill(medicineBill);
            appointment.setPatient(patient);
            appointment.setSchedule(schedule);

            Appointment savedAppointment = appointmentRepository.save(appointment);

            return new AppointmentDTO(
                    savedAppointment.getId(),
                    savedAppointment.getAppointmentNo(),
                    savedAppointment.getTimestamp(),
                    savedAppointment.getStatus(),
                    savedAppointment.getPatient().getId(),
                    savedAppointment.getDoctor().getId(),
                    savedAppointment.getHospital().getId(),
                    savedAppointment.getSchedule().getId(),
                    savedAppointment.getMedicalReport().getId(),
                    savedAppointment.getMedicineBill().getId()
            );
        }
        return null;
    }

    @Override
    public AppointmentDTO updateAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);

        if (appointment != null) {

            appointment.setStatus("Canceled");
            Appointment save = appointmentRepository.save(appointment);

            return new AppointmentDTO(
                    save.getId(),
                    save.getAppointmentNo(),
                    save.getTimestamp(),
                    save.getStatus(),
                    save.getPatient().getId(),
                    save.getDoctor().getId(),
                    save.getHospital().getId(),
                    save.getSchedule().getId(),
                    save.getMedicalReport().getId(),
                    save.getMedicineBill().getId()
            );

        }
        return null;
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            return new AppointmentDTO(
                    appointment.getId(),
                    appointment.getAppointmentNo(),
                    appointment.getTimestamp(),
                    appointment.getStatus(),
                    appointment.getDoctor().getId(),
                    appointment.getHospital().getId(),
                    appointment.getSchedule().getId(),
                    appointment.getMedicalReport().getId(),
                    appointment.getMedicineBill().getId(),
                    appointment.getPatient().getId());
        }
        return null;
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long id, Integer page, Integer count) {
        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {

            Pageable pageable = PageRequest.of(page, count);

            Page<Appointment> appointments = appointmentRepository.findByPatientId(patient, pageable);

            List<AppointmentDTO> dtos = appointments.getContent().stream()
                    .map(appointment -> new AppointmentDTO(
                            appointment.getId(),
                            appointment.getAppointmentNo(),
                            appointment.getTimestamp(),
                            appointment.getStatus(),
                            appointment.getPatient().getId(),
                            appointment.getDoctor().getId(),
                            appointment.getHospital().getId(),
                            appointment.getSchedule().getId(),
                            appointment.getMedicalReport().getId(),
                            appointment.getMedicineBill().getId()
                    )).collect(Collectors.toList());

            if (!dtos.isEmpty()) {
                return dtos;
            }
        }
        return null;
    }

    @Override
    public Integer getCompletedAppointmentCountOfPatient(Long id) {

        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {
            return appointmentRepository.countCompletedAppointments(patient).orElse(0);
        }

        return null;
    }

    @Override
    public Integer getPendingAppointmentCountOfPatient(Long id) {

        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {
            return appointmentRepository.countPendingAppointments(patient).orElse(0);
        }

        return null;
    }

    @Override
    public Integer getAppointmentCountOfPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {
            return appointmentRepository.countAppointments(patient).orElse(0);
        }

        return null;
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentOfPatientOnDate(Long pId, LocalDate date, Integer page, Integer count) {
        Patient patient = patientRepository.findById(pId).orElse(null);

        if (patient != null) {

            Pageable pageable = PageRequest.of(page, count);

            Timestamp start = Timestamp.valueOf(date.atStartOfDay());
            Timestamp end = Timestamp.valueOf(date.atTime(LocalTime.MAX));

            Page<Appointment> appointments = appointmentRepository.findAppointmentsByPatientIdAndDateRange(
                    patient, start, end, pageable);

            List<AppointmentDTO> dtos = appointments.getContent().stream()
                    .map(appointment -> new AppointmentDTO(
                            appointment.getId(),
                            appointment.getAppointmentNo(),
                            appointment.getTimestamp(),
                            appointment.getStatus(),
                            appointment.getPatient().getId(),
                            appointment.getDoctor().getId(),
                            appointment.getHospital().getId(),
                            appointment.getSchedule().getId(),
                            appointment.getMedicalReport().getId(),
                            appointment.getMedicineBill().getId()
                    )).collect(Collectors.toList());

            if (!dtos.isEmpty()) {
                return dtos;
            }
        }
        return null;
    }

    @Override
    public List<AppointmentDTO> findAppointmentsByPatientIdAndDateRange(Long pId, LocalDate startDate, LocalDate endDate, Integer page, Integer count) {
        Patient patient = patientRepository.findById(pId).orElse(null);

        if (patient != null) {

            Pageable pageable = PageRequest.of(page, count);

            Page<Appointment> appointments = appointmentRepository.findAppointmentsByPatientIdAndDateRange(
                    patient, Timestamp.valueOf(startDate.atStartOfDay()),
                    Timestamp.valueOf(endDate.atTime(LocalTime.MAX)), pageable);

            List<AppointmentDTO> dtos = appointments.getContent().stream()
                    .map(appointment -> new AppointmentDTO(
                            appointment.getId(),
                            appointment.getAppointmentNo(),
                            appointment.getTimestamp(),
                            appointment.getStatus(),
                            appointment.getPatient().getId(),
                            appointment.getDoctor().getId(),
                            appointment.getHospital().getId(),
                            appointment.getSchedule().getId(),
                            appointment.getMedicalReport().getId(),
                            appointment.getMedicineBill().getId()
                    )).collect(Collectors.toList());

            if (!dtos.isEmpty()) {
                return dtos;
            }
        }
        return null;
    }
}
