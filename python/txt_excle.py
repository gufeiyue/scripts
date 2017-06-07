#!/usr/bin/python  
#-*- coding:utf-8 -*-
import simplejson
import re
import linecache
import xlwt

def find_replace(old_txt, new_txt):
	rr = linecache.getlines(old_txt)
	f = open(new_txt, 'w')
	for r in rr:
		r = r.decode("utf-8")
		infos = re.sub(u'\uff1a', ':', r)
		for info in infos:
			f.write(info.encode('utf-8'))
	f.close()
#################################################################################

def find_lines(name,line):
	name = name.decode("utf-8")
	pattern = re.compile(u"\u2605\u5c0f\u533a(.*)")
	news = pattern.findall(name)
	#print type(news)
	if news:
		return line
	else:
		pass
			
def gettxt2(new_txt, tables):
	aa = linecache.getlines(new_txt)[1:100000]
	xiaoqu_line1 = 0
	line = 0 
	num = 0
	for a in aa:
		xiaoqu_line2 = find_lines(a, line)		
		line = line + 1
		if xiaoqu_line2 >= 0:
			num = num + 1
			getinfo(num, new_txt, xiaoqu_line1, xiaoqu_line2, tables)
			xiaoqu_line1 = xiaoqu_line2		
		else:
			pass


