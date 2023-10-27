package lpnu.denysoliinyk.oi2023.pp.lab4.service.impl.vegetable;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableCreationDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.vegetable.Vegetable;
import lpnu.denysoliinyk.oi2023.pp.lab4.mapper.vegetable.VegetableMapper;
import lpnu.denysoliinyk.oi2023.pp.lab4.repository.vegetable.VegetableRepository;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.vegetable.VegetableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class VegetableServiceImpl implements VegetableService {
	private final VegetableRepository vegetableRepository;
	private final VegetableMapper vegetableMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<VegetableDto> getAll(Pageable pageable) {
		final Page<Vegetable> vegetables = vegetableRepository.findAll(pageable);

		return vegetables.map(vegetableMapper::toDto);
	}

	@Override
	@Transactional
	public VegetableDto add(VegetableCreationDto creationDto) {
		Vegetable vegetable = vegetableMapper.toEntity(creationDto);

		vegetable = vegetableRepository.save(vegetable);
		log.info("Vegetable with name " + vegetable.getName() + " and id " + vegetable.getId() + " was created");

		return vegetableMapper.toDto(vegetable);
	}

	@Override
	@Transactional(readOnly = true)
	public VegetableDto getById(UUID id) {
		final Vegetable vegetable = getEntityById(id);

		return vegetableMapper.toDto(vegetable);
	}

	@Override
	@Transactional
	public VegetableDto update(UUID id, VegetableCreationDto creationDto) {
		Vegetable vegetable = getEntityById(id);

		vegetableMapper.partialUpdate(creationDto, vegetable);
		log.info("Vegetable with name " + vegetable.getName() + " and id " + vegetable.getId() + " was updated");

		return vegetableMapper.toDto(vegetable);
	}

	@Override
	@Transactional
	public VegetableDto delete(UUID id) {
		final Vegetable vegetable = getEntityById(id);

		vegetableRepository.delete(vegetable);
		log.info("Vegetable with name " + vegetable.getName() + " and id " + vegetable.getId() + " was deleted");

		return vegetableMapper.toDto(vegetable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<VegetableDto> getAllVegetablesBySaladIdAndRange(UUID id, double caloriesStart, double caloriesEnd, Pageable pageable) {
		Page<Vegetable> vegetables = vegetableRepository.findDistinctAllBySalads_IdAndCaloriesBetween(id, caloriesStart, caloriesEnd, pageable);

		return vegetables.map(vegetableMapper::toDto);
	}

	private Vegetable getEntityById(UUID id) {
		return vegetableRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
