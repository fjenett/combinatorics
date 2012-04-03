/**
 *    This example shows a permutation of a set of transforms.
 *
 *    A "permutation" is when just the order of elements is changed but the amout
 *    of elements stays the same.
 *
 *    given [a, b, c] you'll get:
 *
 *    [a, b, c], [a, c, b], [b, c, a], [b, a, c], [c, a, b], [c, b, a]
 *
 *    fjenett 20090305
 */

import de.bezier.math.combinatorics.*;


Transformation transforms[]; // as defined in the next tab

Permutation permutations;
boolean pGenerated = false;
int p = 0;
int blockSize;
PFont fnt;


void setup ()
{
    size( 400, 400 );
    
    fnt = createFont( "Verdana", 10 );
    textFont( fnt );    
    
    blockSize = width/3;

    // be carefull when adding more elements ... 
    // it will increase the time needed to generate the permutations quite a bit!
    transforms = new Transformation[] {
        new Transformation( Transformation.SCALE,     new float[]{  2/4.0               }),
        new Transformation( Transformation.SCALE,     new float[]{ -2/4.0               }),
        new Transformation( Transformation.ROTATE,    new float[]{  HALF_PI/2           }),
        new Transformation( Transformation.ROTATE,    new float[]{ -HALF_PI/2           }),
        new Transformation( Transformation.TRANSLATE, new float[]{ 0, blockSize         }),
        new Transformation( Transformation.TRANSLATE, new float[]{ blockSize, blockSize }),
        new Transformation( Transformation.TRANSLATE, new float[]{ blockSize, 0         })
    };
    
    permutations = new Permutation( transforms.length );
    
    background( 0 );
    noStroke();
    
    frameRate( 10 );
}

void draw ()
{
    background( 0 );
    
    drawCounter();
    
    translate( width/2, height/2 );
    rectMode( CENTER );
    fill( 255 );
    
    // to see the single elements in action uncomment this line:
    //fill( 255, 100 ); stroke( 255 ); strokeWeight( 1/2.0 );
    
    int p[] = permutations.next();
    pushMatrix();
        for ( int i = 0; i < p.length; i++ )
        {
            transforms[ p[i] ].apply();
            rect( 0,0, blockSize, blockSize );
        }
    popMatrix();
}

void drawCounter ()
{
    pushStyle();
    
    // clear back
    fill( 0 );
    noStroke();
    rectMode( CORNER );
    rect( 0, 0, width, 20 );
    
    // write text
    fill( 255 );
    text(       permutations.position()           +
          "/" + permutations.total()              + " " + 
          "(" + permutations.positionInPercent() + "%)", 
          5, 15 );
    
    popStyle();   
}
