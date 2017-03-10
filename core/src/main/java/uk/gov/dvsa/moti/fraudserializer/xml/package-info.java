@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(type=LocalDate.class,
                value=LocalDateAdapter.class),
        @XmlJavaTypeAdapter(type=LocalTime.class,
                value=LocalTimeAdapter.class),
})
@XmlSchema(
        xmlns={
                @XmlNs(prefix="s", namespaceURI=Root.NAMESPACE_SCHEMA),
                @XmlNs(prefix="rs", namespaceURI=Root.NAMESPACE_ROWSET),
                @XmlNs(prefix="dt", namespaceURI=DataType.NAMESPACE_DATATYPE),
                @XmlNs(prefix="z", namespaceURI=Data.NAMESPACE_ROW)
        }
)
package uk.gov.dvsa.moti.fraudserializer.xml;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import uk.gov.dvsa.moti.fraudserializer.adapters.LocalDateAdapter;
import uk.gov.dvsa.moti.fraudserializer.adapters.LocalTimeAdapter;
import uk.gov.dvsa.moti.fraudserializer.xml.schema.DataType;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;