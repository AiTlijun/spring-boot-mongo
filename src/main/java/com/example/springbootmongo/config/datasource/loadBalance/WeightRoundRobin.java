package com.example.springbootmongo.config.datasource.loadBalance;

import java.util.*;

public class WeightRoundRobin {
    private static Integer pos = 0;

    public static String getDataSource() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> dataSouceMap =
                new HashMap<String, Integer>();
        dataSouceMap.putAll(DataSourceMap.dataSouceWeightMap);

        // 取得数据源List
        Set<String> keySet = dataSouceMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> dataSourceList = new ArrayList<String>();
        while (iterator.hasNext()) {
            String dataSource = iterator.next();
            int weight = dataSouceMap.get(dataSource);
            for (int i = 0; i < weight; i++)
                dataSourceList.add(dataSource);
        }

        String dataSourceWeight;
        synchronized (pos) {
            if (pos > keySet.size())
                pos = 0;
            dataSourceWeight = dataSourceList.get(pos);
            pos++;
        }
        return dataSourceWeight;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 30; i++) {
            System.out.println(getDataSource());
        }
    }
}
