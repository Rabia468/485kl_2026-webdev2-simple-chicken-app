#!/bin/bash
set -e  # Stop if any command fails

# Ensure SDKMAN is loaded before using it
#source ~/.sdkman/bin/sdkman-init.sh

# Install Java
#sdk install java 17.0.8-tem

## seems that yarn GPG expired, so we need to add the new key
#curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo gpg --dearmor -o /usr/share/keyrings/yarn-archive-keyring.gpg
#echo "deb [signed-by=/usr/share/keyrings/yarn-archive-keyring.gpg] https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
# faster to just move the old file, since the new one is not working
sudo mv /etc/apt/sources.list.d/yarn.list /etc/apt/sources.list.d/yarn.list.orig




# Install Playwright and Chromium browser
pip install playwright
playwright install --with-deps chromium

