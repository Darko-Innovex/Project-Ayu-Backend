package lk.darkoinnovex.Ayu.dto;

import lk.darkoinnovex.Ayu.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String dob;
    private String nic;
    private int mobile;
    private String email;
    private String bloodType;
    private String password;
    private Long healthCardPin;
    private Long hospitalId;
}
