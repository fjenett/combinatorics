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

public class variations_2 extends PApplet {

/**
 *    A simple clock.
 *
 *    fjenett 20090310
 */



Variation clockwork;
int lastSec = 0;

public void setup ()
{
    size( 200, 200 );
    
    clockwork = new Variation( 60, 3 );
    
    println( hour()+" "+minute()+" "+second() ); // 86400
    
    // forward to the current time.
    int secondsFF = hour()%12; // wrap 24hrs to 12hrs
    secondsFF = secondsFF * 5; // scale 12 to 60
    secondsFF = secondsFF * 60 + minute(); // to minutes
    secondsFF = secondsFF * 60 + second(); // to seconds
    clockwork.nextAndStep( secondsFF );
    
    background( 255 );
    noStroke();
    smooth();
}

public void draw ()
{
    // internal clock was updated
    if ( lastSec != second() )
    {
        background( 255 );
    
        int[] v = clockwork.next();
        
        for ( int i = 0; i < v.length; i++ )
        {
            fill(200-i*100);
            arc( width/2, height/2,
                 height - (i * height/3.0f), height - (i * height/3.0f),
                 -HALF_PI, map(v[i],0,60,0,TWO_PI)-HALF_PI );
        }
        
        // remember for next loop
        lastSec = second();
    }
}

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "variations_2" });
    }
}
