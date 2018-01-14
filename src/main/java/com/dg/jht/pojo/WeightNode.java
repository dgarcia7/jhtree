package com.dg.jht.pojo;

/**
 * Node for storing weight values.  Weight
 * nodes are always internal nodes, that store
 * the weight/frequency sum of both sub nodes.
 */
public class WeightNode extends AbstractNode
{
    protected AbstractNode left = null;
    protected AbstractNode right = null;

    /**
     * Create a new {@link WeightNode}.
     * 
     * @param leftInput
     * @param rightInput
     */
    public WeightNode(AbstractNode leftInput, AbstractNode rightInput)
    {
        left = leftInput;
        right = rightInput;

        long leftWeight = 0;
        long rightWeight = 0;

        if(left != null)
            leftWeight = left.getWeight();
        if(right != null)
            rightWeight = right.getWeight();

        this.setWeight(leftWeight + rightWeight);
    }

    /**
     * Get the left {@link AbstractNode}.
     * 
     * @return
     */
    public AbstractNode getLeft()
    {
        return left;
    }

    /**
     * Get the right {@link AbstractNode}.
     * 
     * @return
     */
    public AbstractNode getRight()
    {
        return right;
    }
}