def getinfo(num, new_txt, xiaoqu_line1, xiaoqu_line2, tables):

	bb = linecache.getlines(new_txt)[xiaoqu_line1:xiaoqu_line2]
	print "============="
	print num
	print "============="
	for b in bb:
		#print b
		pattern_xiaoqu = re.compile(u"\u2605\u5c0f\u533a:(.*)")
		xiao_qu = find_info(b, pattern_xiaoqu)
		if xiao_qu:
		 	print xiao_qu
		 	insert_info(tables, num, 0, xiao_qu)
		else:
		 	pass
	
		pattern_ban_kuai = re.compile(u"\u677f\u5757:(.*)")
		ban_kuai = find_info(b, pattern_ban_kuai)
		if ban_kuai:
		 	print ban_kuai
		 	insert_info(tables, num, 1, ban_kuai)
		else:
		 	pass
	      
		pattern_huan_xian = re.compile(u"\u73af\u7ebf:(.*)")      
		huan_xian = find_info(b, pattern_huan_xian)
		if huan_xian:
			print type(huan_xian)
			insert_info(tables, num, 2, huan_xian)
		else:
			pass
	      	      
		pattern_mian_ji = re.compile(u"\u9762\u79ef:(.*)")      
		mian_ji = find_info(b, pattern_mian_ji)
		if mian_ji:
			print type(mian_ji)
			insert_info(tables, num, 3, mian_ji)
		else:
			pass
	      
		pattern_zong_jia = re.compile(u"\u603b\u4ef7:(.*)")      
		zong_jia = find_info(b, pattern_zong_jia)
		if zong_jia:
			print zong_jia
			insert_info(tables, num, 4, zong_jia)
		else:
			pass
	      
		pattern_dan_jia = re.compile(u"\u5355\u4ef7:(.*)")      
		dan_jia = find_info(b, pattern_dan_jia)
		if dan_jia:
			print dan_jia
			insert_info(tables, num, 5, dan_jia)
		else:
			pass
	      
		pattern_de_jia = re.compile(u"\u5e95\u4ef7:(.*)")      
		de_jia = find_info(b, pattern_de_jia)
		if de_jia:
			print de_jia
			insert_info(tables, num, 6, de_jia)
		else:
			pass
	      
		pattern_gu_jia = re.compile(u"\u4f30\u4ef7:(.*)")      
		gu_jia = find_info(b, pattern_gu_jia)
		if gu_jia:
			print gu_jia
			insert_info(tables, num, 7, gu_jia)
		else:
			pass
	      
		pattern_lian_jia_gua_pai_dian_xing_jia = re.compile(u"\u94fe\u5bb6\u6302\u724c\u5178\u578b\u4ef7:(.*)")      
		lian_jia_gua_pai_dian_xing_jia = find_info(b, pattern_lian_jia_gua_pai_dian_xing_jia)
		if lian_jia_gua_pai_dian_xing_jia:
			print lian_jia_gua_pai_dian_xing_jia
			insert_info(tables, num, 8, lian_jia_gua_pai_dian_xing_jia)
		else:
			pass
	      
		pattern_shui_fei_qing_kuang = re.compile(u"\u7a0e\u8d39\u60c5\u51b5:(.*)")      
		shui_fei_qing_kuang = find_info(b, pattern_shui_fei_qing_kuang)
		if shui_fei_qing_kuang:
			print shui_fei_qing_kuang
			insert_info(tables, num, 9, shui_fei_qing_kuang)
		else:
			pass
	      
		pattern_hu_xing = re.compile(u"\u6237\u578b:(.*)")      
		hu_xing = find_info(b, pattern_hu_xing)
		if hu_xing:
			print hu_xing
			insert_info(tables, num, 10, hu_xing)
		else:
			pass
	      
		pattern_wei_zhi = re.compile(u"\u4f4d\u7f6e:(.*)")      
		wei_zhi = find_info(b, pattern_wei_zhi)
		if wei_zhi:
			print wei_zhi
			insert_info(tables, num, 11, wei_zhi)
		else:
			pass
	      
		pattern_lou_ceng = re.compile(u"\u697c\u5c42:(.*)")      
		lou_ceng = find_info(b, pattern_lou_ceng)
		if lou_ceng:
			print lou_ceng
			insert_info(tables, num, 12, lou_ceng)
		else:
			pass
	      
		pattern_fang_ling = re.compile(u"\u623f\u9f84:(.*)")      
		fang_ling = find_info(b, pattern_fang_ling)
		if fang_ling:
			print fang_ling
			insert_info(tables, num, 13, fang_ling)
		else:
			pass
	      
		pattern_zhuang_xiu = re.compile(u"\u88c5\u4fee:(.*)")      
		zhuang_xiu = find_info(b, pattern_zhuang_xiu)
		if zhuang_xiu:
			print zhuang_xiu
			insert_info(tables, num, 14, zhuang_xiu)
		else:
			pass
	      
		pattern_chao_xiang = re.compile(u"\u671d\u5411:(.*)")      
		chao_xiang = find_info(b, pattern_chao_xiang)
		if chao_xiang:
			print chao_xiang
			insert_info(tables, num, 15, chao_xiang)
		else:
			pass
	      
		pattern_cai_guang = re.compile(u"\u91c7\u5149:(.*)")      
		cai_guang = find_info(b, pattern_cai_guang)
		if cai_guang:
			print cai_guang
			insert_info(tables, num, 16, cai_guang)
		else:
			pass
	      
		pattern_shi_ye_jing_guan = re.compile(u"\u89c6\u91ce\u666f\u89c2:(.*)")      
		shi_ye_jing_guan = find_info(b, pattern_shi_ye_jing_guan)
		if shi_ye_jing_guan:
			print shi_ye_jing_guan
			insert_info(tables, num, 17, shi_ye_jing_guan)
		else:
			pass
	      
		pattern_fu_kuan_yao_qiu = re.compile(u"\u4ed8\u6b3e\u8981\u6c42:(.*)")      
		fu_kuan_yao_qiu = find_info(b, pattern_fu_kuan_yao_qiu)
		if fu_kuan_yao_qiu:
			print fu_kuan_yao_qiu
			insert_info(tables, num, 18, fu_kuan_yao_qiu)
		else:
			pass
	      
		pattern_mai_fang_xin_tai = re.compile(u"\u5356\u623f\u5fc3\u6001:(.*)")      
		mai_fang_xin_tai = find_info(b, pattern_mai_fang_xin_tai)
		if mai_fang_xin_tai:
			print mai_fang_xin_tai
			insert_info(tables, num, 19, mai_fang_xin_tai)
		else:
			pass
	      
		pattern_qu_hua_su_du = re.compile(u"\u53bb\u5316\u901f\u5ea6:(.*)")      
		qu_hua_su_du = find_info(b, pattern_qu_hua_su_du)
		if qu_hua_su_du:
			print qu_hua_su_du
			insert_info(tables, num, 20, qu_hua_su_du)
		else:
			pass
	      
		pattern_di_tie = re.compile(u"\u5730\u94c1:(.*)")      
		di_tie = find_info(b, pattern_di_tie)
		if di_tie:
			print di_tie
			insert_info(tables, num, 21, di_tie)
		else:
			pass
	      
		pattern_xue_qu = re.compile(u"\u5b66\u533a:(.*)")      
		xue_qu = find_info(b, pattern_xue_qu)
		if xue_qu:
			print xue_qu
			insert_info(tables, num, 22, xue_qu)
		else:
			pass
	      
		pattern_can_kao_zu_jin = re.compile(u"\u53c2\u8003\u79df\u91d1:(.*)")      
		can_kao_zu_jin = find_info(b, pattern_can_kao_zu_jin)
		if can_kao_zu_jin:
			print can_kao_zu_jin
			insert_info(tables, num, 23, can_kao_zu_jin)
		else:
			pass
	      
		pattern_hu_kou = re.compile(u"\u6237\u53e3:(.*)")      
		hu_kou = find_info(b, pattern_hu_kou)
		if hu_kou:
			print hu_kou
			insert_info(tables, num, 24, hu_kou)
		else:
			pass
	      
		pattern_an_jie = re.compile(u"\u67e5\u5c01:(.*)")      
		an_jie = find_info(b, pattern_an_jie)
		if an_jie:
			print an_jie
			insert_info(tables, num, 25, an_jie)
		else:
			pass
	      
		pattern_liang_dian = re.compile(u"\u4eae\u70b9:(.*)")      
		liang_dian = find_info(b, pattern_liang_dian)
		if liang_dian:
			print liang_dian
			insert_info(tables, num, 26, liang_dian)
		else:
			pass
	      
		pattern_ying_shang = re.compile(u"\u786c\u4f24:(.*)")      
		ying_shang = find_info(b, pattern_ying_shang)
		if ying_shang:
			print ying_shang
			insert_info(tables, num, 27, ying_shang)
		else:
			pass




