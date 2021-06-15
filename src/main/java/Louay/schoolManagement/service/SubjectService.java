package Louay.schoolManagement.service;

import Louay.schoolManagement.domain.Subject;
import Louay.schoolManagement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class SubjectService {
    private SubjectRepository subjectRepository;

    public SubjectService(@Autowired SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Optional<Subject> findById(String id) { return subjectRepository.findById(Long.parseLong(id));}
    public Optional<Subject> findById(Long id) { return subjectRepository.findById(id);}
    public List<Subject> findAll(){ return subjectRepository.findAll();}

    @Transactional
    public void delete(String id) {
        Optional<Subject> s = subjectRepository.findById(Long.parseLong(id));
        s.ifPresent(subject -> subjectRepository.delete(subject));
    }

    @Transactional
    public void delete(Long id) {
        Optional<Subject> s = subjectRepository.findById(id);
        s.ifPresent(subject -> subjectRepository.delete(subject));
    }

    @Transactional
    public Subject save(Subject vc) {
        return this.subjectRepository.save(vc);
    }


}
