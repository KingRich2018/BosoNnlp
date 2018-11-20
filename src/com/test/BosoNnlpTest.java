package com.test;

import org.json.JSONObject;
import org.junit.Test;
import com.boso.Bosonnlp;
import com.enumBean.TypeBean;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.util.Util;

public class BosoNnlpTest {
	/**
	 * 非负面概率，负面概率
	 * @author wangbo
	 */

	@Test
	public void sentiment() throws UnirestException {
		String [] data =  new String[]{"中国共产党", "美好的世界"};
		System.out.println(Bosonnlp.determine(TypeBean.SENTIMENT.getDesc(),data));
		//第一个值为非负面概率，第二个值为负面概率，两个值相加和为 1
		//[[0.6519134382562579,0.34808656174374203],[0.92706110187413,0.07293889812586994]]
	}
	/**
	 * 命名实体识别（NER）是指识别文本中具有特定意义的实体，主要包括人名、地名、机构名、
	 * 专有名词等。命名实体识别是信息提取、问答系统、句法分析、机器翻译等应用领域的重要基础工具
	 * @author wangbo
	 */
	@Test
	public void ner() throws UnirestException {
		String [] data =  new String[]{"美国超级计算机蝉联冠军，每分钟却需消耗15吨水"};
		System.out.println(Bosonnlp.ner(data));
	}
	/**
	 * 依存文法分析核心思想为将一个线性描写的句子表述为成员之间的搭配与驱动关系
	 * @author wangbo
	 */
	@Test
	public void depparser() throws UnirestException {
		String [] data =  new String[]{"邓紫棋谈男友林宥嘉：我觉得我比他唱得好"};
		System.out.println(Bosonnlp.depparser(data));
		//[{"head":[6,6,3,4,5,1,-1,6,6],
		//"role":["SBJ","MNR","VMOD","DEC","NMOD","POBJ","ROOT","VMOD","OBJ"],
		//"tag":["PN","P","AD","VA","DEC","NN","VV","AS","NN"],"word":["我","以","最","快","的","速度","吃","了","午饭"]}]
	}
	
	/**
	 * 以将文本自动进行关键词分析，给出每个词语相应的权重
	 * @author wangbo
	 */
	@Test
	public void keywords() throws UnirestException {
		String [] data =  new String[]{"病毒式媒体网站：让新闻迅速蔓延"};
		System.out.println(Bosonnlp.determine(TypeBean.KEYWORDS.getDesc(),data));
		//[[[0.5686631749811326,"蔓延"],
		//[0.5671956747680966,"病毒"],
		//[0.33993439662135194,"迅速"],
		//[0.30642011458238383,"网站"],
		//[0.26392731183346607,"新闻"],[0.23807884315568398,"媒体"],[0.12876489756725826,"式"],[0.0504282185215189,"让"]]]

	}
	/**
	 * 将新闻文本归类到预设的 14 个分类当中
		0体育1教育2财经3社会4娱乐5军事6国内7	科技8	互联网9房产10国际11女人12	汽车13游戏
	 * @author wangbo
	 */
	@Test
	public void classify() throws UnirestException {
		String [] data =  new String[]{ "俄否决安理会谴责叙军战机空袭阿勒颇平民",
			    "邓紫棋谈男友林宥嘉：我觉得我比他唱得好",
			    "Facebook收购印度初创公司"};
		String str = Bosonnlp.determine(TypeBean.CLASSIFY.getDesc(),data);
		String classify[] = str.substring(str.indexOf("[")+1, str.indexOf("]")).split(",");
		for (String string : classify) {
			System.out.println(Util.classify(Integer.parseInt(string)));
		}
		//[5,4,8]
	}
	
	@Test
	public void suggest() throws UnirestException {
		String  data =  "\"傻逼\"";
		System.out.println(Bosonnlp.suggest(TypeBean.SUGGEST.getDesc(),data));
	}
	/**
	 * 将中文时间描述转换为三种标准的时间格式的时间字符串：1) 时间点（timestamp，表示某一具体时间时间描述）; 2) 时间量（timedelta，表示时间的增量的时间描述）; 3)时间区间（timespan，大于一天的有具体起始和结束时间点的时间描述），以方便处理。在转换时以尽量保留原始时间描述中所含信息为原则。
	 * @author wangbo
	 */
	@Test
	public void time() throws UnirestException {
		System.out.println(Bosonnlp.time("2013年二月二十八日下午四点三十分二十九秒"));
		//{"type":"timestamp","timestamp":"2013-02-28 16:30:29"}
	}
	/*
	@Test
	public void summary() throws UnirestException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title","")
		          .put("content", "东南亚在线零售平台 Shopee 周一宣布，双十一期间平台的订单超过了1100万，是去年同期的4.5倍。\r\n" + 
		          		"\r\n" + 
		          		"Shopee 首席商务官周俊杰表示，今年双十一的销售额是有史以来最高的一次，同时用户的参与也非常积极。然而，与阿里巴巴不同，该公司并没有透露今年双十一的 GMV。\r\n" + 
		          		"\r\n" + 
		          		"Shopee 成立于2015年，是纽交所上市公司 Sea Group 的电子商务部门，最近取得了现象级的增长。Shopee 最初是一个C2C平台，后来逐渐在上面引入了零售商。Lazada 是该公司在东南亚地区最大的竞争对手是，前者现在归阿里巴巴所有。\r\n" + 
		          		"\r\n" + 
		          		"在双十一当天，Shopee 上的中国品牌都十分畅销。该公司表示，跨国品牌的销售额高达平时的9倍甚至更多。此外，智能手机的跨境出货量增长了20倍。\r\n" + 
		          		"\r\n" + 
		          		"智能手机的热销也表明手机用户越来越多。Shopee 透露，90%以上的订单都来自移动设备用户。根据地区电商对比网站 iPrice，双十一的促销活动主要针对手机用户，这一点可能也使得人们更倾向于使用手机下单。");
		System.out.println(Bosonnlp.summary(TypeBean.SUMMARY.getDesc(),jsonObject));
	}*/
	
}