package lpnu.denysoliinyk.oi2023.pp.lab4.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * @param <T> Dto
 * @param <R> CreationDto
 */
public interface BaseController<T, R> {
	Page<T> getAll(Pageable pageable);

	T add(R creationDto);

	T getById(UUID id);

	T update(UUID id, R creationDto);

	T delete(UUID id);
}
