package org.sky.webcrawler.util;

import org.sky.webcrawler.BaseThreadManager;
import org.sky.webcrawler.stockCrawler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/19.
 */
public class AppContext {

    private AppContext(){
        BaseThreadManager crawler = new BaseThreadManager();
        for (int i = 600000; i < 600999; i++){
            stockCrawler xx = new stockCrawler();
            xx.id = i+"";
            crawler.runCrawler(xx, null);
        }
    };

    public static AppContext getInstance(){
        return AppContextHolder.appContext;
    }

    static class AppContextHolder {
        static AppContext appContext = new AppContext();
    }

    Map<String, Map<String,String>> infoMap = new HashMap<>();

    public Map<String, Map<String, String>> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<String, Map<String, String>> infoMap) {
        this.infoMap = infoMap;
    }
}
