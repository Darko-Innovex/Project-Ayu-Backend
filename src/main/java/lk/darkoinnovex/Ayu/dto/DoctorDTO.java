package lk.darkoinnovex.Ayu.dto;

import lk.darkoinnovex.Ayu.util.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO implements BaseDTO {
    private Long id;
    private String nic;
    private String name;
    private String speciality;
    private String email;
    private String mobile;
    private String password;
    private byte[] photo;

}
