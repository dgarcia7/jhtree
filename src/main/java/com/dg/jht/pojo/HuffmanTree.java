package com.dg.jht.pojo;

import java.util.List;

import com.dg.jht.exc.HuffmanDecodingException;
import com.dg.jht.exc.HuffmanEncodingException;
import com.dg.jht.op.HuffmanDecoder;
import com.dg.jht.op.HuffmanEncoder;

public class HuffmanTree
{
    private AbstractNode root = null;
    private HuffmanEncoder encoder = null;
    private HuffmanDecoder decoder = null;

    public HuffmanTree(AbstractNode rootInput)
    {
        root = rootInput;
        encoder = new HuffmanEncoder(root);
        decoder = new HuffmanDecoder(root);
    }
    
    public List<Boolean> encode(String input) throws HuffmanEncodingException
    {
    	return encoder.encode(input);
    }
    
    public String decode(List<Boolean> input) throws HuffmanDecodingException
    {
    	return decoder.decode(input);
    }
}