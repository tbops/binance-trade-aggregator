#!/bin/sh
./node_exporter-1.5.0.linux-386/node_exporter &
./bin/module &
wait
