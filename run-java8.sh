#!/bin/bash

$JAVA_HOME/bin/java -cp $JAVA_HOME/lib/tools.jar:build/libs/java-agent-hello-world.jar test.AgentInjector build/libs/java-agent-hello-world.jar "$@"
