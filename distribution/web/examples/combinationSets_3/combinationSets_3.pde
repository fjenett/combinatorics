/**
 *    Looping all results of a CombinationSet, that's 2^7 or 128 in total.
 *    (Including an empty set at the beginning.)
 *    
 *    A "Combination" means that no element can be used twice and the 
 *    order is ignored ( "a,b" equals "b,a" and is counted only once ).
 *
 *    given 3 elements, [a, b, c], you'll get from the CombinationSet:
 *
 *    [], [a], [b], [c], [a, b], [a, c], [b, c], [a, b, c]
 *
 *    that is 2^3 = 2*2*2 = 8 results
 *
 *    fjenett 20090305
 */

import de.bezier.math.combinatorics.*;

PShape shapes[];
CombinationSet combinations;

void setup ()
{
    size( 400, 400 );
    
    // initialize array and load shapes from data folder
    shapes = new PShape[7];
    for ( int i = 0; i < shapes.length; i++ )
    {
        shapes[i] = loadShape( "shape_"+i+".svg" );
    }
    
    // initialize combination set
    combinations = new CombinationSet( shapes.length );
    
    shapeMode( CENTER );
    frameRate( 5 );
    smooth();
}

void draw ()
{
    background( 255 );
    
    translate( width/2, height/2 ); // center in window
    
    if ( !combinations.hasMore() )
        combinations.rewind();
    
    int c[] = combinations.next();
    for ( int i = 0; i < c.length; i++ ) // loop elements of current combination
    {
        shape( shapes[ c[i] ], 0, 0 ); // draw element
    }
 }
