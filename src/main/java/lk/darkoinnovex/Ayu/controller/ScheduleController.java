package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.ScheduleDTO;
import lk.darkoinnovex.Ayu.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/hospital/{hId}/doctor/{dId}/schedule")
    public ResponseEntity<List<ScheduleDTO>> getScheduleOfDoctorOnHospital(
            @PathVariable Long hId, @PathVariable Long dId,
            @RequestParam("page") Integer page, @RequestParam("count") Integer count) {
        List<ScheduleDTO> dtos = scheduleService.getScheduleOfDoctorOnHospital(dId, hId, page, count);

        if (dtos != null) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/hospital/{hId}/schedule")
    public ResponseEntity<List<ScheduleDTO>> getScheduleOfHospital(
            @PathVariable Long hId, @RequestParam("page") Integer page, @RequestParam("count") Integer count) {

        List<ScheduleDTO> dtos = scheduleService.getScheduleOfHospital(hId, page, count);

        if (dtos != null) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {

        ScheduleDTO schedule = scheduleService.findScheduleById(id);

        if (schedule != null) {
            return ResponseEntity.ok().body(schedule);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        ScheduleDTO dto = scheduleService.saveSchedule(scheduleDTO);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Schedule created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/doctor/{dId}/schedule")
    public ResponseEntity<List<ScheduleDTO>> getScheduleOfDoctorOnHospital(@PathVariable("dId") Long dId) {
        List<ScheduleDTO> list = scheduleService.findByHospitalAndDoctorOnCurrentDate(dId);

        if (list != null) {
            return ResponseEntity.ok().body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
