package cz.eg.hr.web.mapper;

import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.model.JsFrameworkVersion;
import cz.eg.hr.web.model.JsFrameworkV1;
import cz.eg.hr.web.model.JsFrameworkVersionV1;

import java.util.stream.Collectors;

public class JsFrameworkMapper extends JsFrameworkBaseMapper {

    public static JsFrameworkV1 toModel(JsFramework jsFramework) {
        return new JsFrameworkV1(
            jsFramework.getId(),
            jsFramework.getName(),
            jsFramework.getVersions().stream().map(JsFrameworkMapper::toModel).collect(Collectors.toList()),
            jsFramework.getRating()
        );
    }

    public static JsFrameworkVersionV1 toModel(JsFrameworkVersion jsFrameworkVersion) {
        return new JsFrameworkVersionV1(
            jsFrameworkVersion.getVersion(),
            jsFrameworkVersion.getDeprecated()
        );
    }
}
