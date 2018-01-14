package com.dg.jht.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;

/**
 * Repository for message {@link String}s used in
 * the application.
 */
public class MessageRepository
{
	/**
	 * Build a message for the given {@link AbstractNode}; it
	 * is assumed that this node is a root node with a
	 * number of sub-nodes that will also be traversed
	 * and added to the message.
	 * 
	 * @param root
	 * @return
	 */
	public String buildTreeMessage(AbstractNode root)
	{
		StringBuilder treeStringBuilder = new StringBuilder();
		treeStringBuilder.append("huffman tree contents:");
		Map<Integer,List<Pair<AbstractNode,List<Boolean>>>> map = new HashMap<Integer,List<Pair<AbstractNode,List<Boolean>>>>();
		printTreeHelper(root,map,1,new LinkedList<Boolean>());
		for(Integer level : map.keySet())
		{
			List<Pair<AbstractNode,List<Boolean>>> nodes = map.get(level);
			StringBuilder levelStringBuilder = new StringBuilder();
			levelStringBuilder.append("map: level ");
			levelStringBuilder.append(level);
			levelStringBuilder.append(" has nodes ");
			for(Pair<AbstractNode,List<Boolean>> pair : nodes)
			{
				AbstractNode printNode = pair.getFirst();
				levelStringBuilder.append(buildNodeMessage(printNode,pair.getSecond()));
			}
			treeStringBuilder.append("\n\t\t");
			treeStringBuilder.append(levelStringBuilder.toString());
		}
		return treeStringBuilder.toString();
	}
	
	/**
	 * Assist with building a tree message.
	 * 
	 * @param node
	 * @param map
	 * @param level
	 * @param bits
	 */
	private void printTreeHelper(AbstractNode node, Map<Integer,List<Pair<AbstractNode,List<Boolean>>>> map, int level, List<Boolean> bits)
	{
		if(node != null)
		{
			updateMap(map,node,level,bits);
			if(node instanceof WeightNode)
			{
				WeightNode weightNode = (WeightNode)node;
				
				List<Boolean> leftList = new LinkedList<Boolean>();
				leftList.addAll(bits);
				leftList.add(false);
				printTreeHelper(weightNode.getLeft(),map,level+1,leftList);
				
				List<Boolean> rightList = new LinkedList<Boolean>();
				rightList.addAll(bits);
				rightList.add(true);
				printTreeHelper(weightNode.getRight(),map,level+1,rightList);
			}
		}
	}
	
	/**
	 * Update the {@link Map} used when building a tree message.
	 * 
	 * @param map
	 * @param node
	 * @param level
	 * @param bits
	 */
	private void updateMap(Map<Integer,List<Pair<AbstractNode,List<Boolean>>>> map, AbstractNode node, int level, List<Boolean> bits)
	{
		List<Pair<AbstractNode,List<Boolean>>> nodes = map.get(level);
		if (nodes == null)
		{
			nodes = new LinkedList<Pair<AbstractNode,List<Boolean>>>();
			map.put(level, nodes);
		}
		Pair<AbstractNode,List<Boolean>> pair = new Pair<AbstractNode,List<Boolean>>(node,bits);
		nodes.add(pair);
	}
	
	/**
	 * Convert the given {@link Boolean} {@link List} into
	 * a printable string with binary bits.
	 * 
	 * @param bits
	 * @return
	 */
	public String getBitsAsString(List<Boolean> bits)
	{
		StringBuilder sb = new StringBuilder();
		for(Boolean bit : bits)
		{
			sb.append(getBitAsString(bit));
		}
		
		return sb.toString();
	}
	
	/**
	 * Convert the given {@link Boolean} into a printable binary bit.
	 * 
	 * @param bit
	 * @return
	 */
	public String getBitAsString(Boolean bit)
	{
		return (bit == true ? "1" : "0");
	}
	
	/**
	 * Build a message for the given {@link List} of {@link AbstractNode}s.
	 * 
	 * @param nodes
	 * @return
	 */
	public String buildNodesMessage(List<AbstractNode> nodes)
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(AbstractNode node : nodes)
    	{
    		sb.append(buildNodeMessage(node,new LinkedList<Boolean>()));
    	}
    	
    	return sb.toString();
    }
	
	/**
	 * Build a node message, including the bits that it's symbol
	 * will be converted to, if applicable.
	 * 
	 * @param node
	 * @param bits
	 * @return
	 */
	private String buildNodeMessage(AbstractNode node, List<Boolean> bits)
	{
		StringBuilder sb = new StringBuilder();
		if(node instanceof WeightNode)
		{
			sb.append("(");
			sb.append(((WeightNode)node).getWeight());
			sb.append(") ");
		}
		else if(node instanceof SymbolNode)
		{
			sb.append("[");
			sb.append(((SymbolNode)node).getSymbol());
			sb.append("|");
			sb.append(((SymbolNode)node).getWeight());
			sb.append("|");
			sb.append(getBitsAsString(bits));
			sb.append("] ");
		}
		return sb.toString();
	}
	
	/**
	 * Build a message for encoding exceptions.
	 * 
	 * @param symbol
	 * @return
	 */
	public String buildEncodingExceptionMessage(String symbol)
	{
		return "no bitset could be found for symbol [" + symbol + "].";
	}
	
	/**
	 * Build a message for decoding exceptions.
	 * 
	 * @param input
	 * @return
	 */
	public String buildDecodingExceptionMessage(List<Boolean> input)
	{
		return "no symbol could be found for bitset [" + input.toString() + "].";
	}
}