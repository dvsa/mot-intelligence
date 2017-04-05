package uk.gov.dvsa.moti.fraudserializer.xml.schema;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DataType element
 */
@XmlRootElement
public class DataType {
    public static final String NAMESPACE_DATATYPE = "uuid:C2F41010-65B3-11d1-A29F-00AA00C14882";
    private String type;
    private String dbType;
    private int maxLength;

    public DataType setType(String type) {
        this.type = type;
        return this;
    }

    public DataType setDbType(String dbType) {
        this.dbType = dbType;
        return this;
    }

    public DataType setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    @XmlAttribute(name="type", namespace=NAMESPACE_DATATYPE)
    public String getType() {
        return type;
    }

    @XmlAttribute(name="rs:dbtype")
    public String getDbType() {
        return dbType;
    }

    @XmlAttribute(name="dt:maxLength")
    public int getMaxLength() {
        return maxLength;
    }
}
