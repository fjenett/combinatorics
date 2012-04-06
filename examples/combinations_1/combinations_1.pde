/**
 *    A simple example for class Combination.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

void setup ()
{
    size( 500, 500 );
    
    background( 255 );
    fill( 0 );
    textSize( 40 );
    
    char chars[] = "abcde".toCharArray();
    
    // Generate the following combinations:
    // place 5 elements on 3 positions
    
    Combination combinations = new Combination( chars.length, 3 );
    
    float h = height / (combinations.totalAsInt()+0.5);
    float y = h;
    
    while ( combinations.hasMore() )
    {
        int[] c = combinations.next();
        for ( int i = 0; i < c.length; i++ )
        {
            text( chars[c[i]], 20 + i*24, y );
        }
        y += h;
    }

    noLoop();
}
