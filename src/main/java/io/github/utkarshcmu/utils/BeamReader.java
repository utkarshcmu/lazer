package io.github.utkarshcmu.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BeamReader {
	
	private JSONObject beam;
	
	public BeamReader(String fileLocation) {
		
		JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileLocation));
            this.beam = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
	}
	
	public JSONObject getBeam() {
		
		return beam;
		
	}
}
