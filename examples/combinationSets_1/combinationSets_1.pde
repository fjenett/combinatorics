/**
 *    Wrt smthng hr.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

CombinationSet cset;
char abc[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

cset = new CombinationSet(4,2,3); // 4 elements, return lengths 2 - 3

while ( cset.hasMore() )
{
    int c[] = cset.next();
    for ( int p = 0; p < c.length; p++ )
    {
        print( abc[ c[p] ] );
    }
    println();
}

exit();
