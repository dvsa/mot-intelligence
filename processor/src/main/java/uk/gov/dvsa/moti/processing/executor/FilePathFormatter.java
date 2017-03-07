package uk.gov.dvsa.moti.processing.executor;

import java.text.SimpleDateFormat;
import java.util.Date;

class FilePathFormatter {

    public String format(String filePath) {
        String timestamp = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());

        String formattedFilePath = filePath.replace("[TIMESTAMP]", timestamp);

        return formattedFilePath;
    }
}
