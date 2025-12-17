package at.tuw.mp2.DAOs;

public class ThreadDAO {
    String header;
    String content;

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
}
