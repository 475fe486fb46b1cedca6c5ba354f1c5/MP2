package at.tuw.mp2.Repositories;

import at.tuw.mp2.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
