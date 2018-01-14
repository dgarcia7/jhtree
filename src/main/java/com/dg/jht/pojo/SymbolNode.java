package com.dg.jht.pojo;

/**
 * Node for storing symbols.  These nodes are
 * always leaf nodes.
 */
public class SymbolNode extends AbstractNode
{
    private String symbol;

    /**
     * Create a new {@link SymbolNode}.
     * 
     * @param s
     * @param w
     */
    public SymbolNode(String s, Long w)
    {
        symbol = s;
        weight = w;
    }

    /**
     * Get the symbol {@link String}.
     * 
     * @return
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * Set the symbol {@link String}.
     * 
     * @param input
     */
    public void setSymbol(String input)
    {
        symbol = input;
    }
}