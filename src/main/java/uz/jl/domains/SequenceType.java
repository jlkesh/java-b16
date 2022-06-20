package uz.jl.domains;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Getter
@Setter
@Entity
@Table(name = "seq_type_2")
public class SequenceType {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "seq_Type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")

    long id;

    private String field;
}
