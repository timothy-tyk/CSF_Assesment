package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;


@Repository
public class MovieRepository {
	public final String COLLECTIONS_NAME="comments";
	@Autowired
	MongoTemplate mongoTemplate;
	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below

	// db.comments.find({"title":<movie_title>}).count()
	public int countComments(Review review) {
		String title = review.getTitle();
		System.out.println(title);
		Criteria criteria = Criteria.where("title").is(title);
		Query query = Query.query(criteria);
		List<Document> comments = mongoTemplate.find(query, Document.class,COLLECTIONS_NAME);
		System.out.println(comments);
		return comments.size();
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below

	// db.comments.insert({
		// title:title,
		// user:user,
		// rating:rating,
		// comment:comment
	// })

	public Document insertComment(Comment comment){
		Document commentDoc = Comment.toDoc(comment);
		Document inserted = mongoTemplate.insert(commentDoc, COLLECTIONS_NAME);
		return inserted;
	}
}
