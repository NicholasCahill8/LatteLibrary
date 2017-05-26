package nicky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

//CF says this is OK (CF)


public class MapLatte 
{
	
	public static void main(String[] args) throws IOException 
	{
		String location1;
		String location2;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Pick location 1:");
		location1 = scan.nextLine().replaceAll(" ", "");

		System.out.println("Pick location 2:");
		location2 = scan.nextLine().replaceAll(" ", "");
		
		
		
		System.out.println("it would take " + time(location1, location2) + " to get from " + location(location1) + " to " + location(location2) + " by car");
		
		System.out.println("The distance between " + location(location1) + " to " + location(location2) + " is " + distance(location1, location2));		
	}
	
	public static String distance(String origin, String destination) throws IOException
	{
		//download the content:
		String s = "";



		URL oracle = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + origin + "&destinations=" + destination + "&key=");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(oracle.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			s+=inputLine;
		in.close();


		JSONObject obj = new JSONObject(s);


		String dist = obj.getJSONArray("rows")
				.getJSONObject(0)
				.getJSONArray("elements")
				.getJSONObject(0)
				.getJSONObject("distance")
				.getString("text")
				.toString();

		return dist;
	}


	public static String time(String origin, String destination) throws IOException
	{
		//download the content:
		String s = "";



		URL oracle = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + origin + "&destinations=" + destination + "&key=");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(oracle.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			s+=inputLine;
		in.close();


		JSONObject obj = new JSONObject(s);


		String res = obj.getJSONArray("rows")
				.getJSONObject(0)
				.getJSONArray("elements")
				.getJSONObject(0)
				.getJSONObject("duration")
				.getString("text")
				.toString();

		return res;
	}

	public static String location(String place) throws IOException
	{
		//download the content:
		String s = "";
		String location;

		URL oracle = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + place + "&destinations=" + 10075 + "&key=");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(oracle.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			s+=inputLine;
		in.close();


		JSONObject obj = new JSONObject(s);

		
		location = obj.getJSONArray("origin_addresses")
				.toString()
				.replace("[", "")
				.replace("]", "")
				.replace("\"", "");
		
	return location;
	}
}

