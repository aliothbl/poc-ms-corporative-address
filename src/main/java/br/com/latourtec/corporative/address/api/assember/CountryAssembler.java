package br.com.latourtec.corporative.address.api.assember;

import br.com.latourtec.corporative.address.api.controller.CountryController;
import br.com.latourtec.corporative.address.api.dto.CountryResponse;
import br.com.latourtec.corporative.address.model.CountryEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static br.com.latourtec.corporative.address.api.ApiConfig.ROOT;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class CountryAssembler extends RepresentationModelAssemblerSupport<CountryEntity, CountryResponse> {
	
	public CountryAssembler() {
		super(CountryController.class, CountryResponse.class);
	}
	
	@Override
	public CountryResponse toModel(final CountryEntity entity) {
		
		final CountryResponse response = instantiateModel(entity);
		Link self = Link.of(ROOT + "/countries/{uuid}")
		                  .expand(entity.getUuid())
		                  .withRel("self");
		response.add(self);
		
		Link remove = Link.of(ROOT + "/countries/{uuid}")
		                .expand(entity.getUuid())
		                .withRel("remove");
		response.add(remove);
		
		copyProperties(entity, response);
		return response;
	}
}
