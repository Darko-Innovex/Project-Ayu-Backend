package lk.darkoinnovex.Ayu.entity;

import jakarta.persistence.*;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, unique = true)
    private String nic;

    @Column(nullable = false)
    private int mobile;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String status = "Connected";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "health_card_id")
    private HealthCard healthCard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Hospital hospital;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<LabReport> labReports;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Allergy> allergyList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Notification> notifications;

    public PatientDTO toDto() {

        String[] names = name.split(" ");

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId(id);
        patientDTO.setFirstName(names[0]);
        patientDTO.setLastName(names[1]);
        patientDTO.setDob(dob);
        patientDTO.setNic(nic);
        patientDTO.setMobile(mobile);
        patientDTO.setEmail(email);
        patientDTO.setBloodType(bloodType);
        patientDTO.setPassword(password);

        if (this.hospital != null) {
            patientDTO.setHospitalId(hospital.getId());
        }

        if (this.healthCard != null) {
            patientDTO.setHealthCardPin(healthCard.getPinNo());
        }

        return patientDTO;
    }
}