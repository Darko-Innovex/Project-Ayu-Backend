package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.MedicineBillDTO;
import lk.darkoinnovex.Ayu.dto.MedicineListDTO;
import lk.darkoinnovex.Ayu.entity.Doctor;
import lk.darkoinnovex.Ayu.entity.MedicineBill;
import lk.darkoinnovex.Ayu.entity.MedicineList;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.AppointmentRepository;
import lk.darkoinnovex.Ayu.repository.DoctorRepository;
import lk.darkoinnovex.Ayu.repository.MedicineBillRepository;
import lk.darkoinnovex.Ayu.repository.PatientRepository;
import lk.darkoinnovex.Ayu.service.MedicineBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineBillServiceImpl implements MedicineBillService {

    @Autowired
    private MedicineBillRepository medicineBillRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public MedicineBillDTO saveMedicineBill(MedicineBillDTO medicineBillDTO) {

        Doctor doctor = doctorRepository.findById(medicineBillDTO.getDoctorId()).orElse(null);

        if (doctor != null) {

            MedicineBill medicineBill = new MedicineBill();

            medicineBill.setTimestamp(medicineBillDTO.getTimestamp());
            medicineBill.setDoctor(doctor);

            MedicineBill save = medicineBillRepository.save(medicineBill);

            if (save != null) {

                medicineBillDTO.setId(save.getId());
                return medicineBillDTO;
            }
        }

        return null;

    }

    @Override
    public MedicineBillDTO updateMedicineBill(MedicineBillDTO medicineBillDTO) {

        MedicineBill medicineBill = medicineBillRepository.findById(medicineBillDTO.getId()).orElse(null);
        Doctor doctor = doctorRepository.findById(medicineBillDTO.getDoctorId()).orElse(null);

        if (medicineBill != null) {
            medicineBill.setTimestamp(medicineBillDTO.getTimestamp());
            medicineBill.setDoctor(doctor);

            MedicineBill save = medicineBillRepository.save(medicineBill);

            if (save != null) {

                return medicineBillDTO;
            }
        }

        return null;
    }

    @Override
    public MedicineBillDTO getMedicineBill(Long medicineBillId) {
        MedicineBill medicineBill = medicineBillRepository.findById(medicineBillId).orElse(null);

        if (medicineBill != null) {
            return new MedicineBillDTO(medicineBill.getId(), medicineBill.getDoctor().getId(), medicineBill.getTimestamp());
        }
        return null;
    }

    @Override
    public List<MedicineBillDTO> getMedicineBills() {
        List<MedicineBill> all = medicineBillRepository.findAll();

        if (all != null) {

            return all.stream().map(medicineBill -> new MedicineBillDTO(medicineBill.getId(), medicineBill.getDoctor().getId(), medicineBill.getTimestamp())).toList();
        }

        return null;
    }

    @Override
    public MedicineBillDTO findMedicineBillByAppointment(Long appointmentId) {

        MedicineBill medicineBill = medicineBillRepository.findMedicineBillOfAppointment(appointmentId).orElse(null);

        if (medicineBill != null) {
            return new MedicineBillDTO(medicineBill.getId(), medicineBill.getDoctor().getId(), medicineBill.getTimestamp());
        }

        return null;
    }

    @Override
    public List<MedicineListDTO> getCurrentMedicineListOfPatient(Long patientId) {

        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (patient != null) {

            List<MedicineList> medicineLists = medicineBillRepository.getCurrentDrugListOfPatient(patient).orElse(null);

            if (medicineLists != null) {

                return medicineLists.stream().map(medicineList -> new MedicineListDTO(medicineList.getId(), medicineList.getTimestamp(), medicineList.getDayCount(), medicineList.getMedicineName(), medicineList.getMedicineBrand(), medicineList.getMedicineWeight(), medicineList.getDose(), medicineList.getDosesPerDay())).toList();
            }
        }

        return null;
    }
}
