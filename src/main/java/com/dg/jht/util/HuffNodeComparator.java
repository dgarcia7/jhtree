package com.dg.jht.util;

import java.util.Comparator;
import com.dg.jht.pojo.AbstractNode;
import com.dg.jht.pojo.SymbolNode;

/**
 * {@link Comparator} implementation to assist with sorting
 * of {@link AbstractNode}s.  This {@link Comparator} will
 * first sort by weight, and then sort by symbol if
 * both underlying nodes are {@link SymbolNode}s.
 */
public class HuffNodeComparator implements Comparator<Object>
{
	/**
	 * {@inheritDoc}
	 */
	@Override public int compare(Object left, Object right)
	{
		int comp = ((AbstractNode)left).getWeight().compareTo(((AbstractNode)right).getWeight());
		if(comp == 0 && left instanceof SymbolNode && right instanceof SymbolNode)
			comp = ((SymbolNode)left).getSymbol().compareTo(((SymbolNode)right).getSymbol());
		return comp;
	}
}