package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {

    private SpecialityRepository specialityRepository;

    public SpecialitySDJpaService(){

    }

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities= new HashSet<Speciality>();
        specialityRepository.findAll().forEach(specialities::add);
        return null;
    }

    @Override
    public Speciality findById(Long aLong) {
        Optional<Speciality> optionalSpeciality = Optional.ofNullable(specialityRepository.findById(aLong));
        if (optionalSpeciality.isPresent()){
            return optionalSpeciality.get();
        }else {
            return null;
        }
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
