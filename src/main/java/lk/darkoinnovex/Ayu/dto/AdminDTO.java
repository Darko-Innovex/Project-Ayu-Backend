package lk.darkoinnovex.Ayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminDTO {
    private Long id;
    private String nic;
    private String name;
    private String email;
    private int mobile;
    private String password;
    private String status ;
    private byte[] photo;
}
