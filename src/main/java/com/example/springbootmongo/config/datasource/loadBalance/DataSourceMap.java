package com.example.springbootmongo.config.datasource.loadBalance;

import java.util.HashMap;

public class DataSourceMap {
    // 待路由的数据库列表，Key代表数据库，Value代表该数据库的权重
    public static HashMap<String, Integer> dataSouceWeightMap =
            new HashMap<String, Integer>();

    static {
        dataSouceWeightMap.put("slave1", 2);
        dataSouceWeightMap.put("slave2", 1);
    }
}
