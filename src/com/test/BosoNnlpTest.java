package com.test;

import org.json.JSONObject;
import org.junit.Test;
import com.boso.Bosonnlp;
import com.enumBean.TypeBean;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.util.Util;

public class BosoNnlpTest {
	/**
	 * �Ǹ�����ʣ��������
	 * @author wangbo
	 */

	@Test
	public void sentiment() throws UnirestException {
		String [] data =  new String[]{"�й�������", "���õ�����"};
		System.out.println(Bosonnlp.determine(TypeBean.SENTIMENT.getDesc(),data));
		//��һ��ֵΪ�Ǹ�����ʣ��ڶ���ֵΪ������ʣ�����ֵ��Ӻ�Ϊ 1
		//[[0.6519134382562579,0.34808656174374203],[0.92706110187413,0.07293889812586994]]
	}
	/**
	 * ����ʵ��ʶ��NER����ָʶ���ı��о����ض������ʵ�壬��Ҫ������������������������
	 * ר�����ʵȡ�����ʵ��ʶ������Ϣ��ȡ���ʴ�ϵͳ���䷨���������������Ӧ���������Ҫ��������
	 * @author wangbo
	 */
	@Test
	public void ner() throws UnirestException {
		String [] data =  new String[]{"������������������ھ���ÿ����ȴ������15��ˮ"};
		System.out.println(Bosonnlp.ner(data));
	}
	/**
	 * �����ķ���������˼��Ϊ��һ��������д�ľ��ӱ���Ϊ��Ա֮��Ĵ�����������ϵ
	 * @author wangbo
	 */
	@Test
	public void depparser() throws UnirestException {
		String [] data =  new String[]{"������̸������嶼Σ��Ҿ����ұ������ú�"};
		System.out.println(Bosonnlp.depparser(data));
		//[{"head":[6,6,3,4,5,1,-1,6,6],
		//"role":["SBJ","MNR","VMOD","DEC","NMOD","POBJ","ROOT","VMOD","OBJ"],
		//"tag":["PN","P","AD","VA","DEC","NN","VV","AS","NN"],"word":["��","��","��","��","��","�ٶ�","��","��","�緹"]}]
	}
	
	/**
	 * �Խ��ı��Զ����йؼ��ʷ���������ÿ��������Ӧ��Ȩ��
	 * @author wangbo
	 */
	@Test
	public void keywords() throws UnirestException {
		String [] data =  new String[]{"����ʽý����վ��������Ѹ������"};
		System.out.println(Bosonnlp.determine(TypeBean.KEYWORDS.getDesc(),data));
		//[[[0.5686631749811326,"����"],
		//[0.5671956747680966,"����"],
		//[0.33993439662135194,"Ѹ��"],
		//[0.30642011458238383,"��վ"],
		//[0.26392731183346607,"����"],[0.23807884315568398,"ý��"],[0.12876489756725826,"ʽ"],[0.0504282185215189,"��"]]]

	}
	/**
	 * �������ı����ൽԤ��� 14 �����൱��
		0����1����2�ƾ�3���4����5����6����7	�Ƽ�8	������9����10����11Ů��12	����13��Ϸ
	 * @author wangbo
	 */
	@Test
	public void classify() throws UnirestException {
		String [] data =  new String[]{ "���������Ǵ�����ս����Ϯ������ƽ��",
			    "������̸������嶼Σ��Ҿ����ұ������ú�",
			    "Facebook�չ�ӡ�ȳ�����˾"};
		String str = Bosonnlp.determine(TypeBean.CLASSIFY.getDesc(),data);
		String classify[] = str.substring(str.indexOf("[")+1, str.indexOf("]")).split(",");
		for (String string : classify) {
			System.out.println(Util.classify(Integer.parseInt(string)));
		}
		//[5,4,8]
	}
	
	@Test
	public void suggest() throws UnirestException {
		String  data =  "\"ɵ��\"";
		System.out.println(Bosonnlp.suggest(TypeBean.SUGGEST.getDesc(),data));
	}
	/**
	 * ������ʱ������ת��Ϊ���ֱ�׼��ʱ���ʽ��ʱ���ַ�����1) ʱ��㣨timestamp����ʾĳһ����ʱ��ʱ��������; 2) ʱ������timedelta����ʾʱ���������ʱ��������; 3)ʱ�����䣨timespan������һ����о�����ʼ�ͽ���ʱ����ʱ�����������Է��㴦����ת��ʱ�Ծ�������ԭʼʱ��������������ϢΪԭ��
	 * @author wangbo
	 */
	@Test
	public void time() throws UnirestException {
		System.out.println(Bosonnlp.time("2013����¶�ʮ���������ĵ���ʮ�ֶ�ʮ����"));
		//{"type":"timestamp","timestamp":"2013-02-28 16:30:29"}
	}
	/*
	@Test
	public void summary() throws UnirestException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title","")
		          .put("content", "��������������ƽ̨ Shopee ��һ������˫ʮһ�ڼ�ƽ̨�Ķ���������1100����ȥ��ͬ�ڵ�4.5����\r\n" + 
		          		"\r\n" + 
		          		"Shopee ��ϯ������ܿ��ܱ�ʾ������˫ʮһ�����۶�����ʷ������ߵ�һ�Σ�ͬʱ�û��Ĳ���Ҳ�ǳ�������Ȼ�����밢��ͰͲ�ͬ���ù�˾��û��͸¶����˫ʮһ�� GMV��\r\n" + 
		          		"\r\n" + 
		          		"Shopee ������2015�꣬��Ŧ�������й�˾ Sea Group �ĵ��������ţ����ȡ�������󼶵�������Shopee �����һ��C2Cƽ̨�������������������������̡�Lazada �Ǹù�˾�ڶ����ǵ������ľ��������ǣ�ǰ�����ڹ鰢��Ͱ����С�\r\n" + 
		          		"\r\n" + 
		          		"��˫ʮһ���죬Shopee �ϵ��й�Ʒ�ƶ�ʮ�ֳ������ù�˾��ʾ�����Ʒ�Ƶ����۶�ߴ�ƽʱ��9���������ࡣ���⣬�����ֻ��Ŀ羳������������20����\r\n" + 
		          		"\r\n" + 
		          		"�����ֻ�������Ҳ�����ֻ��û�Խ��Խ�ࡣShopee ͸¶��90%���ϵĶ����������ƶ��豸�û������ݵ������̶Ա���վ iPrice��˫ʮһ�Ĵ������Ҫ����ֻ��û�����һ�����Ҳʹ�����Ǹ�������ʹ���ֻ��µ���");
		System.out.println(Bosonnlp.summary(TypeBean.SUMMARY.getDesc(),jsonObject));
	}*/
	
}