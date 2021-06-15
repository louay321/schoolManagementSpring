package Louay.schoolManagement.service;

import Louay.schoolManagement.domain.Teacher;
import Louay.schoolManagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService (@Autowired TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    //Handle both ID types in the search (string and long)
    public Optional<Teacher> findById(String id){ return teacherRepository.findById(Long.parseLong(id));}
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> findAll(){ return teacherRepository.findAll();}
    //Also in deleting handle both id types
    @Transactional
    public void delete(String id) {
        Optional<Teacher> t = teacherRepository.findById(Long.parseLong(id));
        t.ifPresent(teacher -> teacherRepository.delete(teacher));
    }

    @Transactional
    public void delete(Long id){
        Optional<Teacher> t = teacherRepository.findById(id);
        t.ifPresent(teacher -> teacherRepository.delete(teacher));
    }

    @Transactional
    public Teacher save(Teacher t){
        return this.teacherRepository.save(t);
    }


}
