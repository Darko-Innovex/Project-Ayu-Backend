package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.MedicineBillDTO;
import lk.darkoinnovex.Ayu.dto.MedicineDTO;
import lk.darkoinnovex.Ayu.entity.Doctor;
import lk.darkoinnovex.Ayu.entity.MedicineBill;
import lk.darkoinnovex.Ayu.entity.Medicine;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.*;
import lk.darkoinnovex.Ayu.service.MedicineBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineBillServiceImpl implements MedicineBillService {

    @Autowired
    private MedicineBillRepository medicineBillRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    @Transactional
    public MedicineBillDTO saveMedicineBill(MedicineBillDTO medicineBillDTO) {

        Doctor doctor = doctorRepository.findById(medicineBillDTO.getDoctorId()).orElse(null);

        if (doctor != null) {

            MedicineBill medicineBill = new MedicineBill();

            medicineBill.setTimestamp(medicineBillDTO.getTimestamp());
            medicineBill.setDoctor(doctor);

            MedicineBill save = medicineBillRepository.save(medicineBill);

            if (save != null) {

                List<Medicine> medicines = medicineBillDTO.getMedicineList().stream()
                        .map(medicineDTO -> {
                            Medicine medicine = medicineDTO.toEntity();
                            medicine.setMedicineBill(save);
                            return medicine;
                        }).toList();

                boolean allSaved = true;

                for (Medicine medicine : medicines) {
                    try {
                        medicineRepository.save(medicine);
                    } catch (Exception e) {
                        allSaved = false;
                        break;
                    }
                }

                if (allSaved) {
                    medicineBillDTO.setId(save.getId());
                    return medicineBillDTO;
                }
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


            return new MedicineBillDTO(
                    medicineBill.getId(),
                    medicineBill.getTimestamp(),
                    medicineBill.getDoctor().getId(),
                    medicineBill.getAppointment().getId(),
                    getMedicineAsAList(medicineBill.getMedicine()));
        }
        return null;
    }

    @Override
    public List<MedicineBillDTO> getMedicineBills() {
        List<MedicineBill> all = medicineBillRepository.findAll();

        if (all != null) {

            return all.stream().map(medicineBill -> new MedicineBillDTO(
                    medicineBill.getId(),
                    medicineBill.getTimestamp(),
                    medicineBill.getDoctor().getId(),
                    medicineBill.getAppointment().getId(),
                    getMedicineAsAList(medicineBill.getMedicine())
            )).toList();
        }

        return null;
    }

    @Override
    public MedicineBillDTO findMedicineBillByAppointment(Long appointmentId) {

        MedicineBill medicineBill = medicineBillRepository.findMedicineBillOfAppointment(appointmentId).orElse(null);

        if (medicineBill != null) {
            return new MedicineBillDTO(
                    medicineBill.getId(),
                    medicineBill.getTimestamp(),
                    medicineBill.getDoctor().getId(),
                    medicineBill.getAppointment().getId(),
                    getMedicineAsAList(medicineBill.getMedicine())
            );
        }

        return null;
    }

    @Override
    public List<MedicineDTO> getCurrentMedicineListOfPatient(Long patientId) {
//        Patient patient = patientRepository.findById(patientId).orElse(null);
//
//        if (patient != null) {
//            List<Medicine> medicines = medicineBillRepository.getCurrentDrugListOfPatient(patient).orElse(Collections.emptyList());
//
//            List<MedicineDTO> filteredMedicines = medicines.stream()
//                    .filter(medicine -> {
//                        LocalDateTime endDate = medicine.getTimestamp().toLocalDateTime().plusDays(medicine.getDayCount());
//
//                        return endDate.isAfter(LocalDateTime.now());
//                    })
//                    .map(medicine -> new MedicineDTO(
//                            medicine.getId(),
//                            medicine.getTimestamp(),
//                            medicine.getDayCount(),
//                            medicine.getMedicineName(),
//                            medicine.getMedicineBrand(),
//                            medicine.getMedicineWeight(),
//                            medicine.getDose(),
//                            medicine.getDosesPerDay()
//                    ))
//                    .collect(Collectors.toList());
//
//            return filteredMedicines;
//        }

        return null;
    }

    @Override
    public MedicineDTO updateMedicineDayCount(Long id) {
        Medicine medicine = medicineRepository.findById(id).orElse(null);

        if (medicine != null) {

            int dayCount = calculateDaysBetween(medicine.getTimestamp());

            medicine.setDayCount(dayCount);

            Medicine save = medicineRepository.save(medicine);

            if (save != null) {
                return new MedicineDTO(
                        medicine.getId(),
                        medicine.getTimestamp(),
                        medicine.getDayCount(),
                        medicine.getMedicineName(),
                        medicine.getMedicineBrand(),
                        medicine.getMedicineWeight(),
                        medicine.getDose(),
                        medicine.getDosesPerDay());
            }
        }

        return null;
    }

    // Calculate day count between two days
    public int calculateDaysBetween(Timestamp timestamp) {
        LocalDate startLocalDate = convertToLocalDateViaInstant(timestamp);
        LocalDate today = LocalDate.now();

        return (int) ChronoUnit.DAYS.between(startLocalDate, today);
    }

    // Convert Timestamp to LocalDate
    public LocalDate convertToLocalDateViaInstant(Timestamp timestamp) {
        return timestamp.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public List<MedicineDTO> getMedicineAsAList(List<Medicine> medicineList) {
        List<MedicineDTO> list = medicineList.stream().map(medicine -> new MedicineDTO(
                medicine.getId(),
                medicine.getTimestamp(),
                medicine.getDayCount(),
                medicine.getMedicineName(),
                medicine.getMedicineBrand(),
                medicine.getMedicineWeight(),
                medicine.getDose(),
                medicine.getDosesPerDay()
        )).toList();

        return list;
    }

}
