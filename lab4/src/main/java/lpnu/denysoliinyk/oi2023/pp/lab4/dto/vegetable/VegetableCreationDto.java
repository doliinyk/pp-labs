package lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable;

import java.io.Serializable;

public record VegetableCreationDto(String name,
                                   Double calories,
                                   Double proteins,
                                   Double fats,
                                   Double carbohydrates) implements Serializable {
}