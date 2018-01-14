package com.dg.jht.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;

public class MessageRepository
{
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
	
	public String getBitsAsString(List<Boolean> bits)
	{
		StringBuilder sb = new StringBuilder();
		for(Boolean bit : bits)
		{
			sb.append(getBitAsString(bit));
		}
		
		return sb.toString();
	}
	
	public String getBitAsString(Boolean bit)
	{
		return (bit == true ? "1" : "0");
	}
	
	public String buildNodesMessage(List<AbstractNode> nodes)
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(AbstractNode node : nodes)
    	{
    		sb.append(buildNodeMessage(node,new LinkedList<Boolean>()));
    	}
    	
    	return sb.toString();
    }
	
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
}