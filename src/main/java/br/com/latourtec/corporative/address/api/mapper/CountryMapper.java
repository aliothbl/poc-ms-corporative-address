package br.com.latourtec.corporative.address.api.mapper;

import br.com.latourtec.corporative.address.api.dto.CountryRequest;
import br.com.latourtec.corporative.address.model.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
	
	CountryEntity from(CountryRequest request);
}
