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

public class variations_4 extends PApplet {

/**
 *    Testing variations of colors. Few colors, many variations.
 *
 *    fjenett 20090306
 */



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
    
    public void draw ()
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

public void setup ()
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

public void draw ()
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

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "variations_4" });
    }
}
