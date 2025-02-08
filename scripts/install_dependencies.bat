@echo off

REM Update Chocolatey
choco upgrade chocolatey

REM Install Erlang and rebar3
choco install erlang
choco install rebar3

REM Install ffmpeg
choco install ffmpeg

REM Install HLS and DASH dependencies
choco install libav-tools
choco install libavcodec-extra

REM Install nginx-rtmp-module
choco install nginx
choco install nginx-rtmp-module

REM Check if dependencies are installed correctly
where erl || (echo Erlang could not be found, please install it manually. && exit /b 1)
where rebar3 || (echo rebar3 could not be found, please install it manually. && exit /b 1)
where ffmpeg || (echo ffmpeg could not be found, please install it manually. && exit /b 1)
where nginx || (echo nginx could not be found, please install it manually. && exit /b 1)

REM Print success message
echo All dependencies have been successfully installed.
