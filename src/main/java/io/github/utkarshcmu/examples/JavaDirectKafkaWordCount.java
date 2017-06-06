package io.github.utkarshcmu.examples;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;

import io.github.utkarshcmu.input.InputBeam;
import io.github.utkarshcmu.lazer.Lazer;
import scala.Tuple2;

public class JavaDirectKafkaWordCount {
	
	private static final Pattern SPACE = Pattern.compile(" ");
	
	public static void main(String args[]) {
		Lazer laser = new Lazer("exampleBeams/events.json");
		InputBeam[] inputBeams = laser.getInputBeams();
		JavaDStream<String> lines = inputBeams[0].read();
		JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(SPACE.split(x)).iterator());
	    JavaPairDStream<String, Integer> wordCount = words.mapToPair(s -> new Tuple2<>(s, 1)).reduceByKey((i1, i2) -> i1 + i2);
	    wordCount.print();
		laser.startAndAwaitTermination();
	}

}
