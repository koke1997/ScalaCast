#!/bin/bash

# Update package list
sudo apt-get update

# Install Erlang and rebar3
sudo apt-get install -y erlang rebar3

# Install ffmpeg
sudo apt-get install -y ffmpeg

# Install HLS and DASH dependencies
sudo apt-get install -y libav-tools
sudo apt-get install -y libavcodec-extra

# Install nginx-rtmp-module
sudo apt-get install -y nginx nginx-rtmp-module

# Check if dependencies are installed correctly
check_dependency() {
    if ! command -v $1 &> /dev/null
    then
        echo "$1 could not be found, please install it manually."
        exit 1
    fi
}

check_dependency "erl"
check_dependency "rebar3"
check_dependency "ffmpeg"
check_dependency "nginx"

# Print success message
echo "All dependencies have been successfully installed."
