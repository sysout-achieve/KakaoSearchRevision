#!/bin/bash
echo "kakao.api.key=$KAKAO_API_KEY" >> ../key.api.properties
cd ..
./gradlew clean build
