package com.dg.jht.pojo;

public class WeightNode extends AbstractNode
{
    protected AbstractNode left = null;
    protected AbstractNode right = null;

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

    public AbstractNode getLeft()
    {
        return left;
    }

    public AbstractNode getRight()
    {
        return right;
    }
}