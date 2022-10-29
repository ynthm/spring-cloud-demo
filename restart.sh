FILE_NAME=$1
echo $FILE_NAME
pid="$(ps -ef | grep $FILE_NAME.jar | grep -v grep | awk '{print $2}')"
if [ -n "$pid" ]; then
  kill -2 "$pid"
fi

nohup java -jar /opt/baseapp/$FILE_NAME/$FILE_NAME.jar --spring.cloud.bootstrap.location=$FILE_NAME/bootstrap.yml &> $FILE_NAME.out&