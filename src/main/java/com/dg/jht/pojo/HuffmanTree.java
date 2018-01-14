package com.dg.jht.pojo;

import java.util.List;

import com.dg.jht.exc.HuffmanDecodingException;
import com.dg.jht.exc.HuffmanEncodingException;
import com.dg.jht.op.HuffmanDecoder;
import com.dg.jht.op.HuffmanEncoder;

/**
 * A Huffman tree - this object wraps a root
 * {@link AbstractNode}, which is a binary tree
 * that links to some number of other nodes.
 */
public class HuffmanTree
{
    private AbstractNode root = null;
    private HuffmanEncoder encoder = null;
    private HuffmanDecoder decoder = null;

    /**
     * Create a new {@link HuffmanTree}.
     * 
     * @param rootInput
     */
    public HuffmanTree(AbstractNode rootInput)
    {
        root = rootInput;
        encoder = new HuffmanEncoder(root);
        decoder = new HuffmanDecoder(root);
    }
    
    /**
     * Encode the given {@link String}. Convenience method only.
     * 
     * @param input
     * @return
     * @throws HuffmanEncodingException
     */
    public List<Boolean> encode(String input) throws HuffmanEncodingException
    {
    	return encoder.encode(input);
    }
    
    /**
     * Decode the given bits.  Convenience method only.
     * 
     * @param input
     * @return
     * @throws HuffmanDecodingException
     */
    public String decode(List<Boolean> input) throws HuffmanDecodingException
    {
    	return decoder.decode(input);
    }
}