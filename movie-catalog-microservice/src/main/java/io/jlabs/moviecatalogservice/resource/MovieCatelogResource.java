package io.jlabs.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.jlabs.moviecatalogservice.model.Catalog;
import io.jlabs.moviecatalogservice.model.Movie;
import io.jlabs.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatelogResource {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */

	@RequestMapping("/{userId}")
	public List<Catalog> getCatelog(@PathVariable("userId") String userId) {

		// Api call using RestTemplate
		
		
		  UserRating userRating =
		  restTemplate.getForObject("http://rating-data-service/ratings/user/" +
		  userId, UserRating.class);
		 
		// Api call using WebClient
		
		/*
		 * UserRating userRating =
		 * webClientBuilder.build().get().uri("http://rating-data-service/ratings/user/"
		 * + userId).retrieve() .bodyToMono(UserRating.class).block();
		 */

		return userRating.getRatings().stream().map(ratings -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + ratings.getMovieId(),
					Movie.class);
			return new Catalog(movie.getName(), movie.getDescription(), ratings.getRating());

		}).collect(Collectors.toList());

	}

}
