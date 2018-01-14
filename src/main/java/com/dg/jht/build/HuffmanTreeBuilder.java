package com.dg.jht.build;

import com.dg.jht.pojo.HuffmanTree;
import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import com.dg.jht.util.HuffNodeComparator;

public class HuffmanTreeBuilder
{
	public HuffmanTree build(String input)
    {
        Map<String,Long> frequencyMap = (new FrequencyAnalyzer(input)).generateFrequencyMap();
        return build(frequencyMap);      
    }
    
    public HuffmanTree build(Map<String,Long> frequencyMap)
    {
    	AbstractNode root = buildNodes(frequencyMap);
        return new HuffmanTree(root);
    }
    
    private AbstractNode buildNodes(Map<String,Long> frequencyMap)
    {
    	List<AbstractNode> nodes = new LinkedList<AbstractNode>();
        for(String symbol : frequencyMap.keySet())
        {
            Long frequency = frequencyMap.get(symbol);
            AbstractNode node = new SymbolNode(symbol,frequency);
            nodes.add(node);
        }

        while(nodes.size() > 1)
        {
            Collections.sort(nodes, new HuffNodeComparator());
            //System.out.println(buildMessage(nodes));
            AbstractNode firstNode = nodes.remove(0);
            AbstractNode secondNode = nodes.remove(0);
            AbstractNode combinedNode = new WeightNode(firstNode,secondNode);
            nodes.add(combinedNode);
        }
        //System.out.println(buildMessage(nodes));
        return nodes.get(0);
    }
    
    private String buildMessage(List<AbstractNode> nodes)
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(AbstractNode node : nodes)
    	{
    		if(node instanceof WeightNode)
    		{
    			WeightNode weightNode = (WeightNode)node;
    			sb.append("(");
    			sb.append(weightNode.getWeight());
    			sb.append(") ");
    		}
    		else if(node instanceof SymbolNode)
    		{
    			SymbolNode symbolNode = (SymbolNode)node;
    			sb.append("[");
    			sb.append(symbolNode.getSymbol());
    			sb.append("|");
    			sb.append(symbolNode.getWeight());
    			sb.append("] ");
    		}
    	}
    	
    	return sb.toString();
    }
}
