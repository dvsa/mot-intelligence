package uk.gov.dvsa.moti.web.views.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabular data representation. Consists of header and rows.
 */
public class SummaryTable {
    private String heading;
    private List<SummaryRow> rows = new ArrayList<>();

    public SummaryTable(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    /**
     * Add row to collection
     * @param key
     * @param value
     * @return
     */
    public SummaryTable addRow(String key, String value) {
        rows.add(new SummaryRow(key, value));

        return this;
    }

    public List<SummaryRow> getRows() {
        return rows;
    }
}
