package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源.
 *
 * @author Angel(QQ : 412887952)
 * @version v.0.1
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /*
     * 代码中的determineCurrentLookupKey方法取得一个字符串，
     * 该字符串将与配置文件中的相应字符串进行匹配以定位数据源，配置文件，即applicationContext.xml文件中需要要如下代码：(non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}