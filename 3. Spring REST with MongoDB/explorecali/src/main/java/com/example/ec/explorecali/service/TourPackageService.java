package com.example.ec.explorecali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackageRepository;

@Service
public class TourPackageService {

	private TourPackageRepository tourPackageRepository;

	@Autowired
	public TourPackageService(TourPackageRepository tourPackageRepository) {
		super();
		this.tourPackageRepository = tourPackageRepository;
	}

	public TourPackage createTourPackage(String code, String name) {
		return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code, name)));
	}

	public Iterable<TourPackage> lookup() {
		return tourPackageRepository.findAll();
	}

	public long total() {
		return tourPackageRepository.count();
	}

}