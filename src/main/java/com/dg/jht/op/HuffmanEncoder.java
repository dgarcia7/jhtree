package com.dg.jht.op;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dg.jht.exc.HuffmanEncodingException;
import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;
import com.dg.jht.util.HuffmanTreeLogger;

public class HuffmanEncoder
{
	private AbstractNode huffmanTreeRoot = null;
	private Map<String,List<Boolean>> mapSymbolToBits = null;
	private HuffmanTreeLogger htLogger = new HuffmanTreeLogger();
	
	public HuffmanEncoder(AbstractNode rootInput)
	{
		huffmanTreeRoot = rootInput;
		mapSymbolToBits = buildMap(huffmanTreeRoot);
	}
	
	private Map<String,List<Boolean>> buildMap(AbstractNode root)
	{
		Map<String,List<Boolean>> map = new HashMap<String,List<Boolean>>();
		List<Boolean> emptyBitSet = new LinkedList<Boolean>();
		buildMapRecursive(root,emptyBitSet,map,1);
		htLogger.log(root);
		return map;
	}
	
	private void buildMapRecursive(AbstractNode node, List<Boolean> bitSetSoFar, Map<String,List<Boolean>> mapSoFar, int level)
	{
		if(node instanceof WeightNode)
		{
			WeightNode weightNode = (WeightNode)node;
			
			List<Boolean> bitSetLeft = new LinkedList<Boolean>();
			bitSetLeft.addAll(bitSetSoFar);
			bitSetLeft.add(false);
			
			List<Boolean> bitSetRight = new LinkedList<Boolean>();
			bitSetRight.addAll(bitSetSoFar);
			bitSetRight.add(true);
			
			buildMapRecursive(weightNode.getLeft(),bitSetLeft,mapSoFar,level+1);
			buildMapRecursive(weightNode.getRight(),bitSetRight,mapSoFar,level+1);
		}
		else if(node instanceof SymbolNode)
		{
			SymbolNode symbolNode = (SymbolNode)node;
			mapSoFar.put(symbolNode.getSymbol(), bitSetSoFar);
		}
	}
	
	public List<Boolean> encode(String input) throws HuffmanEncodingException
    {
		List<Boolean> output = new LinkedList<Boolean>();
    	for(int inputIndex = 0; inputIndex < input.length(); inputIndex++)
    	{
    		String symbol = String.valueOf(input.charAt(inputIndex));
    		List<Boolean> symbolEncoded = mapSymbolToBits.get(symbol);
    		if(symbolEncoded == null)
    		{
    			throw new HuffmanEncodingException("no bitset could be found for symbol [" + symbol + "].");
    		}
    		else
    		{
    			for(Boolean bit : symbolEncoded)
    			{
    				output.add(bit);
    			}
    		}
    	}
    	return output;
    }
}