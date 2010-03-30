package de.bezier.math.combinatorics;

import java.math.BigInteger;
import java.math.BigDecimal;

/**
 *	Base class for Combination, Variation, Permutation and CombinatoricsBaseSet
 *
 *	<p>You normaly should not be using this class on it's own ...</p>
 */
public abstract class CombinatoricsBase
{
	int elements;
	int length;
	BigInteger totalResults, current;
	int[] indices;
	
	public CombinatoricsBase () {}
	public CombinatoricsBase ( int elements ) {}
	public CombinatoricsBase ( int elements, int length ) {}
	
	/**
	 *	@return Number of elements as given in parameter "elements" to constructor
	 */
	public int numberOfElements ()
	{
		return elements;
	}
	
	/**
	 *	@return Percent of how many results have been returned
	 */
	public float positionInPercent ()
	{
		return new Float(
					new BigDecimal(current, 5).divide( new BigDecimal(totalResults, 5), 
													BigDecimal.ROUND_HALF_UP ).
						doubleValue() * 100.0
		).floatValue();
	}
	
	/**
	 *	@param precision Round down to this precision (digits after the dot)
	 *	@return Percent of how many results have been returned
	 */
	public float positionInPercent ( int precision )
	{
		double scale = Math.pow( 10, precision );
		return  (float)( new Double( scale * positionInPercent() ).intValue() / new Double( scale ).floatValue() );
	}
	
	/**
	 *	Try to return internal counter (number of results returned) as int
	 *	@return Internal counter as int
	 */
	public int positionAsInt ()
	{
		int iVal = current.intValue();
		if ( !BigInteger.valueOf(iVal).equals(current) )
			System.err.println("ERR: positionAsInt(), the number of results is too large to fit into an int.");
		return iVal;
	}
	
	/**
	 *	Try to return internal counter (number of results returned) as long
	 *	@return Internal counter as long
	 */
	public long positionAsLong ()
	{
		long lVal = current.longValue();
		if ( !BigInteger.valueOf(lVal).equals(current) )
			System.err.println("ERR: positionAsLong(), the number of results is too large to fit into a long.");
		return lVal;
	}
	
	/**
	 *	Return copy of the internal counter (number of results returned) as BigInteger
	 *	@return Internal counter
	 *	@see java.math.BigInteger
	 */
	public BigInteger position ()
	{
		return current.add(BigInteger.ZERO);  // clone
	}
	
	/**
	 *	Try to return total number of results as int
	 *	@return Total number of results as int
	 *	@see java.math.BigInteger
	 */
	public int totalAsInt ()
	{
		int iVal = totalResults.intValue();
		if ( !BigInteger.valueOf(iVal).equals(totalResults) )
			System.err.println("ERR: totalAsInt(), the number of results is too large to fit into an int.");
		return iVal;
	}
	
	/**
	 *	Try to return total number of results as long
	 *	@return Total number of results as long
	 *	@see java.math.BigInteger
	 */
	public long totalAsLong ()
	{
		long lVal = totalResults.longValue();
		if ( !BigInteger.valueOf(lVal).equals(totalResults) )
			System.err.println("ERR: totalAsLong(), the number of results is too large to fit into a long.");
		return lVal;
	}
	
	/**
	 *	Return total number of results as BigInteger (cloned)
	 *	@return Total number of results
	 *	@see java.math.BigInteger
	 */
	public BigInteger total ()
	{
		return totalResults.add(BigInteger.ZERO);  // clone
	}
	
	/**
	 *	Rewind, start over, reset, recycle.
	 *	To be implements by the real workers.
	 */
	public abstract void rewind();
	
	/**
	 *	Check if there are more results available to be read.
	 *	To be implements by the real workers.
	 */
	public abstract boolean hasMore ();
	
	/**
	 *	Return next result and update internal counter.
	 *	To be implements by the real workers.
	 */
	public abstract int[] next();
	
	/**
	 *	Return result and step foreward for given amount of steps (that is: skip results)
	 *
	 *	@param step How many steps to go forward after returning the result
	 *	@return Current result, an array of indices, see comment at constructor in class Combination
	 */
	public int[] nextAndStep( int step )
	{
		int tmp[];
		if ( indices != null )
			tmp = new int[indices.length];
		else
			tmp = new int[length];
			
		System.arraycopy(next(), 0, tmp, 0, tmp.length);
		
		int i  = 0;
		while (i < step-1)
		{
			next();
			i++;
		}
		
		return tmp;
	}
	
	/*************
	 *  DEFAULT  *
	 *************/
	
	void increase ()
	{
		// current = current >= totalResults ? totalResults : current+1;
		if ( current.compareTo(totalResults) < 0 )
			current = current.add(BigInteger.ONE);
	}
	
	void decrease ()
	{
		// current <= 0 ? 0 : current.subtract(1);
		if ( current.compareTo(BigInteger.ZERO) <= 0 )
			current = BigInteger.ZERO;
		else
			current = current.subtract(BigInteger.ONE);
	}
	
	static BigInteger factorial ( int n )
	{
		BigInteger r = BigInteger.ONE;
		BigInteger ii = BigInteger.ONE;
		for ( int i = 1; i <= n; i++ )
		{
			r = r.multiply(ii);
			ii = ii.add( BigInteger.ONE );
		}
		return r;
	}
	
	// MichaelTague
	// http://snippets.dzone.com/posts/show/93
	static final byte[] intToByteArray(int value)
	{
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte) value };
	}
	 
	int[] cloneIntArray ( int[] org )
	{
		int[] tmp = new int[org.length];
		System.arraycopy( org, 0, tmp, 0, org.length );
		return tmp;
	}
	
	/*************
	 *  PRIVATE  *
	 *************/
	
	private static boolean checkTotal ( int total_combinations )
	{
		if ( total_combinations > max_combinations )
		{
			err( "You are trying to generate "+total_combinations+" combinations." );
			return false;
		}
		return true;
	}
	
	private static String printCombinations ( int[][] c )
	{
		String s = "[[";
		for ( int i = 0; i < c.length; i++ )
		{
			for ( int j = 0; j < c[i].length; j++ )
			{
				s += c[i][j] + ( j<c[i].length-1 ? ", " : "" );
			}
			s += "]" + ( i<c.length-1 ? ", [" : "" );
		}
		s += "]";
		
		return s;
	}
	
	private final static boolean do_debug = false;
	private static String msg_context = "Combinatorics";
	private static int max_combinations = 10000;
	
	private static void err ( String msg )
	{
		System.err.println( msg_context + ": " + msg );
	}
	
	private static void debug ( String msg )
	{
		if ( do_debug ) System.out.println( msg_context + ": " + msg );
	}
}
