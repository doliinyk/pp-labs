package lpnu.denysoliinyk.oi2023.pp.lab4.service.vegetable;

import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableCreationDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VegetableService extends BaseService<VegetableDto, VegetableCreationDto> {
	Page<VegetableDto> getAllVegetablesBySaladIdAndRange(UUID id, double caloriesStart, double caloriesEnd, Pageable pageable);
}
