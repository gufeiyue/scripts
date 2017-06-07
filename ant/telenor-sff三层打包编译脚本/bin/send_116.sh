#!/bin/sh

sshpass -p "aiveris-scm" scp ../dest/sff/sff_web.war aiveris@10.1.229.116:./work/veris662-sr0.3.2-rd-test-jar/tools/deploy_91/Cdeploy/packages
sshpass -p "aiveris-scm" scp ../dest/sff/sff_app_0.ear aiveris@10.1.229.116:./work/veris662-sr0.3.2-rd-test-jar/tools/deploy_91/Cdeploy/packages
sshpass -p "aiveris-scm" scp ../dest/sff/config_0.jar aiveris@10.1.229.116:./work/veris662-sr0.3.2-rd-test-jar/tools/deploy_91/Cdeploy/packages
sshpass -p "aiveris-scm" scp ../dest/sff/sff_configext.jar aiveris@10.1.229.116:./work/veris662-sr0.3.2-rd-test-jar/tools/deploy_91/Cdeploy/packages
sshpass -p "aiveris-scm" scp ../dest/sff/sff_lib.jar aiveris@10.1.229.116:./work/veris662-sr0.3.2-rd-test-jar/tools/deploy_91/Cdeploy/packages
