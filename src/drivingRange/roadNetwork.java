package drivingRange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class roadNetwork {
	static int limit = 1000000;
	static int[] arrCities = new int[limit];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String[] cityRoads = reader.readLine().split(" ");
		int cities = Integer.parseInt(cityRoads[0]);
		int roads = Integer.parseInt(cityRoads[1]);
		
		for (int i = 1; i <= cities; i++) {
			arrCities[i] = i;
		}

		int[][] roadsNetwork = new int[roads][3];
		for(int i = 0; i < roads; i++) {
			String[] roadNetworkLine = reader.readLine().split(" ");
			roadsNetwork[i][0] = Integer.parseInt(roadNetworkLine[0]);
			roadsNetwork[i][1] = Integer.parseInt(roadNetworkLine[1]);
			roadsNetwork[i][2] = Integer.parseInt(roadNetworkLine[2]);
		}

		Arrays.sort(roadsNetwork, Comparator. comparingInt(arr -> Integer.valueOf(arr[2])));

		int countRangeCities = 0;
		ArrayList<Integer> minRange = new ArrayList<Integer>();
		for (int i = 0; i < roadsNetwork.length; i++) {
			if (merge(roadsNetwork[i][0], roadsNetwork[i][1])) {
				minRange.add(roadsNetwork[i][2]);
				countRangeCities++;
			}
		}
		if (countRangeCities == cities-1) {
			System.out.println(Collections.max(minRange));
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
	
	public static boolean merge(int firstCity, int secondCity) {
		int firstParent = getParent(firstCity);
		int secondParent = getParent(secondCity);
		if (firstParent != secondParent) {
			arrCities[secondParent] = firstParent;
			return true;
		}
		return false;
	}

	public static int getParent(int cityIndex) {
		if (arrCities[cityIndex] == cityIndex) {
			return cityIndex;
		} else {
			arrCities[cityIndex] = getParent(arrCities[cityIndex]);
			return arrCities[cityIndex];
		}
	}
}