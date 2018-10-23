#!/bin/sh
#我的启动程序脚本
export JAVA_HOME=/usr/share/java/jdk1.8.0_151
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:$JRE_HOME/lib
export PATH=${JAVA_HOME}/bin:$PATH
app_name='DubboProvider.jar'
app_log=${app_name}'.log'
app_dir='/home/csk/'

start()
{
	echo ${app_name}'正在启动……'
		echo '启动'${app_name}'时间:'+ $(date) > ${app_log}
	cd ${app_dir}
	ln=`ps -df |grep -w  ${app_name}|grep java | wc -l`
#echo ${ln}
		if [[ ${ln} -ge 1 ]]
			then
				echo  ${app_name} '已启动，请勿重复执行！'
		else
			java -jar ${app_name} >> ${app_log} &
				echo ${app_name} '启动成功！'
				fi
}

stop()
{
	line=`ps -df |grep -w  ${app_name} | grep java | wc -l`
#echo line=${line}
		if [[ ${line} -eq  1 ]]
			then
				echo '正在停止进程……'
				pid=$(ps -df | grep -w ${app_name} | grep java| awk  '{print $2}')
				kill ${pid}
	echo '已执行 kill '${pid}
	while true
		do
			rs=`check`
#     echo ${rs}
				if [[ ${rs} -eq 1 ]]
					then
						echo  $(date) ' ' ${app_name} ' 正在停止……'
				else
					echo  $(date) ' ' ${app_name} ' 已停止！'
						break
						fi
						sleep 1
						done
						elif [ ${line} -lt  1 ]
						then
						echo '进程未运行！'
						elif [ ${line} -gt  1 ]
						then
						echo '存在多个进程,请检查是否出现异常……'
						ps -df | grep -w ${app_name}
		else
			echo '出现异常'
				fi
}

status()
{
	ln=`ps -df |grep -w  ${app_name}|grep java | wc -l`
		if [[ ${ln} -ge 1 ]]
			then
				ps -df | grep -w ${app_name} |grep java
		else
			echo '进程 ' ${app_name} '未运行！'
				fi
}

check(){
	ln=`ps -df |grep -w  ${app_name}|grep java | wc -l`
		if [[ ${ln} -ge 1 ]]
			then
				echo 1
		else
			echo 0
				fi
}
case "$1" in
start)
start
;;
stop)
stop
;;
restart)
stop
start
;;
status)
status
;;
*)
echo "Usage: $SCRIPT_NAME {start|stop|restart|status}" >&2
exit 1
;;
esac

exit 0

