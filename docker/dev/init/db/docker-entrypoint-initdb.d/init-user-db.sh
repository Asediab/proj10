#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "loan" --dbname "usr" <<-EOSQL
    CREATE DATABASE document WITH OWNER loan;
    CREATE DATABASE loan WITH OWNER loan;
    CREATE DATABASE reservation WITH OWNER loan
EOSQL