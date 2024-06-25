package com.atqingke.es.demo;

import com.alibaba.fastjson.JSONObject;
import com.atqingke.es.demo.pojo.Article;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;

public class InsertDataTest {

    @Test
    public void insertData() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (int i = 0; i < 1000000; i++) {
            Article article = new Article();
            article.setId(10086L);
            article.setTitle(i + ",askjdkfjksjkdjfkjskjfjksjd");
            article.setContent(i + ",凯撒交流发电机离开家阿斯科利将大幅拉升的介绍两款发动机绿卡介绍这款多了几分颗粒剂案例思考发电机看来吉安市离开家东法兰克骄傲颗粒剂抗衰老的几分看来吉安市里卡多几分绿卡数据库老东家");
            bulkRequestBuilder.add(client.prepareIndex("test", "article", i + "").setSource(JSONObject.toJSONString(article), XContentType.JSON));
            if (i % 100 == 0) {
                bulkRequestBuilder.execute().actionGet();
            }
        }
        bulkRequestBuilder.execute().actionGet();
        client.close();
    }
}
