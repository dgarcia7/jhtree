package com.dg.jht.op;

import java.util.List;

import com.dg.jht.exc.HuffmanDecodingException;
import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;
import com.dg.jht.util.MessageRepository;
import com.dg.jht.util.Pair;

/**
 * Decode a message, stored as a {@link List} of
 * {@link Boolean}, and determine the original
 * input.
 */
public class HuffmanDecoder
{
	private AbstractNode huffmanTreeRoot = null;
	private final MessageRepository messageRepository = new MessageRepository();
	
	/**
	 * Create a new {@link HuffmanDecoder}.
	 * 
	 * @param rootInput
	 */
	public HuffmanDecoder(AbstractNode rootInput)
	{
		huffmanTreeRoot = rootInput;
	}
	
	/**
	 * Decode a {@link List} of {@link Boolean} to
	 * generate a {@link String} message.
	 * 
	 * @param input
	 * @return
	 * @throws HuffmanDecodingException
	 */
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
	
	/**
	 * Get the next symbol {@link String} in the given
	 * {@link Boolean} {@link List}, starting at the given
	 * index.
	 * 
	 * @param input
	 * @param index
	 * @return
	 * @throws HuffmanDecodingException
	 */
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
		throw new HuffmanDecodingException(messageRepository.buildDecodingExceptionMessage(input));
	}
}