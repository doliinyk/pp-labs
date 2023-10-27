package lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record SaladCreationDto(String name, List<UUID> vegetableIds) implements Serializable {
}