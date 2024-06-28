package lk.darkoinnovex.Ayu.entity;

import jakarta.persistence.*;
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
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nic;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String speciality;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private int mobile;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private byte[] photo;

    @Column(nullable = false)
    private String status = "Connected";

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Appointment> appointmentList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Schedule> schedules;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<MedicalReport> medicalReportList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<MedicineBill> medicineBillList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<DoctorList> doctorList;
}
