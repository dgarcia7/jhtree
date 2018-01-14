package com.dg.jht.exc;

/**
 * {@link Exception} implementation for errors encountered
 * during {@link HuffmanTree} decoding.
 */
public class HuffmanDecodingException extends Exception
{
	private static final long serialVersionUID = -7878156627544420573L;

	/**
	 * Create a new {@link HuffmanDecodingException}.
	 * 
	 * @param message
	 */
	public HuffmanDecodingException(String message)
	{
		super(message);
	}
}