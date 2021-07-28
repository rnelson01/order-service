#cloud-boothook
#!/bin/sh
echo "RUNNING USER DATA SCRIPT" > /home/ec2-user/user-data.txt
"sudo chkconfig tomcat8 on"
