package com.dg.jht.util;

/**
 * Simple immutable pair implementation for internal use.
 * 
 * @param <F>
 * @param <S>
 */
public class Pair<F,S>
{
	private F first = null;
	private S second = null;
	
	/**
	 * Create a new Pair.
	 * 
	 * @param firstInput
	 * @param secondInput
	 */
	public Pair(F firstInput, S secondInput)
	{
		first = firstInput;
		second = secondInput;
	}
	
	/**
	 * Get the first generic type.
	 * 
	 * @return
	 */
	public F getFirst()
	{
		return first;
	}
	
	/**
	 * Get the second generic type.
	 * 
	 * @return
	 */
	public S getSecond()
	{
		return second;
	}
}