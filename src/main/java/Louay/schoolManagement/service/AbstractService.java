package Louay.schoolManagement.service;


import Louay.schoolManagement.domain.AbstractEntity;
import Louay.schoolManagement.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbstractService {

    private AbstractRepository abstractRepository;

    public AbstractService (@Autowired AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    public Optional<AbstractEntity> findById(Long id) {
        return abstractRepository.findById(id);
    }

}