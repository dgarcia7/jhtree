package com.dg.jht.op;

import java.util.List;

import com.dg.jht.exc.HuffmanDecodingException;
import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;
import com.dg.jht.util.Pair;

public class HuffmanDecoder
{
	private AbstractNode huffmanTreeRoot = null;
	
	public HuffmanDecoder(AbstractNode rootInput)
	{
		huffmanTreeRoot = rootInput;
	}
	
	public String decode(List<Boolean> input) throws HuffmanDecodingException
    {
    	StringBuilder output = new StringBuilder();
    	
    	int index = 0;
    	while(index < input.size())
    	{
    		Pair<String,Integer> pair = getNextSymbol(input,index);
    		output.append(pair.getFirst());
    		index = pair.getSecond();
    	}
    	
    	return output.toString();
    }
	
	private Pair<String,Integer> getNextSymbol(List<Boolean> input, int index) throws HuffmanDecodingException
	{
		AbstractNode currentNode = huffmanTreeRoot;
		while(index <= input.size())
		{
			if(currentNode instanceof WeightNode)
			{
				WeightNode weightNode = (WeightNode)currentNode;
				Boolean direction = input.get(index);
				if(direction)
				{
					currentNode = weightNode.getRight();
				}
				else
				{
					currentNode = weightNode.getLeft();
				}
				index++;
			}
			else if(currentNode instanceof SymbolNode)
			{
				SymbolNode symbolNode = (SymbolNode)currentNode;
				return new Pair<String,Integer>(symbolNode.getSymbol(),index);
			}
		}
		throw new HuffmanDecodingException("no symbol could be found for bitset [" + input.toString() + "].");
	}
}