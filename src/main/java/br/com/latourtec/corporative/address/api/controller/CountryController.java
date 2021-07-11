package br.com.latourtec.corporative.address.api.controller;

import br.com.latourtec.corporative.address.api.dto.CountryRequest;
import br.com.latourtec.corporative.address.api.dto.CountryResponse;
import br.com.latourtec.corporative.address.services.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.latourtec.corporative.address.api.ApiConfig.ROOT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ROOT + "/countries")
@Api(tags = { "Corporative Country" })
public class CountryController {
	
	private final CountryService service;
	
	public CountryController(final CountryService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "List all countries", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200,message = "Success",response = CountryResponse[].class)})
	@GetMapping
	public ResponseEntity<PagedModel<CountryResponse>> getAllCountries(Pageable pageable)
	{
		return ResponseEntity.ok(service.getAll(pageable));
	}
	
	@ApiOperation(value = "Retrieve the country by uuid", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200,message = "Success",response = CountryResponse.class)})
	@GetMapping("/{uuid}")
	public ResponseEntity<CountryResponse> getBy(@PathVariable String uuid)
	{
		return ResponseEntity.ok(service.get(uuid));
	}
	
	@ApiOperation(value = "Remove the country by uuid", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 204,message = "Success"),
	                        @ApiResponse(code = 422,message = "Unprocessable Entity")})
	@DeleteMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String uuid)
	{
		service.delete(uuid);
	}
	
	@ApiOperation(value = "Include a new country", produces = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CountryResponse.class),
	                        @ApiResponse(code = 422,message = "Unprocessable Entity")})
	@PostMapping
	public ResponseEntity<CountryResponse> addCountry(@RequestBody CountryRequest request)
	{
		return ResponseEntity.ok(service.add(request));
	}

}
