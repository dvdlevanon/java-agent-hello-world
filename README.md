
A simple java agent that does nothing but print a line to the output stream every second.

# Building

./gradlew build

# Running

- For java 8 and below use `./run-java8.sh`
- For java 11 and above use `./run-java11.sh`

The above scripts will try to attach to all the running JVMs, in order to attach to a specific JVM, provide a filter token like found in the below example:
```
./run-java11.sh MainClass
```

The token is matched against the VM display name as specified here: https://docs.oracle.com/en/java/javase/11/docs/api/jdk.attach/com/sun/tools/attach/VirtualMachineDescriptor.html#displayName()

In any case, a log line with the provided filter + the actual names will be printed to the console.
