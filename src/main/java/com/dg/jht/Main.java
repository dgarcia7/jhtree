package com.dg.jht;

import java.util.List;

import com.dg.jht.build.HuffmanTreeBuilder;
import com.dg.jht.exc.HuffmanDecodingException;
import com.dg.jht.exc.HuffmanEncodingException;
import com.dg.jht.pojo.HuffmanTree;
import com.dg.jht.util.HuffmanTreeLogger;

public class Main
{
	private static final HuffmanTreeBuilder builder = new HuffmanTreeBuilder();
	private static final HuffmanTreeLogger htLogger = new HuffmanTreeLogger();
	
	public static void main(String []args) throws HuffmanEncodingException, HuffmanDecodingException
	{
		String value = "this is a test string that will be used to test encoding and decoding of huffman trees!";
		System.out.println(String.format("testing huffman with input [%s]...", value));
		
		HuffmanTree tree = builder.build(value);
		System.out.println(String.format("built huffman tree."));
		
		List<Boolean> encoded = tree.encode(value);
		System.out.println(String.format("encoded: [%s]",htLogger.getBitsAsString(encoded)));
		
		String decoded = tree.decode(encoded);
		System.out.println(String.format("decoded: [%s]",decoded));
	}
}