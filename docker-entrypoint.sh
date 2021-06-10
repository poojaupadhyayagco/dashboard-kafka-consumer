#!/bin/sh

## Set the application profile (dev/stg/prod)
export APPLICATION_PROFILE=$(echo "$FUSE_ENV" | awk '{print tolower($0)}')

echo "Application profile is: $APPLICATION_PROFILE"

STATUS=$(curl --max-time 5 -s -o /dev/null -w '%{http_code}' http://169.254.170.2/v2/metadata/)


echo "Starting service"

java -jar -Dspring.profiles.active=$APPLICATION_PROFILE /app.jar