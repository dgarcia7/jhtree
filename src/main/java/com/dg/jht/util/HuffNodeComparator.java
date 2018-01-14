package com.dg.jht.util;

import java.util.Comparator;
import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;

public class HuffNodeComparator implements Comparator<Object>
{
	@Override public int compare(Object left, Object right)
	{
		int comp = ((AbstractNode)left).getWeight().compareTo(((AbstractNode)right).getWeight());
		if(comp == 0 && left instanceof SymbolNode && right instanceof SymbolNode)
			comp = ((SymbolNode)left).getSymbol().compareTo(((SymbolNode)right).getSymbol());
		return comp;
	}
}