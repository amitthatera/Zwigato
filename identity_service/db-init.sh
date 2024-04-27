#!/bin/bash

set -e

# Function to check if PostgreSQL is ready
is_postgres_ready() {
    local host="$DB_HOST"
    local user="$DB_USER"
    local timeout=30  # Timeout in seconds
    local count=0

    until pg_isready -h "$host" -U "$user" > /dev/null 2>&1 || [ "$count" -ge "$timeout" ]; do
        echo "Waiting for PostgreSQL to start..."
        sleep 1
        ((count++))
    done

    if [ "$count" -ge "$timeout" ]; then
        echo "Timeout: PostgreSQL took too long to start."
        exit 1
    fi

    echo "PostgreSQL is ready."
}

# Main script execution
main() {
    # Check if required environment variables are set
    if [[ -z "$DB_NAME" || -z "$DB_USER" || -z "$DB_PASSWORD" || -z "$DB_HOST" ]]; then
        echo "Error: Required environment variables not set. Please set DB_NAME, DB_USER, DB_PASSWORD, and DB_HOST."
        exit 1
    fi

    # Wait for PostgreSQL to be ready
    is_postgres_ready

    # Check if the database exists
    if ! psql -h "$DB_HOST" -U "$DB_USER" -lqt | cut -d \| -f 1 | grep -qw "$DB_NAME"; then
        echo "Database '$DB_NAME' does not exist. Creating..."
        createdb -h "$DB_HOST" -U "$DB_USER" "$DB_NAME"
        echo "Database '$DB_NAME' created."
    else
        echo "Database '$DB_NAME' already exists."
    fi
}

# Execute main script
main
