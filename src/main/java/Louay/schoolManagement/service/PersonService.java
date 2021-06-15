package Louay.schoolManagement.service;

import Louay.schoolManagement.domain.Person;
import Louay.schoolManagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class PersonService {
    PersonRepository personRepository;

    public PersonService(@Autowired PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(String id){return personRepository.findById(Long.parseLong(id)); }

    public Optional<Person> findById(Long id){return personRepository.findById(id); }

    public List<Person> findAll(){ return personRepository.findAll();}

    @Transactional
    public void delete(String id) {
        Optional<Person> p = personRepository.findById(Long.parseLong(id));
        p.ifPresent(person -> personRepository.delete(person));
    }

    @Transactional
    public void delete(Long id) {
        Optional<Person> p = personRepository.findById(id);
        p.ifPresent(person -> personRepository.delete(person));
    }

    @Transactional
    public Person save(Person p) {
        return this.personRepository.save(p);
    }


}
