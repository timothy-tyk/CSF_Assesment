package ibf2022.batch1.csf.assessment.server.models;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
  private String title;
  private String user;
  private String comment;
  private Integer rating;

  public static Document toDoc(Comment c){
    Document doc = new Document();
    doc.append("title", c.getTitle());
    doc.append("user", c.getUser());
    doc.append("comment", c.getComment());
    doc.append("rating", c.getRating());
    return doc;
  }
}
