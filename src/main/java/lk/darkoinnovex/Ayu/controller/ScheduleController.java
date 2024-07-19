package lk.darkoinnovex.Ayu.controller;

import lk.darkoinnovex.Ayu.dto.ScheduleDTO;
import lk.darkoinnovex.Ayu.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // TODO: get schedule of a specific doctor in a specific hospital on current date and onward
    @GetMapping("/hospital/{hId}/doctor/{dId}/schedule")
    public ResponseEntity<List<ScheduleDTO>> getScheduleOfDoctorOnHospital(@PathVariable Long hId, @PathVariable Long dId) {
        List<ScheduleDTO> dtos = scheduleService.getScheduleOfDoctorOnHospital(dId, hId);

        if (dtos != null) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
