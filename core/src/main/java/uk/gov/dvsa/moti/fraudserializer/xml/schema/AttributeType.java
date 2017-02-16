package uk.gov.dvsa.moti.fraudserializer.xml.schema;

import uk.gov.dvsa.moti.fraudserializer.xml.Root;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class AttributeType {
    private String name;

    private int number;

    private DataType dataType;

    public AttributeType setName(String name) {
        this.name = name;
        return this;
    }

    public AttributeType setNumber(int number) {
        this.number = number;
        return this;
    }

    public AttributeType setDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    @XmlAttribute(name="name")
    public String getName() {
        return name;
    }

    @XmlAttribute(name="rs:number")
    public int getNumber() {
        return number;
    }

    @XmlElement(name="datatype", namespace=Root.NAMESPACE_SCHEMA)
    public DataType getDataType() {
        return dataType;
    }
}
