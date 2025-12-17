package at.tuw.mp2.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "text", length = 2048)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commentor", nullable = false)
    private at.tuw.mp2.Model.User commentor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thread", nullable = false)
    private at.tuw.mp2.Model.Thread thread;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public at.tuw.mp2.Model.User getCommentor() {
        return commentor;
    }

    public void setCommentor(at.tuw.mp2.Model.User commentor) {
        this.commentor = commentor;
    }

    public at.tuw.mp2.Model.Thread getThread() {
        return thread;
    }

    public void setThread(at.tuw.mp2.Model.Thread thread) {
        this.thread = thread;
    }

}