package at.tuw.mp2.Model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "threads")
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hd")
    private String hd;

    @Column(name = "body", length = 2048)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator", nullable = false)
    private at.tuw.mp2.Model.User creator;

    @OneToMany(mappedBy = "thread")
    private Set<Comment> comments = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public at.tuw.mp2.Model.User getCreator() {
        return creator;
    }

    public void setCreator(at.tuw.mp2.Model.User creator) {
        this.creator = creator;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return thread.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hd, body, creator, comments);
    }
}