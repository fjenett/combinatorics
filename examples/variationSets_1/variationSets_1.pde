/**
 *    Wrt smthng hr.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

VariationSet vset;
char abc[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

vset = new VariationSet(4,1,4); // 4 elements, return lengths 2 - 3

while ( vset.hasMore() )
{
    int c[] = vset.next();
    for ( int p = 0; p < c.length; p++ )
    {
        print( abc[ c[p] ] );
    }
    println();
}

exit();
