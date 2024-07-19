package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDTO {
    private Long id;

    private Timestamp date;

    private Timestamp inTime;

    private Timestamp outTime;

    private Long hospitalId;

    private Long doctorId;
}
