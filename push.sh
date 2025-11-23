#!/bin/bash
# Push script using GitHub token
# Usage: ./push.sh YOUR_GITHUB_TOKEN

if [ -z "$1" ]; then
    echo "Usage: ./push.sh YOUR_GITHUB_TOKEN"
    exit 1
fi

TOKEN=$1
git push https://${TOKEN}@github.com/fabricioguidine/ed2commarcelo.git master

