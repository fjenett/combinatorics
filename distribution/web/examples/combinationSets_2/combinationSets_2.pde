/**
 *    Testing combinations of colors (think flags)
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

color farben[];
CombinationSet combinations;

void setup ()
{
    size( 400, 300 );
    
    farben = new color[] {
        color( 255, 0, 0 ),
        color( 200, 100, 0 ),
        color( 125, 200, 50 ),
        
        color( 50, 255, 100 ),
        color( 0, 255, 200 ),
        color( 0, 175, 255 ),
        
        color( 0, 120, 200 ),
        color( 10, 75, 100 ),
        color( 30, 0, 20 ),
        
        color( 0, 0, 0 )
    };
    
    combinations = new CombinationSet( farben.length, 1, farben.length );
    
    noStroke();
    frameRate( 10 );
}

void draw ()
{
    if ( !combinations.hasMore() )
    {
        combinations.rewind();
    }
    
    int[] c = combinations.next();
    float w = width/float(c.length);
    for ( int i = 0; i < c.length; i++ )
    {
        fill( farben[ c[i] ] );
        rect( i*w, 0, w, height );
    }
}
