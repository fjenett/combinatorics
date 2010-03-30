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

public class permutation_1 extends PApplet {

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




Transformation transforms[]; // as defined in the next tab

Permutation permutations;
boolean pGenerated = false;
int p = 0;
int blockSize;
PFont fnt;


public void setup ()
{
    size( 400, 400 );
    
    fnt = createFont( "Verdana", 10 );
    textFont( fnt );    
    
    blockSize = width/3;

    // be carefull when adding more elements ... 
    // it will increase the time needed to generate the permutations quite a bit!
    transforms = new Transformation[] {
        new Transformation( Transformation.SCALE,  2/4.0f ),
        new Transformation( Transformation.SCALE, -2/4.0f ),
        new Transformation( Transformation.ROTATE,  HALF_PI/2 ),
        new Transformation( Transformation.ROTATE, -HALF_PI/2 ),
        new Transformation( Transformation.TRANSLATE, 0, blockSize ),
        new Transformation( Transformation.TRANSLATE, blockSize, blockSize ),
        new Transformation( Transformation.TRANSLATE, blockSize, 0 )
    };
    
    permutations = new Permutation( transforms.length );
    
    background( 0 );
    noStroke();
    
    frameRate( 10 );
}

public void draw ()
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

public void drawCounter ()
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
          "(" + permutations.positionInPercent(2) + "%)", 
          5, 15 );
    
    popStyle();   
}

/**
 *    Just a simple class to store a transformation.
 */
 
class Transformation
{
    // constants for types of transformations
    final static int SCALE = 0;
    final static int ROTATE = 1;
    final static int TRANSLATE = 2;
    
    // variables to store an instances type and values
    int type;
    float values[];
 
    // contructors, these get called by "new Transformation(..)"
    
    Transformation ( int _type, float _value )
    {
        this( _type, new float[]{_value} );
    }
    
    Transformation ( int _type, float _valuex, float _valuey )
    {
        this( _type, new float[]{_valuex,_valuey} );
    }
 
    Transformation ( int _type, float[] _values )
    {
        type = _type; values = _values;
    }
    
    // apply the transformation to drawing context
    public void apply ()
    {
        switch ( type )
        {
            case SCALE:
                 scale( values[0] );
                 break;
                 
            case ROTATE:
                 rotate( values[0] );
                 break;
                 
            case TRANSLATE:
                 translate( values[0], values[1] );
                 break;
        }
    }
}

    static public void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#ffffff", "permutation_1" });
    }
}
