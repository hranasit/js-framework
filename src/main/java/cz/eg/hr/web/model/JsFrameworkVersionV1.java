package cz.eg.hr.web.model;

import java.sql.Date;

public class JsFrameworkVersionV1 {

    private String version;

    private Date deprecated;

    public JsFrameworkVersionV1(String version, Date deprecated) {
        this.version = version;
        this.deprecated = deprecated;
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
}
