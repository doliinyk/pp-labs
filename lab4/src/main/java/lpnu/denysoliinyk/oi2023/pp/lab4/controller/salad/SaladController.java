package lpnu.denysoliinyk.oi2023.pp.lab4.controller.salad;

import lombok.RequiredArgsConstructor;
import lpnu.denysoliinyk.oi2023.pp.lab4.controller.BaseController;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad.SaladCreationDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad.SaladDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.salad.SaladService;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.vegetable.VegetableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/salads")
@RequiredArgsConstructor
public class SaladController implements BaseController<SaladDto, SaladCreationDto> {
	private final SaladService saladService;
	private final VegetableService vegetableService;

	@Override
	@GetMapping
	public Page<SaladDto> getAll(@PageableDefault Pageable pageable) {
		return saladService.getAll(pageable);
	}

	@Override
	@PostMapping
	public SaladDto add(@RequestBody SaladCreationDto creationDto) {
		return saladService.add(creationDto);
	}

	@Override
	@GetMapping("/{id}")
	public SaladDto getById(@PathVariable UUID id) {
		return saladService.getById(id);
	}

	@Override
	@PatchMapping("/{id}")
	public SaladDto update(@PathVariable UUID id, @RequestBody SaladCreationDto creationDto) {
		return saladService.update(id, creationDto);
	}

	@Override
	@DeleteMapping("/{id}")
	public SaladDto delete(@PathVariable UUID id) {
		return saladService.delete(id);
	}

	@GetMapping("/{id}/vegetables")
	public Page<VegetableDto> getAllVegetablesBySaladIdAndRange(@PathVariable UUID id,
	                                                            @RequestParam(defaultValue = "0") double caloriesStart,
	                                                            @RequestParam(defaultValue = "" + Double.MAX_VALUE) double caloriesEnd,
	                                                            @PageableDefault Pageable pageable) {
		return vegetableService.getAllVegetablesBySaladIdAndRange(id, caloriesStart, caloriesEnd, pageable);
	}
}
