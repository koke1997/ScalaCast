#!/bin/bash

# Build script to generate static files for GitHub Pages

# Compile Scala code
sbt compile

# Generate static files
sbt stage

# Copy generated files to the docs directory
cp -r target/scala-2.13/scalajs-bundler/main/* docs/
