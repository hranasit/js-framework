package cz.eg.hr.web.model;

import java.util.List;

public class JsFrameworkV1 extends JsFrameworkBaseV1 {

    private Long id;

    public JsFrameworkV1(Long id, String name, List<JsFrameworkVersionV1> versions, int rating) {
        super(name, versions, rating);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
