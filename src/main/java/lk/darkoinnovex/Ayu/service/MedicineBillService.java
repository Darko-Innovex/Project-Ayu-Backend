package lk.darkoinnovex.Ayu.service;

import lk.darkoinnovex.Ayu.dto.MedicineBillDTO;
import lk.darkoinnovex.Ayu.dto.MedicineListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicineBillService {
    MedicineBillDTO saveMedicineBill(MedicineBillDTO medicineBillDTO);

    MedicineBillDTO updateMedicineBill(MedicineBillDTO medicineBillDTO);

    MedicineBillDTO getMedicineBill(Long medicineBillId);

    List<MedicineBillDTO> getMedicineBills();

    MedicineBillDTO findMedicineBillByAppointment(Long appointmentId);

    List<MedicineListDTO> getCurrentMedicineListOfPatient(Long patientId);
}
