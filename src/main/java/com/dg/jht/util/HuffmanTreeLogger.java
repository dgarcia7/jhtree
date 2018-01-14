package com.dg.jht.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;

public class HuffmanTreeLogger
{
	public void log(AbstractNode root)
	{
		Map<Integer,List<Pair<AbstractNode,List<Boolean>>>> map = new HashMap<Integer,List<Pair<AbstractNode,List<Boolean>>>>();
		printTreeHelper(root,map,1,new LinkedList<Boolean>());
		for(Integer level : map.keySet())
		{
			List<Pair<AbstractNode,List<Boolean>>> nodes = map.get(level);
			StringBuilder sb = new StringBuilder();
			sb.append("map: level ");
			sb.append(level);
			sb.append(" has nodes ");
			for(Pair<AbstractNode,List<Boolean>> pair : nodes)
			{
				AbstractNode printNode = pair.getFirst();
				if(printNode instanceof WeightNode)
				{
					sb.append("(");
					sb.append(((WeightNode)printNode).getWeight());
					sb.append(") ");
				}
				else if(printNode instanceof SymbolNode)
				{
					sb.append("[");
					sb.append(((SymbolNode)printNode).getSymbol());
					sb.append("|");
					sb.append(((SymbolNode)printNode).getWeight());
					sb.append("|");
					sb.append(getBitsAsString(pair.getSecond()));
					sb.append("] ");
				}
			}
			System.out.println(sb.toString());
		}
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
}