/**
 *    Testing variations of segments ..
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

Segment segmente[];
Variation variations;
int l = 0;

class Segment
{
    float points[]; // [x,y, x2,y2, x3,y3, ...]

    Segment ( float pnts[] )
    {
        points = pnts;
    }
    
    void draw ()
    {
        beginShape();
            for (int i = 0; i < points.length; i+=2)
            {
                vertex( points[i], points[i+1] );
            }
        endShape();
        translate( points[points.length-2], points[points.length-1] );
    }
}

void setup ()
{
    size( 400, 400 );
    
    segmente = new Segment[5];
    
    int ll = width/2 / segmente.length;
    
    for ( int i = 0; i < segmente.length; i++ )
    {
        float angle = map( i, 0, segmente.length, 0, TWO_PI );
        segmente[i] = new Segment( new float[]{ 0,0, cos(angle) * ll, sin(angle) * ll } );
    }
    
    variations = new Variation( segmente.length, segmente.length-l );
    
    background( 255 );
}

void draw ()
{    
    translate( width/2, height/2 );
    
    if ( !variations.hasMore() )
    {
        l++;
        l %= segmente.length;
        variations = new Variation( segmente.length, segmente.length-l );
    }
    
    int[] c = variations.next();
    for ( int i = 0; i < c.length; i++ )
    {
        segmente[ c[i] ].draw();
    }
}
