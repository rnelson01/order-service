#!/bin/bash
catalina.sh start
sleep 10

echo 'generate load'
if [[ -z "${url}" ]]; then
  NODE_NAME="http://localhost:8080"
else
  NODE_NAME="${url}"
fi

if [[ -z "${buggy}" ]]; then
  buggy=false
else
  buggy="${buggy}"
fi

if [[ -z "${run_time_secs}" ]]; then
  DURATION=14400
else
  DURATION="${run_time_secs}"
fi

echo "node: " $NODE_NAME "duration: " $DURATION "buggy: " $buggy
CURRENT_TIME_START=`date +%s`
curl -v --connect-timeout 1 -m 5 --data "name=wings&password=wings123&password2=wings123" $NODE_NAME/todolist/register > /dev/null
curl -v --connect-timeout 1 -m 5 -b cookies.txt -c cookies.txt --data "name=wings&password=wings123" $NODE_NAME/todolist/requestLogin > /dev/null
curl -v --connect-timeout 1 -m 5 -L -b cookies.txt -c cookies.txt $NODE_NAME/todolist/inside/display > /dev/null
TIME=`date +%s`
END_TIME=$(($CURRENT_TIME_START+$DURATION))
until [ $TIME -gt $END_TIME ]
do
	RAND=$(( ( $RANDOM % 10 )  + 1 ))
	curl -v --connect-timeout 1 -m 5 --data "name=wings&password=wings123&password2=wings123" $NODE_NAME/todolist/inside/$RAND > /dev/null
    curl -v --connect-timeout 1 -m 5 $NODE_NAME/todolist/inside/load?priority=1&task=task1 > /dev/null
    curl -v --connect-timeout 1 -m 5 $NODE_NAME/todolist/exception > /dev/null
	if [ $RAND -lt 3 ]; then
		curl -v --connect-timeout 1 -m 5 --data "name=wings&password=wings123&password2=wings123&throwError=$buggy" $NODE_NAME/todolist/register > /dev/null
	elif [ $RAND -lt 5 ]; then
		curl -v --connect-timeout 1 -m 5 -b cookies.txt -c cookies.txt --data "name=wings&password=wings123" $NODE_NAME/todolist/requestLogin > /dev/null
	elif [ $RAND -lt 7 ]; then
		curl -v --connect-timeout 1 -m 5 -L -b cookies.txt -c cookies.txt $NODE_NAME/todolist/inside/display > /dev/null
    else
		curl -v --connect-timeout 1 -m 5 --data "name=wings&password=wings123&password2=wings123" $NODE_NAME/todolist/register > /dev/null
		curl -v --connect-timeout 1 -m 5 -b cookies.txt -c cookies.txt --data "name=wings&password=wings123" $NODE_NAME/todolist/requestLogin > /dev/null
		curl -v --connect-timeout 1 -m 5 -L -b cookies.txt -c cookies.txt $NODE_NAME/todolist/inside/display > /dev/null
    	curl --connect-timeout 1 -m 5 -L -b cookies.txt -c cookies.txt "$NODE_NAME/todolist/inside/addTask?priority=1&task=task1" > /dev/null
	fi
	sleep 1
    TIME=`date +%s`
done
rm cookies.txt || true

while true; do sleep 2; done