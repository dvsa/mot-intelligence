# MOT Intelligence

MOT Intelligence aka MOTI is an application used to report fraud regarding MOT Tests. It's built with Dropwizard.

MOTI is split to three modules.

## Core
Defines interfaces and behaviors shared between **processor** and **webapp** (e. g. serialization of data).

## Webapp
Web Frontend application. Displays and validates fraud report entered by user and then sends it to temporary storage.

### Run
```
./gradlew webapp:run
```

### Tests
```
./gradlew webapp:test
./gradlew webapp:integrationTest
```

## Processor
Application runned in a repetitive way. It fetches fraud reports from temporary storage, creates archive of batch fraud reports and sends it to final destination.

### Run
```
./gradlew processor:run
```

### Tests
```
./gradlew processor:test
./gradlew processor:integrationTest
```
### Integration with OLCS
Fraud reports are on production are being sent as one XML file to OLCS S3 Bucket.

XML file uses MS SQL Export format.

Reports are delivered as TAR archive containing single XML file with reports and manifest file. Manifest file contains SHA256 hash of XML document which is used to validate correctness of data. Compression of XML files and creation of manifest file can be turned off in configuration, so only XML file is sent.

For purpose of intergration tests we use MOT S3 Bucket as destination for reports.