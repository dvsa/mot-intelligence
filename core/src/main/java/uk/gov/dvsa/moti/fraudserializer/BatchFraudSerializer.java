package uk.gov.dvsa.moti.fraudserializer;

import org.slf4j.LoggerFactory;
import uk.gov.dvsa.moti.fraudserializer.xml.Data;
import uk.gov.dvsa.moti.fraudserializer.xml.Root;
import uk.gov.dvsa.moti.fraudserializer.xml.Schema;
import uk.gov.dvsa.moti.fraudserializer.xml.schema.AttributeType;
import uk.gov.dvsa.moti.fraudserializer.xml.schema.DataType;
import uk.gov.dvsa.moti.fraudserializer.xml.schema.ElementType;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class BatchFraudSerializer {
    private static final String TYPE_STRING = "string";
    private static final String DB_TYPE_STRING = "str";
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(BatchFraudSerializer.class);

    public String serialize(List<Fraud> frauds) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);

            Root root = buildDocument(frauds);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            jaxbMarshaller.marshal(root, writer);

            return writer.toString();
        } catch(JAXBException e) {
            logger.error(SerializerError.SERIALIZE.getMessage(), e);
            throw new SerializerException(SerializerError.SERIALIZE.getMessage(), e);
        }
    }

    public Root unserialize(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (Root) jaxbUnmarshaller.unmarshal(new StringReader(xml));
        } catch(JAXBException e) {
            logger.error(SerializerError.UNSERIALIZE.getMessage(), e);
            throw new SerializerException(SerializerError.UNSERIALIZE.getMessage(), e);
        }
    }

    private Root buildDocument(List<Fraud> frauds) {
        Root root = new Root();

        root.setSchema(buildSchema());
        root.setData(buildData(frauds));

        return root;
    }

    private Schema buildSchema() {
        return new Schema().setId("RowsetSchema")
                .setElementType(buildElementType());
    }

    private ElementType buildElementType() {
        return new ElementType().setName("row")
                .setContent("eltOnly")
                .setCommandTimeOut(30)
                .setAttributeTypes(buildAttributeTypes());

    }

    private List<AttributeType> buildAttributeTypes() {
        List<AttributeType> attributeTypes = new ArrayList<>();

        attributeTypes.add(buildAttributeType("Id", 1, TYPE_STRING, DB_TYPE_STRING, 30));
        attributeTypes.add(buildAttributeType("CreationDate", 2, TYPE_STRING, DB_TYPE_STRING, 30));
        attributeTypes.add(buildAttributeType("CreationTime", 3, TYPE_STRING, DB_TYPE_STRING, 30));
        attributeTypes.add(buildAttributeType("Description", 4, TYPE_STRING, DB_TYPE_STRING, 2147483647));
        attributeTypes.add(buildAttributeType("TargetPersonName", 5, TYPE_STRING, DB_TYPE_STRING, 100));
        attributeTypes.add(buildAttributeType("TargetCompanyName", 6, TYPE_STRING, DB_TYPE_STRING, 100));
        attributeTypes.add(buildAttributeType("TargetCompanyAddress", 7, TYPE_STRING, DB_TYPE_STRING, 100));
        attributeTypes.add(buildAttributeType("TargetVehicleRegistrationMark", 8, TYPE_STRING, DB_TYPE_STRING, 100));

        return attributeTypes;
    }

    private AttributeType buildAttributeType(String name, int number, String type, String dbType, int maxLength) {
        return new AttributeType().setName(name).setNumber(number).setDataType(
                new DataType().setType(type).setDbType(dbType).setMaxLength(maxLength)
        );
    }

    private Data buildData(List<Fraud> frauds) {
        return new Data().setRows(frauds);
    }
}
