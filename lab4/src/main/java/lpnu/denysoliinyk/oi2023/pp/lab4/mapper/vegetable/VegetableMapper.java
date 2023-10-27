package lpnu.denysoliinyk.oi2023.pp.lab4.mapper.vegetable;

import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableCreationDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.vegetable.VegetableDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.vegetable.Vegetable;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VegetableMapper {
	Vegetable toEntity(VegetableCreationDto vegetableCreationDto);

	VegetableDto toDto(Vegetable vegetable);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void partialUpdate(VegetableCreationDto vegetableCreationDto,
	                        @MappingTarget Vegetable vegetable);
}
