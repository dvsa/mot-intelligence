package uk.gov.dvsa.moti.web.views.model;

public class SummaryRow {
    private String key;
    private String value;

    public SummaryRow(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public boolean isValueProvided() {
        return (value.trim().isEmpty() == false);
    }
}
