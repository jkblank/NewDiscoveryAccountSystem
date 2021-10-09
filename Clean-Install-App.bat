@ECHO OFF
start /B /wait mvn clean install -DhaltOnFailure=false
