package lpnu.denysoliinyk.oi2023.pp.lab4.mapper.salad;

import lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad.SaladDto;
import lpnu.denysoliinyk.oi2023.pp.lab4.entity.salad.Salad;
import lpnu.denysoliinyk.oi2023.pp.lab4.mapper.vegetable.VegetableMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { VegetableMapper.class })
public interface SaladMapper {
	SaladDto toDto(Salad salad);
}
