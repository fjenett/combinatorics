/**
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

Permutation permutation;
float w2;
int perRow;

void setup ()
{
    size( 400, 400 );
    
    perRow = 7;
    permutation = new Permutation( perRow*perRow );
    w2 = width/(2.0*perRow);
    
    noStroke();
    background( 255 );
    fill( 0 );
    frameRate( 5 );
}

void draw ()
{
    background( 255 );
    translate( w2, w2 );
    if ( permutation.hasMore() )
    {
        int[] p = permutation.nextAndStep( 1000000 ); // a biiiig step, still not gettin' much further
        
        for ( int j = 0; j < p.length; j++ )
        {
            pushMatrix();
                translate( (j%perRow)*(2*w2), int(j/perRow)*(2*w2) );
                int r = p[j] % 4;
                fill( map(p[j], 0, p.length, 0, 100), 
                      map(p[j], 0, p.length, 50, 200), 
                      70 );
                rotate( r * HALF_PI );
                beginShape();
                    vertex( -w2, -w2 );
                    vertex( -w2,  w2 );
                    vertex(  w2, -w2 );
                endShape(CLOSE);
            popMatrix();
        }
    }
    else
    {
        println( "no more .. ");
    }
}
