package io.jlabs.ratingdataservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jlabs.ratingdataservice.model.Rating;
import io.jlabs.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingResource {
	@RequestMapping("/movies/{movieId}")
	public Rating getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/user/{userId}")
	public UserRating getUerRatings(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.initData(userId);
		return userRating;
	}

}