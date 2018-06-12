#!/bin/bash

#The file should have UNIX-style EOL

if [ -z "$FORMS_API_CONFIG" ]
then
  FORMS_API_CONFIG="forms-api.yml"
fi

echo "config file: $FORMS_API_CONFIG"

if [ -x /paramfolder/parameters.sh ]; then
    source /paramfolder/parameters.sh
fi

if [ -f /opt/newrelic/newrelic.yml ]; then
    java -javaagent:/opt/newrelic/newrelic.jar  ${JAVA_OPTS} -jar forms-api.jar server $FORMS_API_CONFIG
else
    java  ${JAVA_OPTS} -jar forms-api.jar server $FORMS_API_CONFIG
fi
