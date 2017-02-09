package uk.gov.dvsa.moti.processor.executor;

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.fraudserializer.BatchFraudSerializer;
import uk.gov.dvsa.moti.fraudserializer.FraudSerializer;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.persistence.File;

import java.util.ArrayList;
import java.util.List;

public class DocumentJoiner {
    private static Logger logger = LoggerFactory.getLogger(DocumentJoiner.class);
    private BatchFraudSerializer batchFraudSerializer;
    private FraudSerializer fraudSerializer;

    @Inject
    public DocumentJoiner(
            BatchFraudSerializer batchFraudSerializer,
            FraudSerializer fraudSerializer
    ) {
        this.batchFraudSerializer = batchFraudSerializer;
        this.fraudSerializer = fraudSerializer;
    }

    public String joinFiles(List<File> files) {
        String output;
        ArrayList<Fraud> fraudList = new ArrayList<>();
        for (File file : files) {
            Fraud fraud = fraudSerializer.unserialize(file.getContentAsString());
            fraudList.add(fraud);
        }

        output = batchFraudSerializer.serialize(fraudList);
        logger.info(files.size() + " files were contatenated");

        return output;
    }
}