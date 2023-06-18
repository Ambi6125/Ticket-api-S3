#!/bin/bash

set -e

host="$1"
shift
cmd="$@"

until nc -z -v -w30 $host; do
  echo "Waiting for MySQL..."
  sleep 1
done

echo "MySQL is up - executing command"
exec $cmd