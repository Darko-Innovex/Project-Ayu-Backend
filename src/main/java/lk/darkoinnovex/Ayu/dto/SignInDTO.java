package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInDTO {
    private Long patientId;
    private Long doctorId;
    private Long hospitalId;
    private Long adminId;
    private String type;
    private String username;
    private String password;
    private String token;
}
