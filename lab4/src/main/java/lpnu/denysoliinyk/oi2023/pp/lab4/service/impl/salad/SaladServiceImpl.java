package lpnu.denysoliinyk.oi2023.pp.lab4.service.impl.salad;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad.SaladCreationDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad.SaladDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.salad.Salad;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.vegetable.Vegetable;
import lpnu.denysoliinyk.oi2023.pp.lab4.mapper.salad.SaladMapper;
import lpnu.denysoliinyk.oi2023.pp.lab4.repository.salad.SaladRepository;
import lpnu.denysoliinyk.oi2023.pp.lab4.repository.vegetable.VegetableRepository;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.salad.SaladService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaladServiceImpl implements SaladService {
	private final SaladRepository saladRepository;
	private final SaladMapper saladMapper;
	private final VegetableRepository vegetableRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<SaladDto> getAll(Pageable pageable) {
		final Page<Salad> salads = saladRepository.findAll(pageable);

		return salads.map(saladMapper::toDto);
	}

	@Override
	@Transactional
	public SaladDto add(SaladCreationDto creationDto) {
		Salad salad = dtoToEntity(creationDto);

		salad = saladRepository.save(salad);
		log.info("Salad with name " + salad.getName() + " and id " + salad.getId() + " was created");

		return saladMapper.toDto(salad);
	}

	@Override
	@Transactional(readOnly = true)
	public SaladDto getById(UUID id) {
		final Salad salad = getEntityById(id);

		return saladMapper.toDto(salad);
	}

	@Override
	@Transactional
	public SaladDto update(UUID id, SaladCreationDto creationDto) {
		Salad salad = getEntityById(id);

		partialDtoToEntity(creationDto, salad);
		log.info("Salad with name " + salad.getName() + " and id " + salad.getId() + " was updated");

		return saladMapper.toDto(salad);
	}

	@Override
	@Transactional
	public SaladDto delete(UUID id) {
		final Salad salad = getEntityById(id);

		saladRepository.delete(salad);
		log.info("Salad with name " + salad.getName() + " and id " + salad.getId() + " was deleted");

		return saladMapper.toDto(salad);
	}

	private Salad getEntityById(UUID id) {
		return saladRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	private Salad dtoToEntity(SaladCreationDto saladCreationDto) {
		final Salad salad = new Salad();

		setEntityName(saladCreationDto, salad);
		setEntityVegetables(saladCreationDto, salad);

		return salad;
	}

	private void partialDtoToEntity(SaladCreationDto saladCreationDto, Salad salad) {
		if (saladCreationDto == null) {
			return;
		}

		if (saladCreationDto.name() != null) {
			setEntityName(saladCreationDto, salad);
		}
		if (saladCreationDto.vegetableIds() != null) {
			setEntityVegetables(saladCreationDto, salad);
		}
	}

	private void setEntityName(SaladCreationDto saladCreationDto, Salad salad) {
		salad.setName(saladCreationDto.name());
	}

	private void setEntityVegetables(SaladCreationDto saladCreationDto, Salad salad) {
		final List<Vegetable> vegetables = new ArrayList<>();

		saladCreationDto.vegetableIds().forEach(id -> vegetables.add(vegetableRepository.findById(id).orElseThrow()));

		salad.setVegetables(vegetables);
	}
}
