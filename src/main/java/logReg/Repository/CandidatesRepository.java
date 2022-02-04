package logReg.Repository;

import logReg.model.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatesRepository extends JpaRepository<Candidates, Long> {
   <Optional> Candidates findByUsername(String username);
}
