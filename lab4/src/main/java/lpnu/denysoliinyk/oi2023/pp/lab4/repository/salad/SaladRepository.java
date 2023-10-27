package lpnu.denysoliinyk.oi2023.pp.lab4.repository.salad;

import lpnu.denysoliinyk.oi2023.pp.lab4.entity.salad.Salad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaladRepository extends JpaRepository<Salad, UUID> {
}
