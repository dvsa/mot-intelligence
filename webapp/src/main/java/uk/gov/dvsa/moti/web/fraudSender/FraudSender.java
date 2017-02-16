package uk.gov.dvsa.moti.web.fraudSender;


import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;

public interface FraudSender {
    void send(Fraud fraudModel);
}
