#!/usr/bin/env sh

mvn -f pom.xml package

./../rancher-compose --project-name usersms_back \
    --url http://192.168.99.101:8080/v1/projects/1a5 \
    --access-key 65B27CBD2098E86FC4D6 \
    --secret-key 9DgSA9h3gxKu6hmJZa7yrcstqX7YkxYframz14GK \
    -f docker-compose.yml \
    --verbose up \
    -d --force-upgrade \
    --confirm-upgrade
