package io.github.utkarshcmu.lazer;

import java.util.Iterator;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.json.simple.JSONObject;

import io.github.utkarshcmu.input.InputBeam;
import io.github.utkarshcmu.output.OutputBeam;
import io.github.utkarshcmu.utils.BeamReader;

public class Lazer {
    
	private InputBeam[] inputBeams;
	private OutputBeam[] outputBeams;
	private JavaStreamingContext jssc;
	
	public Lazer(String beamFileLocation) {
		
		BeamReader reader = new BeamReader(beamFileLocation);
		JSONObject beam = reader.getBeam();
		JSONObject beamSettings = (JSONObject) beam.get("beam.settings");
		JSONObject sparkSettings = (JSONObject) beam.get("spark.settings");
		JSONObject inputSettings = (JSONObject) beam.get("input.settings");
		JSONObject outputSettings = (JSONObject) beam.get("output.settings");
		this.setJavaStreamingContext(beamSettings, sparkSettings);
		this.setInputBeams(inputSettings);
		this.setOutputBeams(outputSettings);
	
	}
	
	private void setJavaStreamingContext(JSONObject beamSettings, JSONObject sparkSettings) {
		
		String appName = (String) beamSettings.get("name");
		SparkConf sparkConf = new SparkConf().setAppName(appName);
		int duration = (Integer) beamSettings.get("duration");
	    this.jssc = new JavaStreamingContext(sparkConf, Durations.seconds(duration));
	    Set<?> settings = sparkSettings.keySet();
	    Iterator<?> iter = settings.iterator();
	    while (iter.hasNext()) {
	    	String key = (String) iter.next();
	    	sparkConf.set(key, (String)sparkSettings.get(key));
	    }
	    
	}
	
	private void setInputBeams(JSONObject inputSettings) {
		
	}
	
	private void setOutputBeams(JSONObject outputSettings) {
		
	}
	
	public InputBeam[] getInputBeams() {
		return inputBeams;
	}
	
	public OutputBeam[] getOutputBeams() {
		return outputBeams;
	}

	public void startAndAwaitTermination() {
		this.jssc.start();
		try {
			this.jssc.awaitTermination();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
