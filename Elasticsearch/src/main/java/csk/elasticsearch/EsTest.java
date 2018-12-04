package csk.elasticsearch;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

public class EsTest {
    private static TransportClient client;

    @BeforeClass
    public static void init() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.142.130"), 9300));
    }

    @AfterClass
    public static void destroy() {
        client.close();
    }

    @Test
    public void search(){
        HighlightBuilder highlightBuilder1 = new HighlightBuilder();
        highlightBuilder1.preTags("【【");
        highlightBuilder1.postTags("】】");
        highlightBuilder1.field("ContentDescription");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("【【");
        highlightBuilder.postTags("】】");
        highlightBuilder.field("Title");

        SearchRequestBuilder srb1 = client
                .prepareSearch()
                .setQuery(QueryBuilders
                        .matchQuery("Title","编程")
                        .analyzer("ik_smart"))
                .highlighter(highlightBuilder);

        SearchRequestBuilder srb2 = client
                .prepareSearch()
                .setQuery(QueryBuilders
                        .matchQuery("ContentDescription", "编程")
                        .analyzer("ik_smart"))
                .highlighter(highlightBuilder1);

        MultiSearchResponse sr = client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .get();
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
            System.out.println(response.toString());
        }
        System.out.println("total="+nbHits);
    }
}
