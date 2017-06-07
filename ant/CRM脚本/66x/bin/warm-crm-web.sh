echo "warm-crm-web" > logs/warm-crm-web.log

curl http://10.11.20.107:10101/WarmServlet >> logs/warm-crm-web.log
curl http://10.11.20.107:10102/WarmServlet >> logs/warm-crm-web.log
curl http://10.11.20.108:10103/WarmServlet >> logs/warm-crm-web.log
curl http://10.11.20.108:10104/WarmServlet >> logs/warm-crm-web.log
