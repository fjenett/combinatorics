/**
 *    This is just a stupid simple and example for me to test
 *    the class Combination.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

char chars[] = "abcde".toCharArray();

// place 5 elements on 3 positions
Combination combinations = new Combination( chars.length, 3 );

while ( combinations.hasMore() )
{
    int[] c = combinations.next();
    for ( int i = 0; i < c.length; i++ )
    {
        print( chars[ c[i] ] );
    }
    println();
}

exit();
