package Louay.schoolManagement.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//Inheritance Type here makes join not required

public class Person extends AbstractEntity<Long> {

    @Column(name = "person_id", unique = true, nullable = false)
    private String personID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "person_address", nullable = false)
    private String personAddress;



}
