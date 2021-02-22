package com.example.ec.explorecali.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourRating;
import com.example.ec.explorecali.repo.TourRatingRepository;
import com.example.ec.explorecali.repo.TourRepository;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

	private TourRatingRepository tourRatingRepository;
	private TourRepository tourRepository;
	
	@Autowired
	public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
		this.tourRatingRepository = tourRatingRepository;
		this.tourRepository = tourRepository;
	}
	
	protected TourRatingController() { }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTourRating(@PathVariable(value = "tourId") String tourId, @RequestBody @Validated TourRating tourRating) {
		verifyTour(tourId);
		tourRatingRepository.save(new TourRating(tourId, tourRating.getCustomerId(), tourRating.getScore(), tourRating.getComment()));
	}
	
	@GetMapping
	public Page<TourRating> getRatings(@PathVariable(value = "tourId") String tourId, Pageable pageable) {
		return tourRatingRepository.findByTourId(tourId, pageable);
	}

	@PutMapping
	public TourRating updateWithPut(@PathVariable(value = "tourId") String tourId, @RequestBody @Validated TourRating tourRating) {
		TourRating rating = verifyTourRating(tourId, tourRating.getCustomerId());
		rating.setScore(tourRating.getScore());
		rating.setComment(tourRating.getComment());
		return tourRatingRepository.save(rating);
	}
	
	@PatchMapping
	public TourRating updateWithPatch(@PathVariable(value = "tourId") String tourId, @RequestBody @Validated TourRating tourRating) {
		TourRating rating = verifyTourRating(tourId, tourRating.getCustomerId());
		if (tourRating.getScore() != null) {
			rating.setScore(tourRating.getScore());
		}
		if (tourRating.getComment() != null) {
			rating.setComment(tourRating.getComment());
		}
		return tourRatingRepository.save(rating);
	}
	
	@DeleteMapping(path = "/{customerId}")
	public void delete(@PathVariable(value = "tourId") String tourId, @PathVariable(value = "customerId") int customerId) {
		TourRating rating = verifyTourRating(tourId, customerId);
		tourRatingRepository.delete(rating);
	}
	
	private Tour verifyTour(String tourId) throws NoSuchElementException {
		return tourRepository.findById(tourId).orElseThrow(() -> new NoSuchElementException("Tour does not exists : " + tourId));
	}
	
	private TourRating verifyTourRating(String tourId, int customerId) throws NoSuchElementException {
		return tourRatingRepository.findByTourIdAndCustomerId(tourId, customerId).orElseThrow(() -> new NoSuchElementException("Tour-Rating pair for request(" + tourId + " for customer" + customerId));
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();
	}
	
}