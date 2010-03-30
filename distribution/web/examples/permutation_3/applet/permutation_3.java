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

public class permutation_3 extends PApplet {

/**
 *    Colors, colors.
 *    This permutation runs thru all 362880 possible arrangements.
 *
 *    fjenett 20090306
 */



int farben[];
Permutation permutation;

public void setup ()
{
    size( 200, 200 );
    
    farben = new int[] {
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

public void draw ()
{
    if ( permutation.hasMore() )
    {
        int[] p = permutation.next();
        float w = width/3.0f;
        for ( int j = 0; j < p.length; j++ )
        {
            fill( farben[ p[j] ] );
            rect( (j%3)*w, (j/3)*w, w, w );
        } 
    }
}

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "permutation_3" });
    }
}
