package cz.eg.hr.web.error;

public record ValidationError(String field, String message) {
}
