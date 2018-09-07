package com.example.springbootmongo.config.datasource.loadBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoundRobin {
    private static Integer pos = 0;

    public static String getDataSource() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> dataSouceMap =
                new HashMap<String, Integer>();
        dataSouceMap.putAll(DataSourceMap.dataSouceWeightMap);

        // 取得数据源List
        Set<String> keySet = dataSouceMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        String dataSource = null;
        synchronized (pos) {
            if (pos >= keySet.size())
                pos = 0;
            dataSource = keyList.get(pos);
            pos++;
        }
        return dataSource;
    }
    
    public static void main(String[] args) {
        for (int i = 1; i < 30; i++) {
            System.out.println(getDataSource());
        }
    }
}
