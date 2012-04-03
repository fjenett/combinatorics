/**
 *    This is just a stupid simple and unvisual example for me to test
 *    the class Variation.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

void setup ()
{
    char chars[] = "abc".toCharArray();
    
    for ( int l = 1; l <= chars.length; l++ )
    {
        Variation variation = new Variation( chars.length, l );
        
        while ( variation.hasMore() )
        {
            int[] v = variation.next();
            for ( int i = 0; i < v.length; i++ )
            {
                print( chars[ v[i] ] );
            }
            println();
        }
    }
}
