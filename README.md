ansible-bdas
============

Ansible recipes for Berkeley Data Analytics Stack deployment

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
