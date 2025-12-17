package at.tuw.mp2.DAOs;

public class CommentDAO {
    String comment;

    public CommentDAO(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
