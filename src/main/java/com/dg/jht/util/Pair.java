package com.dg.jht.util;

public class Pair<F,S>
{
	private F first = null;
	private S second = null;
	
	public Pair(F firstInput, S secondInput)
	{
		first = firstInput;
		second = secondInput;
	}
	
	public F getFirst()
	{
		return first;
	}
	
	public S getSecond()
	{
		return second;
	}
}