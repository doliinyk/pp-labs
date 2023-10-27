package lpnu.denysoliinyk.oi2023.pp.lab4.repository.vegetable;

import lpnu.denysoliinyk.oi2023.pp.lab4.entity.vegetable.Vegetable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, UUID> {
	Page<Vegetable> findDistinctAllBySalads_IdAndCaloriesBetween(UUID id, double caloriesStart, double caloriesEnd, Pageable pageable);
}
