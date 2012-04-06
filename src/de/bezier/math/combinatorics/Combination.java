package de.bezier.math.combinatorics;

import java.math.BigInteger;

/**
 *	Unique combinations, no duplications, order is ignored
 *
 *	<p>This class represents sets that have no duplicate elements (no "a a") and order 
 *	is ignored ("a b" equals "b a" and is therefore counted only once).</p>
 *
 *	<pre>
 *	Given the elements [a, b, c] Combination will return these results, for
 *
 *	length (of a single result, an array) 0:
 *	[]
 *
 *	length 1:
 *	[a], [b], [c]
 *
 *	length 2:
 *	[a, b], [a, c], [b, c]
 *
 *	length 3:
 *	[a, b, c]
 *	</pre>
 */
public class Combination
extends CombinatoricsBase
{
	/**
	 *	Same as calling Combination( elements, elements )
	 */
	public Combination ( int elements )
	{
		this( elements, elements );
	}
	
	/**
	 *	<p>As with all classes of package de.bezier.math.combinatorics this will return arrays of
	 *	indices for you to use on whatever you want.</p>
	 *
	 *	<p>Say you have an array like this {"a", "b", "c"} and
	 *	want all possible pairs of combinations, you could do:</p>
	 *  <pre>
	 *	// your array ..
	 *	String[] yourArray = new String[]{"a", "b", "c"};
	 *
	 *	// tell Combination the length of your array and how long results should be:
	 *	// 3 elements has your array (yourArray.length), results should have a length of 2
	 *	Combination combi = new Combination( yourArray.length, 2 );
	 *	</pre>
	 *	<p>This will give you access to the following 3 results:</p>
	 *	<pre>
	 *	{0,1}, {0,2}, {1,2}
	 *	</pre>
	 *	<p>Which you can then use to read from your original array:</p>
	 *	<pre>
	 *	int[] result = combi.next();
	 *	// use contents of result as indices (addresses) into your array:
	 *	// yourArray[result[0]] will give "a" since it would be same as writing: "yourArray[0]"
	 *	// yourArray[result[1]] will give "b"
	 *
	 *	result = combi.next();
	 *	// yourArray[result[0]] will give "a"
	 *	// yourArray[result[1]] will give "c"
	 *
	 *	result = combi.next();
	 *	// yourArray[result[0]] will give "b"
	 *	// yourArray[result[1]] will give "c"
	 *	</pre>
	 *
	 *	<p>Since there can be no duplications of elements Combination will not take a higher length than 
	 *	the given number of elements.</p>
	 *
	 *	@param elements Number of elements to use as input, can not be negative ( elements > 0 )
	 *	@param length The length of the results, can not be higher than elements and not be negative ( length >= elements, length > 0 )
	 */
	public Combination ( int elements, int length )
	{
		this.elements = elements;
		this.length = length;
		
		if ( elements == 0 )
		{
			System.err.println( "Working with 0 elements makes no sense, please use a higher input." );
		}
		else if ( elements < length )
		{
			System.err.println( "Can not put "+elements+" on "+length+" places. Just impossible without duplication." );
		}
		
		if ( length == 0 )
			totalResults = BigInteger.ZERO;
		else if ( length == 1 )
			totalResults = new BigInteger( intToByteArray(elements) );
		else
			// totalResults = factorial(elements) / (factorial(length) * factorial(elements-length))
			totalResults = factorial(elements).divide( factorial(length).multiply( factorial(elements-length) ) );
		
		rewind();
	}
	
	/**
	 *	Rewind, start over. Resets the internal counter
	 */
	public void rewind()
	{
		current = BigInteger.ZERO;
		
		indices = new int[length];
		for ( int i = 0; i < indices.length; i++ )
			indices[i] = i;
	}
	
	/**
	 *	Has more results? Any left to be read?
	 *
	 *	<pre>
	 *	// use this as condition:
	 *	while ( lottoNumbers.hasMore() )
	 *	{
	 *		int[] numbersToPlay = lottoNumbers.next();
	 *		// ... go buy a ticket for each result and you will win it all!
	 *  }
	 *	</pre>
	 *
	 *	@return Returns true if there are more results available
	 */
	public boolean hasMore ()
	{
		// current < totalResults
		return current.compareTo(totalResults) < 0;
	}
	
	/**
	 *	Return current result (an array of indices, see explaination at constructor) and go to next result (if possible)
	 *
	 *	@return Current result or an empty array
	 */
	public int[] next ()
	{
		if ( current.equals( BigInteger.ZERO ) ) // == 0
		{
			increase();
			return cloneIntArray(indices);
		}
		if ( current.equals( totalResults ) )
			return cloneIntArray(indices);
		
		for ( int n = indices.length-1; n >= 0; n-- )
		{
			if ( n == indices.length-1 || indices[n+1] == 0 )
			{
				indices[n]++;
				
				for ( int k = n+1; k < indices.length; k++ )
				{
					indices[k] = indices[k-1] + 1;
				}
			}
			
			indices[n] = indices[n] % (elements - (indices.length-1 - n));
		}
		
		increase();
		
		return cloneIntArray(indices);
	}
}
