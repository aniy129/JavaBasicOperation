package csk.elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Program {
    public static void main(String[] args) throws UnknownHostException {
        TransportClient client = getClient();
//        SearchRequestBuilder elasticsearch = client.prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
        GetResponse response = client.prepareGet("mybook", "Book", "1").get();
        String sourceAsString = response.getSourceAsString();
        System.out.println(sourceAsString.replace("\\r\\n","\r\n"));
    }

    public static TransportClient getClient() throws UnknownHostException {
//        Settings settings = Settings.builder()
//                .put("client.transport.sniff", true)
//                .put("cluster.name", "my-application")
//                .build();
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.142.130"), 9300));
        // .addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));

// on shutdown

       // client.close();
        return client;
    }
}
