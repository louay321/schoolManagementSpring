package Louay.schoolManagement.repository;

import Louay.schoolManagement.domain.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository extends JpaRepository<AbstractEntity, Long> {
}
