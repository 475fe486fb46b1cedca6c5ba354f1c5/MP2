package at.tuw.mp2.DAOs;

public enum Header {
    HOME("Home","/"),EXPLORE("Explore","/threads"),MYTHREADS("My Threads","/mythreads"),CREATE("New Thread","/create");

    private String value;
    private String path;

    Header(final String value,final String path) {
        this.value = value;
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
