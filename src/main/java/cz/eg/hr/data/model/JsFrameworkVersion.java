package cz.eg.hr.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.search.annotations.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
public class JsFrameworkVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
     * data type depends on type of versioning (serial, semantic, calendar, git hash)
     * and requirements for sorting and filtering
     * - wasn't specified, so keeping it as string so it's not limiting creation of
     * new framework
     * TODO probably semantic versioning https://docs.npmjs.com/about-semantic-versioning
     */
    private String version;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deprecated;

    public JsFrameworkVersion() {
    }

    public JsFrameworkVersion(String version, Date deprecated) {
        this.version = version;
        this.deprecated = deprecated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Date deprecated) {
        this.deprecated = deprecated;
    }

    @Override
    public String toString() {
        return "JsFrameworkVersion{" +
            "id=" + id +
            ", version='" + version + '\'' +
            ", deprecated=" + deprecated +
            '}';
    }
}
