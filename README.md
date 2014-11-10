ansible-bdas
============

### Description

This is a set of Ansible recipes for Berkeley Data Analytics Stack (and some other software) deployment on a cluster.

Ansible-BDAS includes:
- Cloudera Hadoop Distribution CDH 5
- Hive
- Spark
- Mesos
- Tachyon
- OpenMPI
- Cassandra 

In addition, it contains a role to prepare cluster for executing training excercises from [AMPCamp Big Data mini course](http://ampcamp.berkeley.edu/big-data-mini-course/) and from [Databrix Spark Training](https://databricks-training.s3.amazonaws.com/index.html).

### Installation

This project contains some external Ansible playbooks (Java, Scala, SBT) as submodules, 
so clone with `--recursive`:
```
$ git clone --recursive https://github.com/irifed/ansible-bdas
```

Alternatively, clone as usual and add a couple of additional steps:
```
$ git clone https://github.com/irifed/ansible-bdas
$ cd ansible-bdas
$ git submodule init
$ git submodule update
```

### Usage
This Ansible playbook can be run over existing set of virtual or bare metal hosts, i.e. hosts should be provisioned in advance by other tools. IP addresses and roles for hosts should be described in Ansible inventory file, for example the following inventory file describes a cluster with one master and five worker hosts:
```
master ansible_ssh_host=xxx.xxx.xxx.xxx ansible_ssh_port=22
host1 ansible_ssh_host=xxx.xxx.xxx.xxx ansible_ssh_port=22
host2 ansible_ssh_host=xxx.xxx.xxx.xxx ansible_ssh_port=22
host3 ansible_ssh_host=xxx.xxx.xxx.xxx ansible_ssh_port=22
host4 ansible_ssh_host=xxx.xxx.xxx.xxx ansible_ssh_port=22
host5 ansible_ssh_host=xxx.xxx.xxx.xxx ansible_ssh_port=22

[master]
master

[workers]
host1
host2
host3
host4
host5
```
All hosts should be accessible from the machine where you are going to run Ansible by SSH with key-based authentication (without password). All hosts must be accessible using same key.

To run full playbook issue the following command from your local machine:
```
ansible-playbook \
  --private-key=<path to private key> \
  --user=root \
  --inventory-file=<path to inventory file> \
  --extra-vars=\{"ansible_ssh_user":"root"\} \
  -vvvv site.yml
```
Here `--private-key` should contain path to private SSH key which is required for password-less SSH to all hosts.

File `site.yml` is a main BDAS playbook which defines components to be installed.

By default, `site.yml` will install some basic tools for convenience (Tmux, Vim, etc), set up `/etc/hosts` file so cluster components will know each other, set up password-less SSH for user `root` among all hosts in a cluster, install Java, Scala, SBT. Master host will become HDFS namenode, remaining hosts will be HDFS datanodes. After that standalone Spark will be installed on a cluster and will be accessible for applications via `spark://master:7077` Spark URL. Spark Web UI can be reached at `https://<master external ip>:8080`.

Optionally this playbook can also install Mesos and deploy Spark on Mesos. Mesos Web UI can be reached at `https://<master external ip>:5050`. Finally, Hive and Tachyon can be installed as well.
If you need Mesos, Hive and Tachyon, you can enable them by removing comment symbol `#`  in front of corresponding tasks in `site.yml` , e.g. removing `#` in these lines will enable Tachyon installation:
```
#- name: deploy Tachyon
#  hosts: all
#  roles:
#      - tachyon_standalone
#  any_errors_fatal: yes
```

To upload data for running AMPCamp excercises to the cluster use the following command:
```
```

Note that AMPCamp and Tachyon roles require some data to be downloaded from SoftLayer Object Storage, so you have to set up SoftLayer Swift credentials in `group_vars/swift.yml` file if you want to use these roles. 
Template for `swift.yml` is available at `group_vars/swift.yml.template`:
```
swift_api_url: "https://sjc01.objectstorage.softlayer.net/auth/v1.0/"
swift_user: "EDIT_HERE"
swift_key: "EDIT_HERE"
```
`swift_user` and `swift_key` parameters can be found in SoftLayer control portal in Object Storage page, but usually they match SL API username and SL API key.