package lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable;

import java.io.Serializable;
import java.util.UUID;

public record VegetableDto(UUID id,
                           String name,
                           double calories,
                           double proteins,
                           double fats,
                           double carbohydrates) implements Serializable {
}