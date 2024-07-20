package lk.darkoinnovex.Ayu.entity;

import jakarta.persistence.*;
import lk.darkoinnovex.Ayu.dto.HealthCardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long pinNo;

    @Column(nullable = false)
    private short password;

    @Column(nullable = false)
    private String status = "Pending";

    public HealthCardDTO toDto() {
        return new HealthCardDTO(
                id,
                pinNo,
                password,
                status
        );
    }
}
