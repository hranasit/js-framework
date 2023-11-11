package cz.eg.hr.web.mapper;

import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.model.JsFrameworkVersion;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import cz.eg.hr.web.model.JsFrameworkVersionV1;

import java.util.stream.Collectors;

public class JsFrameworkBaseMapper {

    public static JsFramework toDomain(JsFrameworkBaseV1 jsFramework) {
        return new JsFramework(
            jsFramework.getName(),
            jsFramework.getVersions().stream().map(JsFrameworkBaseMapper::toDomain).collect(Collectors.toList()),
            jsFramework.getRating()
        );
    }

    private static JsFrameworkVersion toDomain(JsFrameworkVersionV1 jsFrameworkVersion) {
        return new JsFrameworkVersion(
            jsFrameworkVersion.getVersion(),
            jsFrameworkVersion.getDeprecated()
        );
    }
}
