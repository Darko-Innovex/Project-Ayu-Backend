package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO updateAppointment(Long id);
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAppointmentsByPatientId(Long id, Integer page, Integer count);
    Integer getCompletedAppointmentCountOfPatient(Long id);
    Integer getPendingAppointmentCountOfPatient(Long id);
    Integer getAppointmentCountOfPatient(Long id);
    List<AppointmentDTO> getAllAppointmentOfPatientOnDate(Long pId, LocalDate date, Integer page, Integer count);
    List<AppointmentDTO> findAppointmentsByPatientIdAndDateRange(Long pId, LocalDate startDate, LocalDate endDate, Integer page, Integer count);
    List<AppointmentDTO> getAppointmentOfHospital(Long id, Integer page, Integer count);
    Integer getCompletedAppointmentCountOfHospital(Long id);
    Integer getPendingAppointmentCountOfHospital(Long id);
    List<Integer> getAppointmentCountOfHospital(Long id);
}
