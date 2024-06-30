package lk.darkoinnovex.Ayu.service.impl;

import lk.darkoinnovex.Ayu.dto.DoctorDTO;
import lk.darkoinnovex.Ayu.dto.SignInDTO;
import lk.darkoinnovex.Ayu.entity.Doctor;
import lk.darkoinnovex.Ayu.entity.DoctorList;
import lk.darkoinnovex.Ayu.entity.Hospital;
import lk.darkoinnovex.Ayu.entity.Patient;
import lk.darkoinnovex.Ayu.repository.*;
import lk.darkoinnovex.Ayu.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository PatientRepository;

    @Autowired
    private HospitalRepository HospitalRepository;

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

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS = doctors.stream().map(doctor -> new DoctorDTO(doctor.getId(), doctor.getNic(), doctor.getName(), doctor.getSpeciality(), doctor.getEmail(), doctor.getMobile(), doctor.getPassword(), doctor.getPhoto())).toList();
        return doctorDTOS;
    }

    @Override
    @Transactional
    public DoctorDTO createDoctor(DoctorDTO dto, Long id) {

        Hospital hospital = hospitalRepository.findById(id).orElse(null);

        if (hospital != null) {
            Doctor doctor = new Doctor();

            doctor.setNic(dto.getNic());
            doctor.setName(dto.getName());
            doctor.setSpeciality(dto.getSpeciality());
            doctor.setEmail(dto.getEmail());
            doctor.setMobile(dto.getMobile());
            doctor.setPassword(dto.getPassword());
            doctor.setPhoto(dto.getPhoto());

            Doctor savedDoctor = doctorRepository.save(doctor);

            if (savedDoctor != null) {

                DoctorList doctorList = new DoctorList();

                doctorList.setDoctor(savedDoctor);
                doctorList.setHospital(hospital);

                DoctorList saveDoctorList = doctorListRepository.save(doctorList);

                if (saveDoctorList != null) {
                    dto.setId(savedDoctor.getId());
                    return dto;
                }
            }
        }

        return null;
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO dto) {

        Doctor doctor = doctorRepository.findById(dto.getId()).orElse(null);

        if (doctor != null) {
            doctor.setMobile(dto.getMobile());
            doctor.setEmail(dto.getEmail());
            doctor.setName(dto.getName());
            doctor.setNic(dto.getNic());
            doctor.setPassword(dto.getPassword());
            doctor.setSpeciality(dto.getSpeciality());

            Doctor save = doctorRepository.save(doctor);

            if (save != null) {
                return dto;
            }
        }

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

    @Override
    public List<DoctorDTO> getDoctorsOfPatient(Long id) {

        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient != null) {
            List<Doctor> dotors = doctorRepository.findCompletedAppointmentDoctorsByPatientId(patient).orElse(null);

            if (dotors != null) {
                return dotors.stream().map(doctor -> new DoctorDTO(doctor.getId(), doctor.getNic(), doctor.getName(), doctor.getSpeciality(), doctor.getEmail(), doctor.getMobile(), doctor.getPassword(), doctor.getPhoto())).toList();
            }
        }

        return null;
    }

    @Override
    public DoctorDTO configDoctorSignIn(SignInDTO dto) {
        Doctor doctor = doctorRepository.findDoctorBySignInInfo(dto.getUsername(), dto.getPassword()).orElse(null);

        if (doctor != null) {
            return new DoctorDTO(doctor.getId(), doctor.getNic()
            , doctor.getName(), doctor.getSpeciality(), doctor.getEmail(), doctor.getMobile(), doctor.getPassword(), doctor.getPhoto());
        }

        return null;
    }
}
