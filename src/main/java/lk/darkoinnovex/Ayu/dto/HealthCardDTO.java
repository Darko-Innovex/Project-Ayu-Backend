package lk.darkoinnovex.Ayu.dto;

import lk.darkoinnovex.Ayu.entity.HealthCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HealthCardDTO {

    private Long id;

    private Long pinNo;

    private String status;

    public HealthCard toEntity() {
        return new HealthCard(
                id,
                pinNo,
                status
        );
    }

}
