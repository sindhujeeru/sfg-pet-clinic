package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    private PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(){

    }

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<PetType>();
        petTypeRepository.findAll().iterator().forEachRemaining(petTypes::add);

        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        Optional<PetType> optionalPetType = Optional.ofNullable(petTypeRepository.findById(aLong));

        if(optionalPetType.isPresent()){
            return optionalPetType.get();
        }else{
            return null;
        }
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}
