package cl.titicoctel.login.repository;

import cl.titicoctel.login.model.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Id> {
    Blacklist save (Blacklist blacklist);
}
