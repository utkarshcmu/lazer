package io.github.utkarshcmu.input;

import org.apache.spark.streaming.api.java.JavaDStream;

public interface InputBeam {

	public JavaDStream<String> read();
	
}
