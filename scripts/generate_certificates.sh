#!/bin/bash

# Function to generate SSL certificates
generate_certificate() {
    local cert_type=$1
    local cert_dir=$2
    local cert_name=$3

    case $cert_type in
        PEM)
            openssl req -new -newkey rsa:2048 -days 365 -nodes -x509 -keyout "$cert_dir/$cert_name.key" -out "$cert_dir/$cert_name.crt"
            ;;
        DER)
            openssl req -new -newkey rsa:2048 -days 365 -nodes -x509 -keyout "$cert_dir/$cert_name.key" -out "$cert_dir/$cert_name.crt"
            openssl x509 -outform der -in "$cert_dir/$cert_name.crt" -out "$cert_dir/$cert_name.der"
            ;;
        PFX)
            openssl req -new -newkey rsa:2048 -days 365 -nodes -x509 -keyout "$cert_dir/$cert_name.key" -out "$cert_dir/$cert_name.crt"
            openssl pkcs12 -export -out "$cert_dir/$cert_name.pfx" -inkey "$cert_dir/$cert_name.key" -in "$cert_dir/$cert_name.crt"
            ;;
        *)
            echo "Unsupported certificate type: $cert_type"
            exit 1
            ;;
    esac
}

# Main script
if [ "$#" -ne 3 ]; then
    echo "Usage: $0 <certificate_type> <certificate_directory> <certificate_name>"
    echo "Supported certificate types: PEM, DER, PFX"
    exit 1
fi

CERT_TYPE=$1
CERT_DIR=$2
CERT_NAME=$3

mkdir -p "$CERT_DIR"
generate_certificate "$CERT_TYPE" "$CERT_DIR" "$CERT_NAME"

echo "Certificate generated successfully: $CERT_DIR/$CERT_NAME"
