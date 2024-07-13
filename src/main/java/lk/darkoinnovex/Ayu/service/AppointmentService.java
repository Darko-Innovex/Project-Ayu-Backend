package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAppointmentsByPatientId(Long id);
    Integer getCompletedAppointmentCountOfPatient(Long id);
    Integer getPendingAppointmentCountOfPatient(Long id);
}
