package com.dg.jht.pojo;

public class SymbolNode extends AbstractNode
{
    private String symbol;

    public SymbolNode(String s, Long w)
    {
        symbol = s;
        weight = w;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String input)
    {
        symbol = input;
    }
}