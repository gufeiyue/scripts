echo "starting..."

echo "start scp marketingcampaign ... "     
sshpass -p   "aiapp"   scp ../dest/ear/marketingcampaign.ear  aiapp@10.1.226.87:/home/aiapp/marketingcampaign/app

echo "start scp order ... "                  
sshpass -p   "aiapp"   scp ../dest/ear/order.ear  aiapp@10.1.226.87:/home/aiapp/order/app

echo "start scp productoffering ... "                     
sshpass -p   "aiapp"   scp ../dest/ear/productoffering.ear  aiapp@10.1.226.87:/home/aiapp/productoffering/app

echo "start scp customer ... "                   
sshpass -p   "aiapp"   scp ../dest/ear/customer.ear  	aiapp@10.1.226.87:/home/aiapp/customer/app

echo "start scp marketingresource ... "                   
sshpass -p   "aiapp"   scp ../dest/ear/marketingresource.ear  aiapp@10.1.226.87:/home/aiapp/marketingresource/app

echo "start scp product ... "                     
sshpass -p   "aiapp"   scp ../dest/ear/product.ear  aiapp@10.1.226.87:/home/aiapp/product/app

echo "start scp resourcespecification ... "                      
sshpass -p   "aiapp"   scp ../dest/ear/resourcespecification.ear aiapp@10.1.226.87:/home/aiapp/resourcespecification/app

echo "start scp authframe ... "                  
sshpass -p   "aiapp"   scp ../dest/ear/authframe.ear  	aiapp@10.1.226.87:/home/aiapp/sessionmanager/app

echo "start scp integrated ... "                  
sshpass -p   "aiapp"   scp ../dest/ear/integrated.ear  	aiapp@10.1.226.87:/home/aiapp/integrated/app

echo "start scp businesscommon ... "
sshpass -p   "aiapp"   scp ../dest/ear/businesscommon.ear aiapp@10.1.226.87:/home/aiapp/businesscommon/app

echo "start scp hubframework ... "                  
sshpass -p   "aihub"   scp ../dest/ear/hubframework.ear  aihub@10.1.226.86:/home/aihub/lib

echo "start scp nMsm ... "
sshpass -p   "aiapp"   scp ../dest/ear/nMsm.ear  aiapp@10.1.226.87:/home/aiapp/msm/app

echo "starts scp  agentview..."
sshpass -p "aiapp" scp ../dest/ear/agentviewpaas.ear aiapp@10.1.226.87:/home/aiapp/AgentView/app

echo "starts scp  customerservice..."
sshpass -p "aiapp" scp ../dest/ear/customerservice.ear aiapp@10.1.226.87:/home/aiapp/customerservice/app

echo "starts scp  pushc..."
sshpass -p "aiapp" scp ../dest/ear/pushc.ear aiapp@10.1.226.87:/home/aiapp/pushc/app

echo "starts scp  crmsaas..."
sshpass -p "aiapp" scp ../dest/ear/crmsaas.ear aiapp@10.1.226.87:/home/aiapp/crm/app

backup(){
echo "start backup ..."
backuplist=`ls ../dest/ear/*.ear | awk -F'/' '{print($4)}'`
ctime=`date +%Y%m%d%H%M`
for i in ${backuplist}
do
	mv ../dest/ear/${i} ../backup/${ctime}.${i}
done
find ../backup/ -mtime +2 -name "*.ear" -exec rm -rf {} \;
}

backup $

echo "finish ..."
