#!/bin/bash

KEY_FILE=$1
shift
EXTRA_ARGS="$@"
cmd="ansible-playbook  --private-key=$KEY_FILE --user=ubuntu --inventory-file=hosts-csdev-3 -e ansible_ssh_user=ubuntu -vvvv site.yml ${EXTRA_ARGS}" 
echo "$cmd"
$cmd
