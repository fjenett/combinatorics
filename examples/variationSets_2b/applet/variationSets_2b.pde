/**
 *    This example shows how to loop all variations for a given number of elements.
 *    
 *    A "variation" means that elements can be used multiple times (or not at all) and 
 *    that the order is important ( "a,b" is not "b,a", so it's counted ).
 *
 *    given [a, b, c] you'll get:
 *
 *    [],
 *    [a], [b], [c],
 *    [a, a], [a, b], [a, c], [b, a], [b, b], [b, c], [c, a], [c, b], [c, c],
 *    [a, a, a], [a, a, b], .., [c, c, b], [c, c, c]
 *
 *    fjenett 20090305
 */

import de.bezier.math.combinatorics.*;

VariationSet variations;
int drawingWidth;
float gRotation = 0.05;

void setup ()
{
    size( 300, 300 );
    
    drawingWidth = width/8;
    
    variations = new VariationSet( 10 );
    
    frameRate( 5 );
    smooth();
    noStroke();
}

void draw ()
{
    background( 0 );
    
    translate( width/2, height/2 );
    
    int[] v;
    v = variations.next();
    //v = variations.nextAndStep(frameCount*10);  //or try this to see what's going on
    
    scale( width / (drawingWidth * (v.length + 2.0)) ); // scale drawing to fit into window
    
    rotate( -HALF_PI ); // -90°, "corrects" for Processing looking to the right at 0° degrees
    rotate( gRotation );
    
    if ( !variations.hasMore() )
        variations.rewind();
        
    float radius = 0;
    // length of a circle: 2*PI * radius
    if ( v.length > 1 )
        radius = 3*( (1/TWO_PI) * (drawingWidth * v.length) ); // -90°, corrects for Processing looking to the right at 0° degrees
    
    float[] p = calcNGon(0,0, radius,radius, v.length);
    
    for ( int i = 0, ii = 0; i < v.length; i++, ii+=2 )
    {
        pushMatrix();
        
        translate( p[ii], p[ii+1] );
        rotate( -gRotation ); // unrotate global rotation
        
        switch ( v[i] )
        {
            case 0:
              drawTriangle();
              break;
            
            case 1:
              drawRect();
              break;
              
            case 2:
              drawPentagon();
              break;
              
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
              drawNGon( 0, 0, drawingWidth, drawingWidth, v[i] );
              break;
              
            case 9:
              drawCircle();
              break;
        }
        popMatrix();
    }
    
    gRotation += 0.02; // update global rotation
}

void drawTriangle ()
{
    drawNGon ( 0, 0, drawingWidth, drawingWidth, 3 );
}

void drawRect ()
{
    drawNGon ( 0, 0, drawingWidth, drawingWidth, 4 );
}

void drawPentagon ()
{
    drawNGon ( 0, 0, drawingWidth, drawingWidth, 5 );
}

void drawCircle ()
{
    drawNGon ( 0, 0, drawingWidth, drawingWidth, 12 );
}

void drawNGon ( float x, float y, float w, float h, int s )
{
    float[] points = calcNGon(x,y,w,h,s);
    
    fill( 255 );
    
    beginShape();
    for ( int a = 0; a < points.length; a+=2 )
    {
        vertex( points[a], points[a+1] );
    }
    endShape( CLOSE );
}

float[] calcNGon ( float x, float y, float w, float h, int s )
{
    int step = int(360.0 / s);
    float points[] = new float[s*2]; 
    
    for ( int a = 0, i = 0; a < 360 && i < points.length; a+=step, i+=2 )
    {
        points[i]   = x + cos( radians( a ) ) * w;
        points[i+1] = y + sin( radians( a ) ) * h;
    }
    
    return points;
}
