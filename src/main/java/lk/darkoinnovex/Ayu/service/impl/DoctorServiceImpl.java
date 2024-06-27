package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import lk.darkoinnovex.Ayu.entity.Doctor;
import lk.darkoinnovex.Ayu.repository.*;
import lk.darkoinnovex.Ayu.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MedicalReportRepository medicalReportRepository;
    @Autowired
    private MedicineBillRepository medicineBillRepository;
    @Autowired
    private DoctorListRepository doctorListRepository;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS = doctors.stream().map(doctor -> new DoctorDTO(doctor.getId(), doctor.getNic(), doctor.getName(), doctor.getSpeciality(), doctor.getEmail(), doctor.getMobile(), doctor.getPassword(), doctor.getPhoto())).toList();
        return doctorDTOS;
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO dto) {

        Doctor doctor = new Doctor();
        doctor.setNic(dto.getNic());
        doctor.setName(dto.getName());
        doctor.setSpeciality(dto.getSpeciality());
        doctor.setEmail(dto.getEmail());
        doctor.setMobile(dto.getMobile());
        doctor.setPassword(dto.getPassword());
        doctor.setPhoto(dto.getPhoto());
        doctor.setAppointmentList(new ArrayList<>());
        doctor.setSchedules(new ArrayList<>());
        doctor.setMedicalReportList(new ArrayList<>());
        doctor.setMedicineBillList(new ArrayList<>());
        doctor.setDoctorList(new ArrayList<>());

        //should implement the logic to save new doctor to the hospital

        Doctor savedDoctor = doctorRepository.save(doctor);

        return new DoctorDTO(savedDoctor.getId(), savedDoctor.getNic(), savedDoctor.getName(), savedDoctor.getSpeciality(), savedDoctor.getEmail(), savedDoctor.getMobile(), savedDoctor.getPassword(), savedDoctor.getPhoto());
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO dto) {
        //should implement logic to update foreign keys
        return null;
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            return new DoctorDTO(doctor.getId(), doctor.getNic(), doctor.getName(), doctor.getSpeciality(), doctor.getEmail(), doctor.getMobile(), doctor.getPassword(), doctor.getPhoto());
        }
        return null;
    }
}
