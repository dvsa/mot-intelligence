package uk.gov.dvsa.moti.fraudserializer.xml.schema;
import uk.gov.dvsa.moti.fraudserializer.xml.Root;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class ElementType {
    private String name;

    private String content;

    private int commandTimeOut;

    private List<AttributeType> attributeTypes;

    public ElementType setName(String name) {
        this.name = name;
        return this;
    }

    public ElementType setContent(String content) {
        this.content = content;
        return this;
    }

    public ElementType setCommandTimeOut(int commandTimeOut) {
        this.commandTimeOut = commandTimeOut;
        return this;
    }

    public ElementType setAttributeTypes(List<AttributeType> attributeTypes) {
        this.attributeTypes = attributeTypes;
        return this;
    }

    @XmlAttribute(name="name")
    public String getName() {
        return name;
    }

    @XmlAttribute(name="content")
    public String getContent() {
        return content;
    }

    @XmlAttribute(name="rs:CommandTimeout")
    public int getCommandTimeOut() {
        return commandTimeOut;
    }

    @XmlElements(@XmlElement(name="AttributeType", namespace=Root.NAMESPACE_SCHEMA))
    public List<AttributeType> getAttributeTypes() {
        return attributeTypes;
    }
}
