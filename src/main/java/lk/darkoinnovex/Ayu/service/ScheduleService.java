package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    ScheduleDTO findScheduleById(Long id);
    ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO deleteSchedule(Long id);
    List<ScheduleDTO> getScheduleOfDoctorOnHospital(Long doctorId, Long hospitalId, Integer page, Integer count);
    List<ScheduleDTO> getScheduleOfHospital(Long hospitalId, Integer page, Integer count);
    ScheduleDTO updateScheduleStatus(Long scheduleId);
}
