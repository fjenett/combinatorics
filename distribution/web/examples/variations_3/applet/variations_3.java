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

public class variations_3 extends PApplet {

/**
 *    Testing variations of colors. Few colors, many variations.
 *
 *    fjenett 20090306
 */



int farben[];
Variation variations;
int l = 0;

public void setup ()
{
    size( 500, 200 );
    
    farben = new int[] {
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

public void draw ()
{
    if ( !variations.hasMore() )
    {
        l++;
        l %= farben.length;
        variations = new Variation( farben.length, l+1 );
    }
    
    int[] c = variations.next();
    float w = width/PApplet.parseFloat(c.length);
    for ( int i = 0; i < c.length; i++ )
    {
        fill( farben[ c[i] ] );
        rect( i*w, 0, w, height );
    }
}

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "variations_3" });
    }
}
