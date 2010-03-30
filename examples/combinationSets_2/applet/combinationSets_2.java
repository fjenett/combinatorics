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

public class combinationSets_2 extends PApplet {

/**
 *    Testing combinations of colors (think flags)
 *
 *    fjenett 20090306
 */



int farben[];
CombinationSet combinations;

public void setup ()
{
    size( 400, 300 );
    
    farben = new int[] {
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

public void draw ()
{
    if ( !combinations.hasMore() )
    {
        combinations.rewind();
    }
    
    int[] c = combinations.next();
    float w = width/PApplet.parseFloat(c.length);
    for ( int i = 0; i < c.length; i++ )
    {
        fill( farben[ c[i] ] );
        rect( i*w, 0, w, height );
    }
}

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "combinationSets_2" });
    }
}
