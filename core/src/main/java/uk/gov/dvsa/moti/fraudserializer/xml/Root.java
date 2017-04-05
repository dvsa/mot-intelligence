package uk.gov.dvsa.moti.fraudserializer.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Root element in exported xml
 */
@XmlRootElement(name="xml")
@XmlType(propOrder={"schema", "data"})
public class Root {
    public static final String NAMESPACE_ROWSET = "urn:schemas-microsoft-com:rowset";
    public static final String NAMESPACE_SCHEMA = "uuid:BDC6E3F0-6DA3-11d1-A2A3-00AA00C14882";
    private Schema schema;
    private Data data;

    public Root() {
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @XmlElement(name="Schema", namespace=NAMESPACE_SCHEMA)
    public Schema getSchema() {
        return schema;
    }

    @XmlElement(name="data", namespace= NAMESPACE_ROWSET)
    public Data getData() {
        return data;
    }
}
