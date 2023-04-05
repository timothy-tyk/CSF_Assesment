package ibf2022.batch1.csf.assessment.server.models;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short 
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setRating(String rating) { this.rating = rating; }
	public String getRating() { return this.rating; }

	public void setByline(String byline) { this.byline = byline; }
	public String getByline() { return this.byline; }

	public void setHeadline(String headline) { this.headline = headline; }
	public String getHeadline() { return this.headline; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setReviewURL(String reviewURL) { this.reviewURL = reviewURL; }
	public String getReviewURL() { return this.reviewURL; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }
	public boolean hasImage() { return null != this.image; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; };
	public int getCommentCount() { return this.commentCount; }


	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}

	public static Review fromJson(JsonObject json){
		Review rv = new Review();
		rv.setTitle(json.getString("display_title"));
		rv.setRating(json.getString("mpaa_rating"));
		rv.setByline(json.getString("byline"));
		rv.setHeadline(json.getString("headline"));
		rv.setSummary(json.getString("summary_short"));
		rv.setReviewURL(json.getJsonObject("link").getString("url"));
		if(json.get("multimedia").toString().equals("null")){
			rv.setImage("");
		}else{			
			rv.setImage(json.getJsonObject("multimedia").getString("src"));
		}
		rv.setCommentCount(0);
		return rv;
	}

	public static JsonObject toJson(Review r){
		return Json.createObjectBuilder()
						.add("title", r.getTitle())
						.add("rating", r.getRating())
						.add("byline", r.getByline())
						.add("headline", r.getHeadline())
						.add("summary", r.getSummary())
						.add("reviewUrl", r.getReviewURL())
						.add("image", r.getImage())
						.add("commentCount", r.getCommentCount())
						.build();
	}

	
	
}
