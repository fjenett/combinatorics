/**
 *    Testing variations of colors. Few colors, many variations.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

color farben[];
Variation variations;
int l = 0;

void setup ()
{
    size( 500, 200 );
    
    farben = new color[] {
        color( 255, 245, 220 ),
        color( 200, 170, 150 ),
        color( 126, 115, 100 ),
        color( 70, 60, 50 ),
        color( 20, 10, 0 )
    };
    
    variations = new Variation( farben.length, l+1 );
    
    noStroke();
    frameRate( 10 );
}

void draw ()
{
    if ( !variations.hasMore() )
    {
        l++;
        l %= farben.length;
        variations = new Variation( farben.length, l+1 );
    }
    
    int[] c = variations.next();
    float w = width/float(c.length);
    for ( int i = 0; i < c.length; i++ )
    {
        fill( farben[ c[i] ] );
        rect( i*w, 0, w, height );
    }
}
