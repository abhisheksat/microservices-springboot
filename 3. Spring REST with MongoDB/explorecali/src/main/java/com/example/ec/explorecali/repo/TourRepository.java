package com.example.ec.explorecali.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.ec.explorecali.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, String> {
	
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);

	/*
	 * @Override
	 * 
	 * @RestResource(exported = false) default <S extends Tour> S save(S entity) {
	 * // TODO Auto-generated method stub return null; }
	 * 
	 * @Override
	 * 
	 * @RestResource(exported = false) default <S extends Tour> Iterable<S>
	 * saveAll(Iterable<S> entities) { // TODO Auto-generated method stub return
	 * null; }
	 */

	@Query(value = "{'tourPackageCode' : ?0}", fields = "{ 'id':1, 'title':1, 'tourPackageCode':1, 'tourPackageName':1 }")
	Page<Tour> findSummaryByTourPackageCode(@Param("code") String code, Pageable pageable);
	
	@Override
	@RestResource(exported = false)
	default void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RestResource(exported = false)
	default void delete(Tour entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RestResource(exported = false)
	default void deleteAll(Iterable<? extends Tour> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RestResource(exported = false)
	default void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
}