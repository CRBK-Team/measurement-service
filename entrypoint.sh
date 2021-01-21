#!/bin/sh

if [ ! -z "$CREDENTIALS_FILE" ] && [ -r "/run/secrets/$CREDENTIALS_FILE" ]; then
	. /run/secrets/$CREDENTIALS_FILE
fi

java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /app.jar
