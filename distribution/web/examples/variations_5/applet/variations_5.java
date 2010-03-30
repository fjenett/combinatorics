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

public class variations_5 extends PApplet {

/**
 *    Testing variations of branches. Few branches, many variations.
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

public void draw ()
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

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "variations_5" });
    }
}
