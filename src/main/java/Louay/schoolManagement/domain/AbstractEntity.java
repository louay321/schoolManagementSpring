package Louay.schoolManagement.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@MappedSuperclass

public class AbstractEntity <ID extends Serializable> implements Serializable {


@Id
@Column(name = "id", updatable = false, nullable = false)
@GeneratedValue(strategy = GenerationType.AUTO)
private ID id;

@CreationTimestamp
@JsonIgnore
private LocalDateTime created;

@UpdateTimestamp
@JsonIgnore
private LocalDateTime updated;

        }
