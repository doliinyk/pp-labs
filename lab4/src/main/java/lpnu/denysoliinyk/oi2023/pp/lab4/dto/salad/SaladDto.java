package lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad;

import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableDto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record SaladDto(UUID id,
                       String name,
                       double calories,
                       List<VegetableDto> vegetables) implements Serializable {
}