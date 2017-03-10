package uk.gov.dvsa.moti.processing;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import uk.gov.dvsa.moti.persistence.File;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class TarDecompressor {
    public static List<File> unpackTarGzArchive(byte[] tarGz) {
        ArrayList<File> out = new ArrayList<>();

        try {
            final TarArchiveInputStream tarInputStream = new TarArchiveInputStream(new GZIPInputStream(new ByteArrayInputStream(tarGz)));
            TarArchiveEntry tarEntry = tarInputStream.getNextTarEntry();
            while (tarEntry != null) {
                byte[] bytesToRead = new byte[1024];
                ByteArrayOutputStream entryAsByteStream = new ByteArrayOutputStream();
                int length = 0;
                while ((length = tarInputStream.read(bytesToRead)) != -1) {
                    entryAsByteStream.write(bytesToRead, 0, length);
                }
                entryAsByteStream.close();

                String content = entryAsByteStream.toString(StandardCharsets.UTF_8.name());
                out.add(new File(tarEntry.getName(), content));
                tarEntry = tarInputStream.getNextTarEntry();
            }
            tarInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }
}
