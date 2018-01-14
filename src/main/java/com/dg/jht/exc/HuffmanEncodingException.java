package com.dg.jht.exc;

/**
 * {@link Exception} implementation for errors encountered
 * during {@link HuffmanTree} encoding.
 */
public class HuffmanEncodingException extends Exception
{
	private static final long serialVersionUID = -7878156627544420573L;

	/**
	 * Create a new {@link HuffmanEncodingException}.
	 * 
	 * @param message
	 */
	public HuffmanEncodingException(String message)
	{
		super(message);
	}
}