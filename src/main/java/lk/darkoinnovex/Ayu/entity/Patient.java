package lk.darkoinnovex.Ayu.entity;

import jakarta.persistence.*;
import lk.darkoinnovex.Ayu.dto.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String dob;

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

    public PatientDTO toDto() {

        String[] names = name.split(" ");

        return new PatientDTO(
                id,
                names[0],
                names[1],
                dob,
                nic,
                mobile,
                email,
                bloodType,
                password,
                healthCard.getPinNo(),
                hospital.getId()
        );
    }
}
