package org.sky.webcrawler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.sky.webcrawler.CrawlerThread;
import org.sky.webcrawler.util.AppContext;
import org.sky.webcrawler.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/19.
 */
public class stockCrawler extends CrawlerThread {

    static String queryUrl = "http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?";

    public String id;

    @Override
    protected void clean() {
    }

    @Override
    protected void cralwer() {

        String context = HttpRequestUtil.httpRequest(queryUrl + "id=" + id+"1", "", "");
        context = context.replace("callback(", "");
        context = context.replace(")", "");
        JSONObject obj = new JSONObject(context);
        JSONArray array = obj.getJSONArray("Value");
        if (array.length()== 0){
            context = HttpRequestUtil.httpRequest(queryUrl + "id=" + id+"2", "", "");
            context = context.replace("callback(", "");
            context = context.replace(")", "");
            obj = new JSONObject(context);
            array = obj.getJSONArray("Value");
        }

        if (array.length() == 0)
        {
            return;
        }

        Map<String,String> infoMap = new HashMap<>();
        /*  index
           0 : 1 上证， 2 深成
           1 : 股票id （代码）
           2 : 股票名称
           3-12 ：最近的10手 从成交价 到 最低价， 再到最高价
           13- 22: 对应的 挂单数量
           23：涨停价
           24：跌停价
           25：最新价格
           26：成交均价
           27：涨跌金额
           28：开盘价
           29：涨跌幅度
           30：今最高
           31：总手数
           32：今最低
           33： ？
           34：昨收
           35：交易金额？
           36：量比
           37：换手率
           38：？
           39：外盘
           40：内盘
           49：时间
         */
        infoMap.put("bourse", array.get(0).toString());
        infoMap.put("id", array.get(1).toString());
        infoMap.put("name", array.get(2).toString());
        infoMap.put("nowPrice", array.get(25).toString());
        infoMap.put("avgPrice", array.get(26).toString());
        infoMap.put("start", array.get(28).toString());
        infoMap.put("end", array.get(34).toString());
        infoMap.put("high", array.get(30).toString());
        infoMap.put("low", array.get(32).toString());
        infoMap.put("float", array.get(29).toString());
        infoMap.put("exchangeCount", array.get(31).toString());
        infoMap.put("exchangeRota", array.get(37).toString());
        System.out.println(array);
        AppContext.getInstance().getInfoMap().put(array.get(1).toString(), infoMap);
//        System.out.println(array.get(2));
    }

    public static  void  main(String []agrs){
//        stockCrawler stock = new stockCrawler();
//        Thread t = new Thread(stock);
//        t.start();
    }
}
