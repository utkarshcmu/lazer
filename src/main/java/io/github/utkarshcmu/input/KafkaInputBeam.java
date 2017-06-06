package io.github.utkarshcmu.input;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kafka.serializer.StringDecoder;
import scala.Tuple2;

public class KafkaInputBeam implements InputBeam{

	private JavaDStream<String> beam;
	
	KafkaInputBeam(JSONObject config, JavaStreamingContext jssc) {
		
		JSONArray topicArray = (JSONArray) config.get("topics");
		Set<String> topicsSet = new HashSet<String>();
		for (Object topic : topicArray) {
			topicsSet.add((String) topic);
		}
	    Map<String, String> kafkaParams = new HashMap<>();
	    JSONObject params = (JSONObject) config.get("params");
	    Set<?> set = params.keySet();
	    Iterator<?> iter = set.iterator();
	    while(iter.hasNext()) {
	    	String key = (String) iter.next();
	    	kafkaParams.put(key, (String) params.get(key));
	    }

	    JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(jssc,
	        String.class,
	        String.class,
	        StringDecoder.class,
	        StringDecoder.class,
	        kafkaParams,
	        topicsSet
	    );

	    this.beam = messages.map(Tuple2::_2);
	    
	}
	
	public JavaDStream<String> read() {
		return beam;	
	}

}
