package br.com.latourtec.corporative.address.services;

import br.com.latourtec.corporative.address.api.assember.CountryAssembler;
import br.com.latourtec.corporative.address.api.dto.CountryRequest;
import br.com.latourtec.corporative.address.api.dto.CountryResponse;
import br.com.latourtec.corporative.address.api.mapper.CountryMapper;
import br.com.latourtec.corporative.address.exception.EntityNotFoundException;
import br.com.latourtec.corporative.address.model.CountryEntity;
import br.com.latourtec.corporative.address.repository.CountryRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountryService {
	
	
	private final CountryRepository repository;
	private final CountryMapper mapper;
	private final CountryAssembler assembler;
	private final PagedResourcesAssembler<CountryEntity> pagedAssembler;
	
	public CountryService(final CountryRepository repository,
	                      final CountryMapper mapper,
	                      final CountryAssembler assembler,
	                      final PagedResourcesAssembler<CountryEntity> pagedAssembler) {
		this.repository = repository;
		this.mapper = mapper;
		this.assembler = assembler;
		this.pagedAssembler = pagedAssembler;
	}
	
	public PagedModel<CountryResponse> getAll(Pageable pageable){
		Page<CountryEntity> page = repository.findAll(pageable);
		return pagedAssembler.toModel(page, assembler);
	}
	
	public CountryResponse add(CountryRequest request){
		return assembler.toModel(this.repository.save(mapper.from(request)));
	}
	
	public CountryResponse get(final String uuid){
		return assembler.toModel(this.repository.findBy(uuid).orElseThrow(() -> new EntityNotFoundException("We didn't find country with uuid '"+ uuid + "', sorry!")));
	}
	
	public void delete(final String uuid){
		repository.delete(uuid);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	private void loadDatabase(){
		String [] countryNames = {"Afghanistan","Angola","Argentina","Australia","Austria","Belgium","Bolivia","Brazil","Bulgaria","Canada","China","Chile","Colombia","Croatia","Cuba","Denmark","Dominican","Ecuador","Egypt","England","Finland","France","Germany","Greece","Guinea","Haiti","Holland","Hungary","Iceland","India","Indonesia","Iran","Ireland","Israel","Italy","Jamaica","Japan","Kenya","Kwait","Latvia"};
		for (int i = 0; i < countryNames.length; i++) {
			final CountryEntity entity = new CountryEntity();
			entity.setName(countryNames[i]);
			entity.setUuid(UUID.randomUUID().toString());
			repository.save(entity);
		}
	}
}
