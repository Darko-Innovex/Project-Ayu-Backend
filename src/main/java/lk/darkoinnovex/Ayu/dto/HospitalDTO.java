package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HospitalDTO {
    private Long id;
    private String name;
    private String email;
    private int mobile;
    private String location;
    private String password;
    private String status ;
}
