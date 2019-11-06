#!/bin/bash -f

#
# USAGE:  stargate-session.sh [-m]
#   -m ... run mock prism adapter
#

TMUX_SESSION='stargate'
MOCK_PRISM_ADAPTER=false
INDEX_FILE='components/api/src/main/resources/static/index.html'

tmux ls | grep $TMUX_SESSION > /dev/null
if [ $? -eq 0 ]; then
    echo ''
    echo "A TMUX session for ${TMUX_SESSION} already exists."
    echo "use 'tmux attach -t ${TMUX_SESSION}' to attch to it, or 'tmux kill-session -t ${TMUX_SESSION}' to kill it"
    echo ''
    exit 1
fi

if [ "$1" == '-m' ]; then
    MOCK_PRISM_ADAPTER=true
fi

clear
echo '============================================================='
echo ''
echo 'Starting TMUX ... use "^b d" to detach from session'
$MOCK_PRISM_ADAPTER && echo '(running mock prism adapter)'
echo ''
echo '============================================================='
echo ''
echo -n 'Press <Enter> to start '; read foo


tmux new -d -s $TMUX_SESSION

# enable mouse scrolling
tmux set -g mouse on

tmux split-pane -v
tmux split-pane -v

tmux select-pane -t 0
tmux send-keys 'cd ~/Dev/stargate' 'C-m'
tmux send-keys './gradlew runPrismAdapter' 'C-m'

tmux select-pane -t 1
tmux send-keys 'cd ~/Dev/stargate' 'C-m'
tmux send-keys './gradlew buildWatch' 'C-m'

tmux select-pane -t 2
tmux send-keys 'sleep 30' 'C-m'
tmux send-keys 'cd ~/Dev/stargate' 'C-m'
tmux send-keys "while (true); do if [ -f ${INDEX_FILE} ]; then break; else echo waiting for buildWatch....; sleep 5; fi; done" 'C-m'
tmux send-keys './gradlew bootrun' 'C-m'

$MOCK_PRISM_ADAPTER && {
    tmux select-pane -t 0
    tmux split-pane -h
    tmux send-keys 'cd ~/Dev/stargate' 'C-m'
    tmux send-keys './gradlew runPrismMock' 'C-m'
}

tmux attach-session -t $TMUX_SESSION

# cleanup ...
echo -n 'Clean up session? [y] '; read ans
if [ ! "$ans" = 'n' ]; then
  echo 'killing tmux session...'
  tmux kill-session -t $TMUX_SESSION
  killall java
else
  echo ""
  echo 'Use "tmux a #" to re-attach to session'
  echo 'or "tmux kill-session #" to kill it'
  echo ""
fi

