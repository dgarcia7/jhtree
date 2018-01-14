package com.dg.jht.pojo;

/**
 * <p>
 * Base node type.  Nodes are the underlying
 * structure of huffman trees.
 * </p>
 * <p>
 * All nodes have weight (frequency).
 * </p>
 */
public abstract class AbstractNode
{
    protected Long weight;

    /**
     * Get the weight {@link Long}.
     * 
     * @return
     */
    public Long getWeight()
    {
        return weight;
    }

    /**
     * Set the weight {@link Long}.
     * 
     * @param input
     */
    public void setWeight(Long input)
    {
        weight = input;
    }
}