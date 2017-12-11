package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Uutinen;

public interface UutisRepository extends JpaRepository<Uutinen, Long> {

    List<Uutinen> findByAihe(String aihe);
    
}
