/**
 *    This is just a stupid simple and unvisual example for me to test
 *    the class Permutation.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

char chars[] = ".;\"*".toCharArray(); // Me is lazy today: convert string to char[]

Permutation permutation = new Permutation( chars.length );

while ( permutation.hasMore() )
{
    int[] p = permutation.next();
    for ( int j = 0; j < p.length; j++ )
    {
        print( chars[p[j]] );
        if ( j < p.length-1 )
            print( " " );
    }   
    println();
}

exit();
