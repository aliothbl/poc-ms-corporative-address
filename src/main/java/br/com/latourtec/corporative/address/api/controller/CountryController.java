package br.com.latourtec.corporative.address.api.controller;

import br.com.latourtec.corporative.address.api.dto.CountryRequest;
import br.com.latourtec.corporative.address.api.dto.CountryResponse;
import br.com.latourtec.corporative.address.services.CountryService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.latourtec.corporative.address.api.ApiConfig.ROOT;

@RestController
@RequestMapping(ROOT + "/countries")
@Api(tags = { "Corporative Country" })
public class CountryController {
	
	private final CountryService service;
	
	public CountryController(final CountryService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<PagedModel<CountryResponse>> getAllCountries(Pageable pageable)
	{
		return ResponseEntity.ok(service.getAll(pageable));
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<CountryResponse> getBy(@PathVariable String uuid)
	{
		return ResponseEntity.ok(service.get(uuid));
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity delete(@PathVariable String uuid)
	{
		service.delete(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping
	public ResponseEntity<CountryResponse> addCountry(@RequestBody CountryRequest request)
	{
		
/*		String [] countryNames = {"Afghanistan","Angola","Argentina","Australia","Austria","Belgium","Bolivia","Brazil","Bulgaria","Canada","China","Chile","Colombia","Croatia","Cuba","Denmark","Dominican","Ecuador","Egypt","England","Finland","France","Germany","Greece","Guinea","Haiti","Holland","Hungary","Iceland","India","Indonesia","Iran","Ireland","Israel","Italy","Jamaica","Japan","Kenya","Kwait","Latvia"};
		for (int i = 0; i < countryNames.length; i++) {
			System.out.println("insert into address.tb_country (nm_name, uuid, cd_seq_country) values ('"+countryNames[i]+"', '" +
			                   UUID.randomUUID().toString() + "', "+ (1000000+i)+");");
			
		}
		
 */
		
		return ResponseEntity.ok(service.add(request));
	}

}
