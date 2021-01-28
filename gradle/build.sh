#!/bin/sh
echo "kakao.api.key=\"$KAKAO_API_KEY\"" >> ../key.api.properties
echo ls
./gradlew clean build
