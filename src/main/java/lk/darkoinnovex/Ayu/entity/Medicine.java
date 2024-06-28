package lk.darkoinnovex.Ayu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false)
    private int dayCount;

    @Column(nullable = false)
    private String medicineName;

    private String medicineBrand;

    @Column(nullable = false)
    private double medicineWeight;

    @Column(nullable = false)
    private double dose;

    @Column(nullable = false)
    private String dosesPerDay;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MedicineBill medicineBill;
}
