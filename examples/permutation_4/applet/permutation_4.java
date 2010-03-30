import processing.core.*; 
import processing.xml.*; 

import de.bezier.math.combinatorics.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class permutation_4 extends PApplet {

/**
 *    
 *
 *    fjenett 20090306
 */



Permutation permutation;
float w2;
int perRow;

public void setup ()
{
    size( 400, 400 );
    
    perRow = 7;
    permutation = new Permutation( perRow*perRow );
    w2 = width/(2.0f*perRow);
    
    noStroke();
    background( 255 );
    fill( 0 );
    frameRate( 5 );
}

public void draw ()
{
    background( 255 );
    translate( w2, w2 );
    if ( permutation.hasMore() )
    {
        int[] p = permutation.nextAndStep( 1000000 ); // a biiiig step, still not gettin' much further
        for ( int j = 0; j < p.length; j++ )
        {
            pushMatrix();
                translate( (j%perRow)*(2*w2), (j/perRow)*(2*w2) );
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

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "permutation_4" });
    }
}
