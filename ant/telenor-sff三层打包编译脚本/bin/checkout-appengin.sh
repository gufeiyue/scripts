rm -rf ../svn/sff/
rm -rf ../config/runtime-config/

echo "BEGIN TO checout!!!!"
svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/oss_proj/src ../svn/sff/src
svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/oss_proj/config ../svn/sff/config
svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/oss_proj/html ../svn/sff/html
svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/oss_proj/lib/ ../svn/sff/lib
 

svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/runtime_config/DK0.3App ../config/runtime-config/sff_app
svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/runtime_config/DK0.3Web ../config/runtime-config/sff_web
svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/runtime_config/DK0.3Inter ../config/runtime-config/sff_inter
svn co --force -r HEAD --username tangs3 --password sfframe111 http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev/OSS/runtime_config/DK0.3Exe ../config/runtime-config/sff_exe


