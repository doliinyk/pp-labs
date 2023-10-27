package lpnu.denysoliinyk.oi2023.pp.lab4.entity.vegetable;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.BaseEntity;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.salad.Salad;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vegetables", uniqueConstraints = {
		@UniqueConstraint(name = "uc_vegetable_name", columnNames = { "name" })
})
public class Vegetable extends BaseEntity {
	private String name;
	private double calories;
	private double proteins;
	private double fats;
	private double carbohydrates;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@ManyToMany(mappedBy = "vegetables", fetch = FetchType.LAZY)
	private Set<Salad> salads = new LinkedHashSet<>();
}
