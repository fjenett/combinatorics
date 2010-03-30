package de.bezier.math.combinatorics;

import java.math.BigInteger;

/**
 *	Base class for all sets of CombinatoricsBases. Used for CombinationSet and VariationSet
 *
 *	<p>A set means a group of. For example CombinationSet will represent a group of Combinations, ..</p>
 *
 *	<p>You should not need to use this class on it's own ..</p>
 */
public abstract class CombinatoricsBaseSet
extends CombinatoricsBase
{
	int from, to;
	int currentSet;
	CombinatoricsBase generator;
	
	CombinatoricsBaseSet () {}
	
	/**
	 *	Same as using new CombinatoricsBaseSet( elements, 0, elements )
	 */
	public CombinatoricsBaseSet ( int elements )
	{
		this( elements, 0, elements );
	}
	
	/**
	 *	See intro to CombinationSet for an explaination.
	 *
	 *	@param	elements Number of elements each CombinatoricsBase in this set should represent
	 *	@param from Length of smallest: CombinatoricsBase( elements, from )
	 *	@param to Length of largest included: CombinatoricsBase( elements, to )
	 *
	 *	@see de.bezier.math.combinatorics.CombinationSet
	 */
	public CombinatoricsBaseSet ( int elements, int from, int to )
	{
		this.elements = elements;
		this.from = from;
		this.length = from;
		this.to = to;
		
		rewind();
	}
	
	/**
	 *	Check to see if there are more results available to be read, for example:
	 *	<pre>
	 *	while ( cset.hasMore() )
	 *	{
	 *		int res[] = cset.next();
	 *		// do something extraordinary with it here ..
	 *	}
	 *	</pre>
	 *	@return true if there are more results to be read, false otherwise
	 */
	public boolean hasMore ()
	{
		return hasMoreInSet();
	}
	
	/**
	 *	Read next result (if there is one) and update internal counter.
	 *
	 *	@return A result in form of an array of indices, see Combination intro for explaination
	 *	@see de.bezier.math.combinatorics.CombinationSet
	 */
	public int[] next ()
	{
		int tmp[] = generator.next();
		if ( !generator.hasMore() && hasMoreInSet() )
		{
			currentSet++;
			length = from+currentSet; // needed to nextAndStep()
			generator = nextGenerator();
		}
		return tmp;
	}
	
	boolean hasMoreInSet ()
	{
		if ( currentSet < to )
			return true;
		else
			return generator.hasMore();
	}
	
	abstract CombinatoricsBase nextGenerator ();
}
