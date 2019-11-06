#!/bin/bash

set -e

# exit 0 early if we're rerunning the same commit we ran last time
if [[ $GIT_COMMIT == $GIT_PREVIOUS_SUCCESSFUL_COMMIT ]]; then
    echo "*** This commit is the same as the previous run; we're guessing this is an intentional re-run"
    exit 0
fi

# `git diff --name-only` returns the filenames that contain changes between the two listed commits
# `grep -qvf ./.build-ignore` quietly finds matches that are NOT contained in the ignore list
#    ie, changes that should trigger a build
# if the grep succeeds, it lists matches and returns 0, which means Jenkins executes the downstream build
# if the grep fails, it returns a non-zero code, which means Jenkins skips the downstream build
echo "*** Comparing changes between this commit (${GIT_COMMIT})"
echo "*** and the last successful commit (${GIT_PREVIOUS_SUCCESSFUL_COMMIT})"
if ! git diff --name-only ${GIT_COMMIT} ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} | grep -qvf ./.build-ignore; then
    echo "*** Changes appear to have only occurred in files that should not trigger a build"
    echo "*** If this seems wrong, check ./scripts/detect-relevant-changes.sh and ./scripts/.build-ignore for more info"
    exit 1
else
    echo "*** Changes detected that should trigger the build, good luck!"
fi

# Q: I manually ran a build, but it didn't trigger the downstream job, what gives?
# A: Jenkins doesn't seem to properly set the GIT_PREVIOUS_COMMIT or GIT_PREVIOUS_SUCCESSFUL_COMMIT
#    specifically when manually running a build. They seem to be set to the last commit that was
#    manually run, instead of the last commit that ran at all. Due to this, our early exit at the
#    top gets skipped, and if your changes are only in files listed in .build-ignore, your re-build
#    does nothing, sorry! Luckily, you can simply *click build again* and now it will work.
