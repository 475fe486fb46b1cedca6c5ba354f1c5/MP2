package at.tuw.mp2.Repositories;

import at.tuw.mp2.Model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread,Integer> {
}
