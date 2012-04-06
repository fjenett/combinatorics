package de.bezier.math.combinatorics;

import java.math.BigInteger;

/**
 *	A range of Variations
 *
 *	<p>Represents a range of Variations. See intro to CombinationSet for an explaination of what that means.</p>
 *
 *	@see de.bezier.math.combinatorics.CombinationSet
 */
public class VariationSet
extends CombinatoricsBaseSet
{
	public VariationSet ( int elements ) { super(elements); }
	public VariationSet ( int elements, int from, int to ) { super(elements, from, to); }
	
	/**
	 *	Does what it says: rewinds the internal state to start over.
	 */
	public void rewind ()
	{
		current = BigInteger.ZERO;
		totalResults = BigInteger.ZERO;
		Variation v[] = new Variation[to-from+1];
		for ( int i = from; i <= to; i++ )
		{
			v[i] = new Variation(elements, i);
			totalResults = 
				totalResults.add( v[i].total() );
		}
		
		generator = new Variation(elements, from);
		current = BigInteger.ZERO;
		currentSet = from;
	}
	
	CombinatoricsBase nextGenerator ()
	{
		generator = new Variation(elements, currentSet);
		return generator;
	}
}
