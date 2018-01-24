package com.example.assignment.utility;

import java.util.Collection;
import java.util.Map;

public class Util {
	/**
	 * This method returns true if the collection is null or is empty.
	 * @param collection
	 * @return true | false
	 */
	public static boolean isEmpty( Collection<?> collection ){
		if( collection == null || collection.isEmpty() ){
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns true of the map is null or is empty.
	 * @param map
	 * @return true | false
	 */
	public static boolean isEmpty( Map<?, ?> map ){
		if( map == null || map.isEmpty() ){
			return true;
		}
		return false;
	}
}
