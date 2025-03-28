package lk.darkoinnovex.Ayu.dto;

import lk.darkoinnovex.Ayu.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO  {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String nic;
    private int mobile;
    private String email;
    private String bloodType;
    private String password;
    private Long healthCardPin;
    private Long hospitalId;

    public Patient toEntity() {
        Patient patient = new Patient();

        patient.setId(id);
        patient.setName(firstName + " " + lastName);
        patient.setDob(dob);
        patient.setNic(nic);
        patient.setMobile(mobile);
        patient.setEmail(email);
        patient.setBloodType(bloodType);
        patient.setPassword(password);

        return patient;
    }
}
