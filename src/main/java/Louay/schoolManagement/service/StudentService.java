package Louay.schoolManagement.service;

import Louay.schoolManagement.domain.Student;
import Louay.schoolManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(@Autowired StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findById(String id) { return studentRepository.findById(Long.parseLong(id)); }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete(String id) {
        Optional<Student> s = studentRepository.findById(Long.parseLong(id));
        s.ifPresent(Student -> studentRepository.delete(Student));
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        Optional<Student> s = studentRepository.findById(id);
        s.ifPresent(Student -> studentRepository.delete(Student));
    }


    @org.springframework.transaction.annotation.Transactional
    public Student save(Student s) {
        return this.studentRepository.save(s);
    }
}
