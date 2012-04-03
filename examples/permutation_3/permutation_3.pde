/**
 *    Colors, colors.
 *    This permutation runs thru all 362880 possible arrangements.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

color farben[];
Permutation permutation;

void setup ()
{
    size( 200, 200 );
    
    farben = new color[] {
        color( 255, 0, 0 ),
        color( 200, 100, 0 ),
        color( 125, 200, 50 ),
        
        color( 50, 255, 100 ),
        color( 0, 255, 200 ),
        color( 0, 175, 255 ),
        
        color( 0, 120, 200 ),
        color( 10, 75, 100 ),
        color( 30, 0, 20 )
    };
    
    permutation = new Permutation( farben.length );
    
    frameRate( 20 );
    noStroke();
}

void draw ()
{
    if ( permutation.hasMore() )
    {
        int[] p = permutation.next();
        float w = width/3.0;
        for ( int j = 0; j < p.length; j++ )
        {
            fill( farben[ p[j] ] );
            rect( (j%3)*w, int(j/3)*w, w, w );
        } 
    }
}
