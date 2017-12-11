package wad.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Uutinen extends AbstractPersistable<Long> {

    private String otsikko;
    private String kirjoittaja;
    private String sisalto;
    private String ingressi;
    @OneToOne
    private Gif gif;
    private LocalDateTime pvm;
    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> aihe;
    private int visits;

    

}
