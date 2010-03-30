package de.bezier.math.combinatorics;

import java.math.BigInteger;

/**
 *	Like a counter, duplications, order counts
 *
 *	<p>A Variation represents all possible variations of a given set of elements.
 *	This includes duplications ("a,a,a" is OK) and the order is important 
 *	("a,b" is not "b,a" and each is counted).</p>
 */
public class Variation
extends CombinatoricsBase
{
	private BigInteger deviders[];
	private BigInteger bigElements;
	
	/**
	 *	Same as using Variation( elements, elements )
	 */
	public Variation ( int elements )
	{
		this( elements, elements );
	}
	
	/**
	 *	@param elements Number of elements to return variations for, should be larger than 0
	 *	@param length Length of the results (arrays of indices) to return, should not be negative
	 */
	public Variation ( int elements, int length )
	{
		if ( elements <= 0 )
		{
			System.err.println("Variation(): Given elements ("+elements+") is too small.");
		}
		if ( length < 0 )
		{
			System.err.println("Variation(): Given length ("+length+") is too small.");
		}
		
		this.elements = elements;
		this.length = length;
		
		bigElements = new BigInteger(intToByteArray(elements));
		
		totalResults = bigElements.pow(length); // (long)(Math.pow(elements, length))
		
		rewind();
		
		deviders = new BigInteger[length];
		for ( int i = 0; i < deviders.length; i++ )
			deviders[deviders.length-1-i] = bigElements.pow(i+1).divide(bigElements); // (long)(Math.pow(elements, i+1)) / elements
	}
	
	public void rewind ()
	{
		current = BigInteger.ZERO;
		
		indices = new int[length];
	}
	
	public boolean hasMore ()
	{
		return current.compareTo(totalResults) < 0; // current < totalResults
	}
	
	public int[] next ()
	{
		if ( current.equals(BigInteger.ZERO) ) // == 0
		{
			increase();
			return cloneIntArray(indices);
		}
		
		if ( current == totalResults )
			return cloneIntArray(indices);
		
		for ( int k = indices.length-1; k >= 0; k-- )
		{
			indices[k] = current.divide(deviders[k]).mod(bigElements).intValue(); // (int)( (current / deviders[k]) % elements )
		}
		
		increase();
		
		return cloneIntArray(indices);
	}
}
