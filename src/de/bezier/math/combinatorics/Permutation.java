package de.bezier.math.combinatorics;

import java.math.BigInteger;

/**
 *	Changes in the order
 *
 *	<p>A permutation is a change in the order of a given set of elements. 
 *	An <a href="http://en.wikipedia.org/wiki/Anagram">anagram</a> or a deck of
 *  cards being shuffled are examples.</p>
 *
 *  <p>Given the elements [a,b,c] a permutation would produce:<br />
 *	[a,b,c], [a,c,b], [b,a,c], [b,c,a], [c,a,b], [c,b,a].</p>
 *
 *	<p>For 3 elements you will receive 3! (read "!" as <a href="http://en.wikipedia.org/wiki/Factorial">factorial</a>) 
 *  results, that is 3*2*1 = 6</p>
 *
 *	<p>This code is based on: Michael Gillelands <a href="http://www.merriampark.com/perm.htm">code</a>,  
 *	which he claims to be an implementation of <br />
 *	Kenneth H. Rosen, Discrete Mathematics and Its Applications, 2nd edition (NY: McGraw-Hill, 1991), pp. 282-284
 *  </p>
 */
public class Permutation
extends CombinatoricsBase
{
	// TODO: permutations with classes:
	// a,a,a,b,b,c,c,c,c
	// (3 + 2 + 4)! = 362880
	// 3! * 2! * 4! = 288
	// 362880 / 288 = 1260
	
	/**
	 *	@param elements Number of elements in the set to generate permutations for, should be larger than 0
	 */
	public Permutation ( int elements )
	{
		if ( elements <= 0 )
		{
			System.err.println("Variation(): Given elements ("+elements+") is too small.");
		}
		
		this.elements = elements;
		//this.length = length;
		
		totalResults = factorial( elements );
		
		rewind();
	}
	
	/**
	 *	As it says, rewinds the internal state to start over.
	 */
	public void rewind ()
	{
		current = totalResults;
		
		indices = new int[elements];
		for ( int i = 0; i < indices.length; i++ )
			indices[i] = i;
	}
	
	/**
	 *	Are there more results available? Use in conditionals:
	 *	<pre>if ( permuti.hasMore() ) { ... }</pre>
	 *	@return true if there are more results available, false otherwise
	 */
	public boolean hasMore ()
	{
		return current.compareTo(BigInteger.ZERO) > 0; // > 0
	}
	
	/**
	 *	Return next result and update internal counter by one
	 *	@return Result as array of indices, see Combination intro for explaination
	 */
	public int[] next ()
	{
		if ( current.equals(BigInteger.ZERO) ) // == 0
			return cloneIntArray(indices);
			
		if ( current == totalResults )
		{
			decrease();
			return cloneIntArray(indices);
		}
		
		// Find largest index j with a[j] < a[j+1]
			
		int j = indices.length - 2;
		while ( indices[j] > indices[j+1] ) j--;
		
		// Find index k such that a[k] is smallest integer
		// greater than a[j] to the right of a[j]
		
		int k = indices.length - 1;
		while ( indices[j] > indices[k] ) k--;
		
		// Interchange a[j] and a[k]
		
		int tmp = indices[k];
		indices[k] = indices[j];
		indices[j] = tmp;
		
		// Put tail end of permutation after jth position in increasing order
		
		int r = indices.length - 1;
		int s = j + 1;

		while ( r > s )
		{
			tmp = indices[s];
			indices[s] = indices[r];
			indices[r] = tmp;
			r--;
			s++;
		}
		
		decrease();
		
		return cloneIntArray(indices);
	}
	
	/**
	 *	How many percent of results returned so far?
	 *	@return Number of returned results (internal state) in percent as float
	 */
	public float positionInPercent ()
	{
		return 100.0f - super.positionInPercent();
	}
	
	/**
	 *	Try to return internal counter (number of results returned) as int
	 *	@return Internal counter as int
	 */
	public int positionAsInt ()
	{
		int iVal = getPosition().intValue();
		if ( !BigInteger.valueOf(iVal).equals(getPosition()) )
			System.err.println("ERR: positionAsInt(), the number of results is too large to fit into an int.");
		return iVal;
	}
	
	
	/**
	 *	Try to return internal counter (number of results returned) as long
	 *	@return Internal counter as long
	 */
	public long positionAsLong ()
	{
		long lVal = getPosition().longValue();
		if ( !BigInteger.valueOf(lVal).equals(getPosition()) )
			System.err.println("ERR: positionAsLong(), the number of results is too large to fit into a long.");
		return lVal;
	}
	
	
	/**
	 *	Return copy of internal counter (number of results returned) as BigInteger
	 *	@return Internal counter
	 */
	public BigInteger position ()
	{
		return getPosition();
	}
	
	// helper for Permutation since it's actually counting down (backwards)
	private BigInteger getPosition ()
	{
		return totalResults.subtract(current).add(BigInteger.ZERO); // cloned
	}
}
