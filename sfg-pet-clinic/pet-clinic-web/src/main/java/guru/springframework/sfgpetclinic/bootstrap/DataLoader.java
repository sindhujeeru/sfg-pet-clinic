package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
	
	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialityService;
	private final VisitService visitService;


	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {

		int count = petTypeService.findAll().size();

		if(count == 0){
			loadData();
		}
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType saveDogType = petTypeService.save(dog);

		PetType cat = new PetType();
		dog.setName("Cat Brown");
		PetType saveCatType = petTypeService.save(cat);

		System.out.println("Loaded PetTypes......");

		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		Speciality savedRadiology = specialityService.save(radiology);

		Speciality surgery = new Speciality();
		surgery.setDescription("Surgery");
		Speciality savedSurgery = specialityService.save(surgery);

		Speciality dentistry = new Speciality();
		dentistry.setDescription("dentistry");
		Speciality savedDentistry = specialityService.save(dentistry);

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialities().add(savedRadiology);

		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Rohit");
		vet2.setLastName("claus");
		vet1.getSpecialities().add(savedDentistry);

		vetService.save(vet2);


		Vet vet3 = new Vet();
		vet3.setFirstName("stefan");
		vet3.setLastName("salv");
		vet3.getSpecialities().add(savedSurgery);

		vetService.save(vet3);
		System.out.println("Loaded vets......");


		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Wilson");
		owner1.setAddress("Calle corredera de san marcos");
		owner1.setCity("Madrid");
		owner1.setTelephone("9856421243");

		Pet MikesPet = new Pet();
		MikesPet.setPetType(saveDogType);
		MikesPet.setOwner(owner1);
		MikesPet.setBirthDate(LocalDate.now());
		MikesPet.setName("Dusky");
		owner1.getPets().add(MikesPet);

		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Jacson");
		owner2.setLastName("Alberto");
		owner2.setAddress("Calle Argulluso 45");
		owner2.setCity("Cadiz");
		owner2.setTelephone("995631843");

		Pet jacksPet = new Pet();
		jacksPet.setPetType(saveCatType);
		jacksPet.setOwner(owner2);
		jacksPet.setName("Milu");
		jacksPet.setBirthDate(LocalDate.now());
		owner2.getPets().add(jacksPet);

		ownerService.save(owner2);
		System.out.println("Loaded owners......");

		Visit catVisit = new Visit();
		catVisit.setPet(jacksPet);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Sneezy cat");
		visitService.save(catVisit);
		System.out.println("Loaded Visits.......");

	}

}
