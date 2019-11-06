#!/bin/bash

find /var/lib/mysql -type f -exec touch {} \;

service mysql start #todo run in foreground?