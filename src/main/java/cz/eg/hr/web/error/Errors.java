package cz.eg.hr.web.error;

import java.util.List;

public record Errors(List<ValidationError> errors) {
}
