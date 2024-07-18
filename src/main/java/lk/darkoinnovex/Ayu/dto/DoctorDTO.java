package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private Long id;
    private String nic;
    private String name;
    private String speciality;
    private String email;
    private String mobile;
    private byte[] photo;

}
