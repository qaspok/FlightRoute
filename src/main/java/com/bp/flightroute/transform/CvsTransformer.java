package com.bp.flightroute.transform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.bp.flightroute.model.Flights;

public class CvsTransformer {

	public static Flights process(InputStream cvs) {
		
		System.out.println(getStringFromInputStream(cvs));
		return null;
	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}
}
