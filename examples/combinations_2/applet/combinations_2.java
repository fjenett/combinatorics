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

public class combinations_2 extends PApplet {

/**
 *    Hallo norm.to.
 *    http://www.norm.to/pages/generator_3.html
 *
 *    Click inside window to speed things up a little ..
 *
 *    fjenett 20090306
 */



int connections[][];
Combination combinations;
float w3, w32;
    
int spots = 3;
int spotsSq = spots * spots;

public void setup ()
{
    size( 300, 300 );
    
    w3 = width/PApplet.parseFloat(spots);
    w32 = w3/2.0f;
    
    // find all possible connections (lines) between our number of spots
    Combination cc = new Combination( spotsSq, 2); // 2 points are a connection, spots ^ 2 to connect
    
    connections = new int[cc.totalAsInt()][2];
    
    int c = 0; while ( cc.hasMore() )
    {
        connections[c] = cc.next();
        c++;
    }
    
    // find all combinations (no dupls, ignore order) for 4 connections
    combinations = new Combination( connections.length, 4 );
    
    strokeWeight( 20 );
    strokeCap( PROJECT ); // uncomment if you don't like the edge overlaps
    smooth();
    fill(0);
    
    frameRate( 7 );
}

public void draw ()
{
    background( 255 );
    
    noStroke();
    for ( int i = 0; i < spotsSq; i++ )
        ellipse( w32 + (i%spots) * w3, w32 + (i/spots) * w3, 4, 4 );
    
    if ( !combinations.hasMore() )
        combinations.rewind();
    
    stroke( 0 );
    
    int c[] = combinations.next();
    for ( int i = 0; i < c.length; i++ )
    {        
        int cc[] = connections[c[i]];
        for ( int ii = 0; ii < cc.length; ii+=2 )
        {
            line( w32 + (cc[ii  ]%spots) * w3, w32 + (cc[ii  ]/spots) * w3, 
                  w32 + (cc[ii+1]%spots) * w3, w32 + (cc[ii+1]/spots) * w3 );
        }
    }
}

public void mousePressed ()
{
    frameRate( 40 );  
}

public void mouseReleased ()
{
    frameRate( 7 );   
}

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "combinations_2" });
    }
}
