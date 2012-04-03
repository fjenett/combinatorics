/**
 *    A simple clock.
 *
 *    fjenett 20090310
 */

import de.bezier.math.combinatorics.*;

Variation clockwork;
int lastSec = 0;

void setup ()
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

void draw ()
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
                 height - (i * height/3.0), height - (i * height/3.0),
                 -HALF_PI, map(v[i],0,60,0,TWO_PI)-HALF_PI );
        }
        
        // remember for next loop
        lastSec = second();
    }
}
