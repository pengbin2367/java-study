package com.atqingke.es.demo;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;

public class IndexAndMappingTest {

    @Test
    public void indexAndMapping() throws Exception {
//        // 设置 Elasticsearch 服务器地址和端口
//        String hostname = "localhost"; // Elasticsearch 地址
//        int port = 9200; // Elasticsearch 端口
//
//        // 设置用户名和密码
//        String username = "es01"; // Elasticsearch 用户名
//        String password = "pengbin"; // Elasticsearch 密码
//
//        // 创建 RestClient 实例，设置用户名和密码
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials(username, password));
//
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                                new HttpHost(hostname, port, "http"))
//                        .setHttpClientConfigCallback(httpClientBuilder -> {
//                            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                            return httpClientBuilder;
//                        })
//        );
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        client.admin().indices().prepareCreate("test").get();
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder
                .startObject()
                    .startObject("article")
                        .startObject("properties")
                            .startObject("id")
                                .field("type", "long")
                            .endObject()
                            .startObject("title")
                                .field("type", "text")
                                .field("analyzer", "ik_max_word")
                            .endObject()
                            .startObject("id")
                                .field("type", "text")
                                .field("analyzer", "ik_max_word")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();
        PutMappingRequest request = Requests.putMappingRequest("test").type("article").source(builder);
        client.admin().indices().putMapping(request);
        // 关闭客户端连接
        client.close();
    }
}
