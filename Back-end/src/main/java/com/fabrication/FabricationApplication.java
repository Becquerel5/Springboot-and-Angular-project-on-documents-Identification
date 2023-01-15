package com.fabrication;

import com.fabrication.agent.repositories.AdditionalDocumentsRepository;
import com.fabrication.agent.repositories.CniRepository;
import com.fabrication.agent.repositories.PassportRepository;
import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.agent.services.*;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.entities.*;
import com.fabrication.client.services.PersonService;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.Gender;
import com.fabrication.utils.PersonStatus;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
public class FabricationApplication {

	public static void main(String[] args) {
		//new File(FileResourceServiceImpl.uploadDirectory).mkdir();
		SpringApplication.run(FabricationApplication.class, args);
	}



	//private final String FOLDER_PATH="/Users/besoins/MyFIles/";

	private final String FOLDER_PATH="/Users/besoins/MyFIles/";

	@Autowired
	CniRepository cniRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonService personService;

	@Autowired
	FileResourceService fileResourceService;



	@Autowired
	PassportRepository passportRepository;

	@Autowired
	ReferenceDocumentRepository referencedocumentRepository;

	@Autowired
	ReferentDocumentService referentDocumentService;

	@Autowired
	private AdditionalDocumentsRepository additionalDocumentsRepository;

	@Bean
	CommandLineRunner start(ReferentDocumentService referentDocumentService,FileResourceService fileResourceService) {
		return args -> {

			/*personRepository.save(new Client(
					null,
					"dtfb@gmail.com",
					PersonStatus.ACTIVE,
					"123456",
					Date.from(Instant.now())
			));
			personRepository.save(new Client(
					null,
					"dt1fb@gmail.com",
					PersonStatus.ACTIVE,
					"123456",
					Date.from(Instant.now())
			));
			cniRepository.save(
					new Cni(
							null,
							"123",
							"123456789",
							"ELLA BELINGA",
							"ELLA BELINGA",
							new Date("05/16/1993 22:30"),
							Gender.MALE,
							"Developer",
							"Father Name",
							"Mother Name",
							null,
							null,
							"Mimboman-Shakespeare",
							null,
							personRepository.findClientById(1L),
							null,
							StatusTreatmentSystemeList.FORM,
							StatusInTreatment.Ready,
							null,
							null,
							null,
							null,
							null,
							null
					)
			);
			Passport passport = new Passport(
					null,
					null,
					"123456789",
					"DTFBPASS",
					"firstName",
					Date.from(Instant.now()),
					Gender.MALE,
					"profession",
					"nameOfFather",
					"nameOfMother",
					Date.from(Instant.now()),
					Date.from(Instant.now()),
					"address",
					null,
					personRepository.findClientById(2L),
					null,
					StatusTreatmentSystemeList.VALIDATE,
					StatusInTreatment.Waiting,
					null
			);
			passportRepository.save(passport);*/


			Agent agent = new Agent(
					null,
					"becquerel@gmail.com",
					PersonStatus.CREATE,
					"agentL",
					"agentF",
					"login",
					"123456"
			);
			personRepository.save(agent);
				//agent.setId(2L);

/*
			cniRepository.save(
					new Cni(
							null,
							"123",
							"123456789",
							"ELLA BELINGA",
							"ELLA BELINGA",
							new Date("05/16/1993 22:30"),
							Gender.MALE,
							"null",
							"null",
							"null",
							null,
							null,
							"Mimboman-Shakespeare",
							null,
							personRepository.findClientById(1L),
							null,
							StatusTreatmentSystemeList.FORM,
							StatusInTreatment.Ready,
							null,
							null,
							null,
							null,
							null,
							null
					)
			);


			Client client = new Client(
					null,
					"toto@d.cd",
					PersonStatus.CREATE,
					"123456",
					Date.from(Instant.now())
			);
			personRepository.save(client);
			//System.err.println(personRepository.findClientById(1L).getId());
			//client.setId(1L);

			Cni cni = new Cni(
					null,
					null,
					"123456789",
					"DTFBCNI",
					"firstName",
					Date.from(Instant.now()),
					Gender.MALE,
					"profession",
					"nameOfFather",
					"nameOfMother",
					Date.from(Instant.now()),
					Date.from(Instant.now()),
					"address",
					null,
					client,
					null,
					null,
					StatusInTreatment.Waiting,
					null
			);
			cniRepository.save(cni);

			 passport = new Passport(
					null,
					null,
					"123456789",
					"DTFBPASS",
					"firstName",
					Date.from(Instant.now()),
					Gender.MALE,
					"profession",
					"nameOfFather",
					"nameOfMother",
					Date.from(Instant.now()),
					Date.from(Instant.now()),
					"address",
					null,
					client,
					null,
					null,
					StatusInTreatment.Waiting,
					null
			);
			passportRepository.save(passport);
			//System.err.println(referencedocumentRepository.getReferencedocumentById(client.getId()).getClass());
			System.err.println(additionalDocumentsRepository.getReferencedocumentClientById(1L));
*/
		};
	}


}
