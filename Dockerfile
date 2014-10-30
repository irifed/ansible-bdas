#
# jasongiedymin/ansible-java
#   docker build -t jasongiedymin/ansible-java .
#
# Requires:
# jasongiedymin/ansible-java
#   https://github.com/AnsibleShipyard/ansible-base-ubuntu
#

FROM jasongiedymin/ansible-base-ubuntu
MAINTAINER AnsibleShipyard

# Working dir
WORKDIR /tmp/build/ansible-java

# ADD
ADD meta $WORKDIR/meta
ADD tasks $WORKDIR/tasks
ADD tests $WORKDIR/tests
ADD vars $WORKDIR/vars

# Here we continue to use add because
# there are a limited number of RUNs
# allowed.
ADD tests/inventory /etc/ansible/hosts
ADD tests/playbook.yml $WORKDIR/playbook.yml

# Execute
RUN ansible-playbook $WORKDIR/playbook.yml -c local
