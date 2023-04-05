package ibf2022.batch1.csf.assessment.server.services;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.utils.MovieUtils;;


@Service
public class MovieService {
	public final String NY_TIMES_API_URL="https://api.nytimes.com/svc/movies/v2/reviews/search.json";
	public final String NY_TIMES_API_KEY=System.getenv("NY_TIMES_API_KEY");

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE

	@Autowired
	MovieRepository movieRepo;
	public List<Review> searchReviews(String query) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("api-key", NY_TIMES_API_KEY);
		String url = UriComponentsBuilder.fromUriString(NY_TIMES_API_URL)
									.queryParam("query", query)
									.queryParam("api-key", NY_TIMES_API_KEY)
									.toUriString();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,null,String.class);

		if (response.getStatusCode()!=HttpStatusCode.valueOf(200)){
			System.out.println("badreq");
			List<Review> reviews = new LinkedList<Review>();
			return reviews;
		}
		List<Review> reviews =  MovieUtils.parseResponse(response.getBody());
		for(Review r:reviews){
			int count = movieRepo.countComments(r);
			r.setCommentCount(count);
		}
		return reviews;
	}

	public Document postComment(Comment comment){
		Document inserted = movieRepo.insertComment(comment);
		return inserted;
	}

}
