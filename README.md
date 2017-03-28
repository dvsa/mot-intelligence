# MOT Intelligence

MOT Intelligence aka MOTI is an application used to report fraud regarding MOT Tests. It's built with Dropwizard.

MOTI is split to three modules.

## Core
Defines interfaces and behaviors shared between **processor** and **webapp** (e. g. serialization of data).

## Webapp
Web Frontend application. Displays and validates fraud report entered by user and then sends it to temporary storage.

###run
```
./gradlew webapp:run
```

###tests
```
./gradlew webapp:test
./gradlew webapp:integrationTest
```

## Processor
Application runned in a repetitive way. It fetches fraud reports from temporary storage, creates archive of batch fraud reports and sends it to final destination.

###run
```
./gradlew processor:run
```

###tests
```
./gradlew processor:test
./gradlew processor:integrationTest
```