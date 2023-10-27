package lpnu.denysoliinyk.oi2023.pp.lab4.controller.vegetable;

import lombok.RequiredArgsConstructor;
import lpnu.denysoliinyk.oi2023.pp.lab4.controller.BaseController;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableCreationDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.vegetable.VegetableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vegetables")
@RequiredArgsConstructor
public class VegetableController implements BaseController<VegetableDto, VegetableCreationDto> {
	private final VegetableService vegetableService;

	@Override
	@GetMapping
	public Page<VegetableDto> getAll(@PageableDefault Pageable pageable) {
		return vegetableService.getAll(pageable);
	}

	@Override
	@PostMapping
	public VegetableDto add(@RequestBody VegetableCreationDto creationDto) {
		return vegetableService.add(creationDto);
	}

	@Override
	@GetMapping("/{id}")
	public VegetableDto getById(@PathVariable UUID id) {
		return vegetableService.getById(id);
	}

	@Override
	@PatchMapping("/{id}")
	public VegetableDto update(@PathVariable UUID id, @RequestBody VegetableCreationDto creationDto) {
		return vegetableService.update(id, creationDto);
	}

	@Override
	@DeleteMapping("/{id}")
	public VegetableDto delete(@PathVariable UUID id) {
		return vegetableService.delete(id);
	}
}
