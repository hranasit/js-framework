package cz.eg.hr.web.model;

import java.util.List;

public class JsFrameworkBaseV1 {

    //TODO max 30
    protected String name;

    protected List<JsFrameworkVersionV1> versions;

    //TODO 1,2...5
    protected int rating;

    public JsFrameworkBaseV1(String name, List<JsFrameworkVersionV1> versions, int rating) {
        this.name = name;
        this.versions = versions;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JsFrameworkVersionV1> getVersions() {
        return versions;
    }

    public void setVersions(List<JsFrameworkVersionV1> versions) {
        this.versions = versions;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
