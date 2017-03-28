package uk.gov.dvsa.moti.fraudserializer.xml;

import uk.gov.dvsa.moti.fraudserializer.xml.schema.ElementType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Schema section
 * https://docs.microsoft.com/en-us/sql/ado/guide/data/schema-section
 */
public class Schema {

    private String id;

    private ElementType elementType;

    public Schema setId(String id) {
        this.id = id;
        return this;
    }

    public Schema setElementType(ElementType elementType) {
        this.elementType = elementType;
        return this;
    }

    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    @XmlElement(name="ElementType", namespace=Root.NAMESPACE_SCHEMA)
    public ElementType getElementType() {
        return elementType;
    }
}
