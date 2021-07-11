package br.com.latourtec.corporative.address.api.assember;

import br.com.latourtec.corporative.address.api.controller.CountryController;
import br.com.latourtec.corporative.address.api.dto.CountryResponse;
import br.com.latourtec.corporative.address.api.mapper.CountryMapper;
import br.com.latourtec.corporative.address.model.CountryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CountryAssembler extends RepresentationModelAssemblerSupport<CountryEntity, CountryResponse> {
	
	public CountryAssembler(final CountryMapper mapper) {
		super(CountryController.class, CountryResponse.class);
	}
	
	@Override
	public CountryResponse toModel(final CountryEntity entity) {
		
		final CountryResponse response = instantiateModel(entity);
		response.add(linkTo(
				methodOn(CountryController.class)
						.getBy(entity.getUuid()))
				             .withSelfRel());
		
		response.add(linkTo(
				methodOn(CountryController.class)
						.delete(entity.getUuid()))
				             .withRel("delete"));
		
		copyProperties(entity, response);
		return response;
	}
}
