package uk.gov.dvsa.moti.fraudserializer.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;

public class Data {
    public static final String NAMESPACE_ROW = "#RowsetSchema";
    private List<Fraud> rows;

    @XmlElements(@XmlElement(name="row", namespace=NAMESPACE_ROW))
    public List<Fraud> getRows() {
        return rows;
    }

    public Data setRows(List<Fraud> rows) {
        this.rows = rows;
        return this;
    }
}
