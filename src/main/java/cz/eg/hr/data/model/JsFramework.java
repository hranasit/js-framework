package cz.eg.hr.data.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class JsFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany
    @JoinTable(name = "js_framework_version")
    private List<JsFrameworkVersion> versions;

    //TODO 1-5 constraint
    private int rating;

    public JsFramework() {
    }

    public JsFramework(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JsFrameworkVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<JsFrameworkVersion> version) {
        this.versions = version;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "JavascriptFramework{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", versions=" + versions +
            ", rating=" + rating +
            '}';
    }
}
