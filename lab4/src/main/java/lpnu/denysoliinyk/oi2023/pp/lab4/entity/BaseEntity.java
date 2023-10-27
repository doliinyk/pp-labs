package lpnu.denysoliinyk.oi2023.pp.lab4.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	protected UUID id;
}
