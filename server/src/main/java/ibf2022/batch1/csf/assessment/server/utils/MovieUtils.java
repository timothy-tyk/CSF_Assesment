package ibf2022.batch1.csf.assessment.server.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import ibf2022.batch1.csf.assessment.server.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class MovieUtils {
  
  public static List<Review> parseResponse(String response){
    InputStream is = new ByteArrayInputStream(response.getBytes());
		JsonReader jReader = Json.createReader(is);
		JsonObject jsonResponse = jReader.readObject();
		JsonArray resultArray = jsonResponse.getJsonArray("results");
		List<Review> reviews = resultArray.stream().map(v -> Review.fromJson(v.asJsonObject())).toList();
    return reviews;
  }
}
