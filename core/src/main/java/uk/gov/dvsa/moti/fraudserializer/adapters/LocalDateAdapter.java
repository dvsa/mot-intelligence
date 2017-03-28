package uk.gov.dvsa.moti.fraudserializer.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Handling LocalDate in JAXB
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    public LocalDate unmarshal(String v) throws Exception {
        return v == null ? null : LocalDate.parse(v, formatter);
    }

    public String marshal(LocalDate v) throws Exception {
        return v == null ? null : formatter.print(v);
    }
}
