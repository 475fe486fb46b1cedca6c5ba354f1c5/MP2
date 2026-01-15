package at.tuw.mp2.DAOs;

public class ThreadDAO {
    String header;
    String content;
    Integer id;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ThreadDAO(String header) {
        this.header = header;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ThreadDAO(String header, String content, Integer id) {
        this.header = header;
        this.content = content;
        this.id = id;
    }

    public ThreadDAO() {
    }
}
