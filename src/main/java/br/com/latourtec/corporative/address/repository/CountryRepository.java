package br.com.latourtec.corporative.address.repository;

import br.com.latourtec.corporative.address.model.CountryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<CountryEntity, Long> {
	
	@Query("select entity from CountryEntity entity where entity.uuid = :uuid")
	Optional<CountryEntity> findBy(@Param("uuid") String uuid);
	
	@Transactional
	@Modifying
	@Query("delete from CountryEntity  where uuid = :uuid")
	Integer delete(@Param("uuid") String uuid);
}
