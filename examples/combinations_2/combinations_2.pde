/**
 *    Hallo norm.to.
 *    http://lineto.com/The+Designers/Norm/Sign-Generator/Software/Sign-Generator+1.0/
 *
 *    Click inside window to speed things up a little ..
 *
 *    fjenett 20090306
 */

import de.bezier.math.combinatorics.*;

int connections[][];
Combination combinations;
float w3, w32;
    
int spots = 3;
int spotsSq = spots * spots;

void setup ()
{
    size( 300, 300 );
    
    w3 = width/float(spots);
    w32 = w3/2.0;
    
    // find all possible connections (lines) between our number of spots
    Combination cc = new Combination( spotsSq, 2); // 2 points are a connection, spots ^ 2 to connect
    
    connections = new int[ cc.totalAsInt() ][2];
    
    int c = 0; while ( cc.hasMore() )
    {
        connections[c] = cc.next();
        c++;
    }
    
    // find all combinations (no dupls, ignore order) for 4 connections
    combinations = new Combination( connections.length, 4 );
    
    strokeWeight( 20 );
    smooth();
    fill(0);
    
    frameRate( 7 );
}

void draw ()
{
    background( 255 );
    
    noStroke();
    for ( int i = 0; i < spotsSq; i++ )
        ellipse( w32 + int(i%spots) * w3, w32 + int(i/spots) * w3, 4, 4 );
    
    if ( !combinations.hasMore() )
        combinations.rewind();
    
    stroke( 0 );
    
    int c[] = combinations.next();
    for ( int i = 0; i < c.length; i++ )
    {        
        int cc[] = connections[c[i]];
        for ( int ii = 0; ii < cc.length; ii+=2 )
        {
            line( w32 + int(cc[ii  ]%spots) * w3, w32 + int(cc[ii  ]/spots) * w3, 
                  w32 + int(cc[ii+1]%spots) * w3, w32 + int(cc[ii+1]/spots) * w3 );
        }
    }
}

void mousePressed ()
{
    frameRate( 40 );  
}

void mouseReleased ()
{
    frameRate( 7 );   
}
