package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.ScheduleDTO;
import lk.darkoinnovex.Ayu.entity.Doctor;
import lk.darkoinnovex.Ayu.entity.Hospital;
import lk.darkoinnovex.Ayu.entity.Schedule;
import lk.darkoinnovex.Ayu.repository.DoctorRepository;
import lk.darkoinnovex.Ayu.repository.HospitalRepository;
import lk.darkoinnovex.Ayu.repository.ScheduleRepository;
import lk.darkoinnovex.Ayu.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public ScheduleDTO findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);

        if (schedule != null) {
            ScheduleDTO dto = new ScheduleDTO();

            dto.setId(schedule.getId());
            dto.setDate(schedule.getDate());
            dto.setInTime(schedule.getInTime());
            dto.setOutTime(schedule.getOutTime());
            dto.setHospitalId(schedule.getHospital().getId());
            dto.setDoctorId(schedule.getDoctor().getId());

            return dto;
        }

        return null;
    }

    @Override
    public ScheduleDTO saveSchedule(ScheduleDTO dto) {
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId()).orElse(null);
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElse(null);

        if (hospital != null && doctor != null) {
            Schedule schedule = new Schedule();

            schedule.setDate(dto.getDate());
            schedule.setInTime(dto.getInTime());
            schedule.setOutTime(dto.getOutTime());
            schedule.setHospital(hospital);
            schedule.setDoctor(doctor);

            Schedule save = scheduleRepository.save(schedule);

            if (save != null) {
                dto.setId(save.getId());
                return dto;
            }
        }

        return null;
    }

    @Override
    public ScheduleDTO updateSchedule(ScheduleDTO dto) {
        Schedule schedule = scheduleRepository.findById(dto.getId()).orElse(null);

        if (schedule != null) {
            schedule.setInTime(dto.getInTime());
            schedule.setOutTime(dto.getOutTime());

            Schedule save = scheduleRepository.save(schedule);

            if (save != null) {
                return dto;
            }
        }

        return null;
    }

    @Override
    public ScheduleDTO deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);

        if (schedule != null) {
            scheduleRepository.delete(schedule);
            return new ScheduleDTO();
        }

        return null;
    }

    @Override
    public List<ScheduleDTO> getScheduleOfDoctorOnHospital(Long doctorId, Long hospitalId) {
        List<Schedule> schedules = scheduleRepository.getScheduleOfDoctorOnHospital(doctorId, hospitalId).orElse(null);

        if (schedules != null) {
            return schedules.stream().map(schedule -> new ScheduleDTO(
                    schedule.getId(),
                    schedule.getDate(),
                    schedule.getInTime(),
                    schedule.getOutTime(),
                    schedule.getHospital().getId(),
                    schedule.getDoctor().getId()
            )).toList();
        }

        return null;
    }
}
