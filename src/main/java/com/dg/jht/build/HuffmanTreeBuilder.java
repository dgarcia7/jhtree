package com.dg.jht.build;

import com.dg.jht.pojo.HuffmanTree;
import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;
import com.dg.jht.pojo.WeightNode;
import java.util.Map;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import com.dg.jht.util.HuffNodeComparator;
import com.dg.jht.util.MessageRepository;

public class HuffmanTreeBuilder
{
	private final Logger logger = Logger.getLogger(HuffmanTreeBuilder.class);
	private final MessageRepository messageRepository = new MessageRepository();
	
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
            logger.trace(messageRepository.buildNodesMessage(nodes));
            AbstractNode firstNode = nodes.remove(0);
            AbstractNode secondNode = nodes.remove(0);
            AbstractNode combinedNode = new WeightNode(firstNode,secondNode);
            nodes.add(combinedNode);
        }
        logger.trace(messageRepository.buildNodesMessage(nodes));
        return nodes.get(0);
    }
}
