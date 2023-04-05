package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@Controller
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
	// TODO: Task 3, Task 4, Task 8
	@Autowired
	MovieService movieSvc;

	@GetMapping(path = "/search",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> searchMovies(@RequestParam String query){
		List<Review> reviews = movieSvc.searchReviews(query);
		JsonArrayBuilder jArrB = Json.createArrayBuilder();
		for(Review r:reviews){
			jArrB.add(Review.toJson(r));
		}
		return ResponseEntity.ok().body(jArrB.build().toString());
	}

	@PostMapping(path = "/comment")
	public ResponseEntity<String> postCommentToMovie(@RequestBody Comment comment){
		System.out.println(comment.toString());
		Document response = movieSvc.postComment(comment);
		return ResponseEntity.ok().body(response.toJson());
	}

}
