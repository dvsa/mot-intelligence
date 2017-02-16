package uk.gov.dvsa.moti.fraudserializer;

import org.slf4j.LoggerFactory;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class FraudSerializer {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FraudSerializer.class);

    public String serialize(Fraud fraud) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Fraud.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            jaxbMarshaller.marshal(fraud, writer);

            return writer.toString();
        } catch(JAXBException e) {
            logger.error(SerializerError.SERIALIZE.getMessage(), e);
            throw new SerializerException(SerializerError.SERIALIZE.getMessage(), e);
        }
    }

    public Fraud unserialize(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Fraud.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (Fraud) jaxbUnmarshaller.unmarshal(new StringReader(xml));
        } catch(JAXBException e) {
            logger.error(SerializerError.UNSERIALIZE.getMessage(), e);
            throw new SerializerException(SerializerError.UNSERIALIZE.getMessage(), e);
        }
    }
}