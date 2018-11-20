package com.enumBean;

public enum TypeBean {
	SENTIMENT("url", "http://api.bosonnlp.com/sentiment/analysis"),
    NER("url", "http://api.bosonnlp.com/ner/analysis"),
    KEYWORDS("url", "http://api.bosonnlp.com/keywords/analysis"),
    CLASSIFY("url", "http://api.bosonnlp.com/classify/analysis"),
    DEPPARSER("url", "http://api.bosonnlp.com/depparser/analysis"),
    SUGGEST("url", "http://api.bosonnlp.com/suggest/analysis"),
    TIME("url", "http://api.bosonnlp.com/time/analysis"),
    SUMMARY("url", "http://api.bosonnlp.com/summary/analysis"),
	ACCEPT("Accept", "application/json"),
	TOKEN("X-Token", "uGt2_zOd.29992.fGMSqFobRzdK"),
	TYPE("Content-Type", "application/json");

	private final String desc;
    private final String code;
    TypeBean(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
