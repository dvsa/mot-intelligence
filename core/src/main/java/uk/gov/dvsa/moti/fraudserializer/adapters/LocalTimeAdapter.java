package uk.gov.dvsa.moti.fraudserializer.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    private final static DateTimeFormatter formatter = DateTimeFormat.mediumTime();

    public LocalTime unmarshal(String v) throws Exception {
        return v == null ? null : LocalTime.parse(v, formatter);
    }

    public String marshal(LocalTime v) throws Exception {
        return v == null ? null : formatter.print(v);
    }
}