#################################################################################
def find_info(name, condition):
	name = name.decode("utf_8")
	infos = condition.findall(name)
	info1 = "000"
	for info in infos:
		if info:
		 	return info
		else:
			return info1

		
####################################################################################


def create_excle():
	files = xlwt.Workbook(encoding='utf-8')
	return files

def create_table(files):
	tables = files.add_sheet('gufy')
	return tables

def insert_info(tables, num1, num2, info):
	tables.write(num1, num2, info)


def save_excle(files):
	files.save('gufy.xls')


def init(tables):
	insert_info(tables, 0, 0, u'小区11')
	insert_info(tables, 0, 1, u'板块')
	insert_info(tables, 0, 2, u'环线')
	insert_info(tables, 0, 3, u'面积')
	insert_info(tables, 0, 4, u'总价')
	insert_info(tables, 0, 5, u'单价')
	insert_info(tables, 0, 6, u'底价')
	insert_info(tables, 0, 7, u'估价')
	insert_info(tables, 0, 8, u'链家挂牌典型价')
	insert_info(tables, 0, 9, u'税费情况')
	insert_info(tables, 0, 10, u'户型')
	insert_info(tables, 0, 11, u'位置')
	insert_info(tables, 0, 12, u'楼层')
	insert_info(tables, 0, 13, u'房龄')
	insert_info(tables, 0, 14, u'装修')
	insert_info(tables, 0, 15, u'朝向')
	insert_info(tables, 0, 16, u'采光')
	insert_info(tables, 0, 17, u'视野景观')
	insert_info(tables, 0, 18, u'付款要求')
	insert_info(tables, 0, 19, u'卖房心态')
	insert_info(tables, 0, 20, u'去化速度')
	insert_info(tables, 0, 21, u'地铁')
	insert_info(tables, 0, 22, u'学区')
	insert_info(tables, 0, 23, u'参考租金')
	insert_info(tables, 0, 24, u'户口')
	insert_info(tables, 0, 25, u'按揭/抵押/查封')
	insert_info(tables, 0, 26, u'亮点')
	insert_info(tables, 0, 27, u'硬伤')
####################################################################################


if __name__ == '__main__':
	old_txt = 'fangyuan.txt'
	new_txt = 'test2.txt'
	files = create_excle()
	tables = create_table(files)
	init(tables)
	gettxt2(new_txt, tables)
	save_excle(files)

	#find_replace('fangyuan.txt')
	
