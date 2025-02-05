package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.jfree.data.xy.XYSeries;

import com.allendowney.thinkdast.Profiler.Timeable;

public class ProfileListAdd {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		profileArrayListAddEnd();
		//profileArrayListAddBeginning();
		//profileLinkedListAddBeginning();
		//profileLinkedListAddEnd();
	}

	/**
	 * Characterize the run time of adding to the end of an ArrayList
	 */
	public static void profileArrayListAddEnd() {
		List<String> list = new ArrayList<>();
		setUpProfile(list, list::add);
	}
	
	/**
	 * Characterize the run time of adding to the beginning of an ArrayList
	 */
	public static void profileArrayListAddBeginning() {

		List<String> list = new ArrayList<>();
		setUpProfile(list, s -> list.add(0, s));
	}

	/**
	 * Characterize the run time of adding to the beginning of a LinkedList
	 */
	public static void profileLinkedListAddBeginning() {
		List<String> list = new LinkedList<>();
		setUpProfile(list, s -> list.add(0, s));
	}

	/**
	 * Characterize the run time of adding to the end of a LinkedList
	 */
	public static void profileLinkedListAddEnd() {
		List<String> list = new LinkedList<>();
		setUpProfile(list, list::add);
	}

	/**
	 * Runs the profiles and displays results.
	 * 
	 * @param timeable
	 * @param startN
	 * @param endMillis
	 */
	private static void runProfiler(String title, Timeable timeable, int startN, int endMillis) {
		Profiler profiler = new Profiler(title, timeable);
		XYSeries series = profiler.timingLoop(startN, endMillis);
		profiler.plotResults(series);
	}

	private static void setUpProfile(List<String> list, Consumer<String> function) {

		Timeable timeable = new Timeable() {

			@Override
			public void setup(int n) { }

			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					function.accept("a string");
				}
			}
		};
		int startN = 4000;
		int endMillis = 1000;
		runProfiler("ArrayList add end", timeable, startN, endMillis);
	}

}