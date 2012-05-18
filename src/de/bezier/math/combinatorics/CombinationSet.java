package de.bezier.math.combinatorics;

import java.math.BigInteger;

/**
 *	A range of Combinations
 *
 *	<p>A CombinationSet represents a range of Combinations, for example:</p>
 *	<pre>
 *	CombinationSet cset = new CombinationSet( 3 );
 *	</pre>
 *	<p>would represent (and loop thru the results of) these single Combinations:</p>
 *	<pre>
 *	{
 *	  new Combination( 3, 0 ),		// [], empty
 *	  new Combination( 3, 1 ),		// [0], [1], [2]
 *	  new Combination( 3, 2 ),		// [0,1], [0,2], [1,2]
 *	  new Combination( 3, 3 )		// [0,1,2]
 *	}
 *	</pre>
 *	<p>If you were just interessted in results of lengths 1 and 2 you could:</p>
 *	<pre>
 *	CombinationSet cset = new CombinationSet( 3, 1, 2 ); // 3 elements, starting at lenghts 1, ends at length 2
 *	</pre>
 *	<p>which then would only represent these Combinations:</p>
 *	<pre>
 *	{
 *	  new Combination( 3, 1 ),		// [0], [1], [2]
 *	  new Combination( 3, 2 ),		// [0,1], [0,2], [1,2]
 *	}
 *	</pre>
 *
 *	@see de.bezier.math.combinatorics.Combination
 */
public class CombinationSet
extends CombinatoricsBaseSet
{
	// TODO: implement groups, say CombinationSet( 3, [1,3,5,6] )
	// maybe even CombinationSet( 3, CombinationSet.ODD )
	
	public CombinationSet ( int elements ) { super(elements); }
	public CombinationSet ( int elements, int from, int to ) { super(elements, from, to); }

	public void rewind ()
	{
		current = BigInteger.ZERO;
		totalResults = BigInteger.ZERO;
		
		// calc total results
		for ( int i = from; i <= to; i++ )
		{
			Combination c = new Combination(elements, i);
			totalResults = 
				totalResults.add( c.total() );
		}
		
		// first generator
		generator = new Combination(elements, from);
		
		current = BigInteger.ZERO;
		currentSet = from;
	}
	
	// this will jump to / create the next generator (CombinatoricsBase) represented in this group
	CombinatoricsBase nextGenerator ()
	{
		generator = new Combination(elements, currentSet);
		return generator;
	}
}
