/**
 *    Testing variations of branches. Few branches, many variations.
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

Segment segmente[];
Variation variations;
int l = 0;

void setup ()
{
    size( 400, 400 );
    
    int ll = 25;
    
    segmente = new Segment[]{
        new Segment(new float[]{0,0, 0,ll}),
        new Segment(new float[]{0,0, ll,ll}),
        new Segment(new float[]{0,0, ll,0}),
        new Segment(new float[]{0,0, ll,-ll}),
        new Segment(new float[]{0,0, 0,-ll}),
        new Segment(new float[]{0,0, -ll,-ll}),
        new Segment(new float[]{0,0, -ll,0}),
        new Segment(new float[]{0,0, -ll,ll})
    };
    
    variations = new Variation( segmente.length, segmente.length-l );
    
    strokeWeight( 10 );
}

void draw ()
{
    background( 255 );
    
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
