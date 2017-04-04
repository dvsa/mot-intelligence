#!/bin/bash

./../gradlew clean integrationTest -Daws.accessKeyId=$1 -Daws.secretKey=$2