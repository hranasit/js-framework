package cz.eg.hr.data.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class JsFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<JsFrameworkVersion> versions;

    //TODO 1-5 constraint
    private int rating;

    public JsFramework() {
    }

    public JsFramework(Long id, String name, List<JsFrameworkVersion> versions, int rating) {
        this.id = id;
        this.name = name;
        this.versions = versions;
        this.rating = rating;
    }

    public JsFramework(String name, List<JsFrameworkVersion> versions, int rating) {
        this.name = name;
        this.versions = versions;
        this.rating = rating;
    }

    public JsFramework(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof JsFramework framework)) return false;
        return rating == framework.rating && Objects.equals(id, framework.id) && Objects.equals(name, framework.name) && Objects.equals(versions, framework.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, versions, rating);
    }
}
