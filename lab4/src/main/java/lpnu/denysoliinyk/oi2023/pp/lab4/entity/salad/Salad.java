package lpnu.denysoliinyk.oi2023.pp.lab4.entity.salad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.BaseEntity;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.vegetable.Vegetable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "salads", uniqueConstraints = {
		@UniqueConstraint(name = "uc_salad_name", columnNames = { "name" })
})
public class Salad extends BaseEntity {
	private String name;

	@ManyToMany
	@JoinTable(name = "salads_vegetables",
			joinColumns = @JoinColumn(name = "salad_id"),
			inverseJoinColumns = @JoinColumn(name = "vegetable_id"))
	private List<Vegetable> vegetables = new ArrayList<>();

	public double getCalories() {
		return vegetables.stream().map(Vegetable::getCalories).reduce(0D, Double::sum);
	}
}
