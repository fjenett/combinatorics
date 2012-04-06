/**
 *    Wrt smthng hr.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

void setup () 
{
    size( 500, 500 );
    
    background( 255 );
    fill( 0 );
    
    CombinationSet cset;
    char abc[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    // CombinationSet
    // .. on 4 elements
    // .. generating lengths 2-4 that is 2, 3, 4
    
    cset = new CombinationSet(5,1,4);
    
    float h = height / (cset.totalAsInt() + 0.5);
    float y = h;
    
    textSize( h * 0.9 );
    
    while ( cset.hasMore() )
    {
        int c[] = cset.next();
        for ( int p = 0; p < c.length; p++ )
        {
            text( abc[ c[p] ], 20 + p * (h/2), y );
        }
        y += h;
    }
}
