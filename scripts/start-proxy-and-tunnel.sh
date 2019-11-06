#!/usr/bin/env bash

tmux new-session -d -s proxy

#start proxy
tmux send-keys 'cd ../prism-proxy && node app.js' 'C-m'

# start ssh tunnel
tmux split-window -h
tmux select-window -t proxy:1
tmux send-keys 'ssh -v -i ~/.ssh/Stargate-PRISM-GovCloud.pem -R 8080:localhost:5050 ubuntu@96.127.111.218' 'C-m'

tmux select-window -t proxy:1

tmux -2 attach-session -t proxy