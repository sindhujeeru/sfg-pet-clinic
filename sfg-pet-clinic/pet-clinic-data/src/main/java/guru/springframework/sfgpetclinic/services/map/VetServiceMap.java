package guru.springframework.sfgpetclinic.services.map;


import java.util.Set;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;

@Service
@Profile({"default","map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialityService specialityService;

	public VetServiceMap(SpecialityService specialityService) {
		this.specialityService = specialityService;
	}

	@Override
	public Set<Vet> findAll() {
		
		return super.findAll();
	}

	@Override
	public Vet save(Vet object) {

		if(object.getSpecialities().size()>0){
			object.getSpecialities().forEach(spec->{
				if(spec.getId() == null){
					Speciality savedSpeciality = specialityService.save(spec);
					spec.setId(savedSpeciality.getId());
				}
			});
		}
		
		return super.save(object);
	}

	@Override
	public Vet findById(Long id) {
		
		return super.findById(id);
	}

	@Override
	public void delete(Vet object) {
		
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		
		super.deleteById(id);
	}

}
