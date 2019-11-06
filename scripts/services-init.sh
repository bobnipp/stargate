#!/bin/bash
find /var/lib/mysql -type f -exec touch {} \; && service mysql start && mailcatcher && redis-server /etc/redis/redis.conf