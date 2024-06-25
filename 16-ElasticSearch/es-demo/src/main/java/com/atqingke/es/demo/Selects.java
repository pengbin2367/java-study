package com.atqingke.es.demo;

import com.alibaba.fastjson.JSONObject;
import com.atqingke.es.demo.pojo.Article;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Map;

public class Selects {

    @Test
    public void highlightSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        SearchResponse response = client.prepareSearch("test").setTypes("article")
                .setQuery(QueryBuilders.matchQuery("title", "search"))
                .addSort("id", SortOrder.ASC)
                .highlighter(highlightBuilder)
                .setFrom(0)
                .setSize(10)
                .get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            String sourceAsString = hit.getSourceAsString();
            Article article = JSONObject.parseObject(sourceAsString, Article.class);
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            if (title != null) {
                Text[] fragments = title.getFragments();
                if (fragments != null && fragments.length > 0) {
                    String atitle = "";
                    for (Text fragment : fragments) {
                        atitle += fragment;
                    }
                    article.setTitle(atitle);
                }
            }
            System.out.println(article);
        }
        client.close();
    }

    @Test
    public void pageSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article")
                .setQuery(QueryBuilders.matchAllQuery())
                .addSort("id", SortOrder.ASC)
                .setFrom(0)
                .setSize(10)
                .get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void rangeSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article")
                .setQuery(QueryBuilders.rangeQuery("id").lte(888).gte(999)).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void compSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article")
                .setQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.fuzzyQuery("title", "search target"))
                        .should(QueryBuilders.wildcardQuery("content", "fsjdkj"))
                ).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void fuzzySelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article").setQuery(QueryBuilders.fuzzyQuery("title", "search target")).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void wildSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article").setQuery(QueryBuilders.wildcardQuery("title", "*search target*")).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void termSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article").setQuery(QueryBuilders.termQuery("title", "search target")).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void matchSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article").setQuery(QueryBuilders.matchQuery("title", "search target")).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void stringSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article").setQuery(QueryBuilders.queryStringQuery("ttt").field("title")).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void selectAll() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("test").setTypes("article").setQuery(QueryBuilders.matchAllQuery()).get();
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }

    @Test
    public void indexSelect() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        GetResponse response = client.prepareGet("test", "article", "29").get();
        System.out.println(response.getSourceAsString());
        client.close();
    }
}
