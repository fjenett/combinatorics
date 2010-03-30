/**
 *    This example shows how to loop all variations for a given number of elements.
 *    
 *    A "variation" means that elements can be used multiple times (or not at all) and 
 *    that the order is important ( "a,b" is not "b,a", so it's counted ).
 *
 *    given [a, b, c] you'll get:
 *
 *    [a], [b], [c],
 *    [a, a], [a, b], [a, c], [b, a], [b, b], [b, c], [c, a], [c, b], [c, c],
 *    [a, a, a], [a, a, b], .., [c, c, b], [c, c, c]
 *
 *    fjenett 20090305
 */

import de.bezier.math.combinatorics.*;

VariationSet variations;
int drawingWidth;

void setup ()
{
    size( 300, 300 );
    
    drawingWidth = width/8;
    
    variations = new VariationSet( 3 );
    
    frameRate( 5 );
    smooth();
    noStroke();
}

void draw ()
{
    background( 0 );
    
    translate( width/2, height/2 );
    
    if ( !variations.hasMore() )
        variations.rewind();
    
    int[] v = variations.next();
    for ( int i = 0; i < v.length; i++ )
    {
        pushMatrix();
        
        if ( v.length > 1 )
        {
            float spacing = (drawingWidth) * (v.length-1);
            translate( map(i, 0, v.length-1, -spacing, spacing), 0 );
        }
        
        switch ( v[i] )
        {
            case 0:
              drawTriangle();
              break;
              
            case 1:
              drawCircle();
              break;
              
            case 2:
              drawPentagon();
              break;
        }
        popMatrix();
    }
}

void drawTriangle ()
{
    drawNGon ( 0, 0, drawingWidth, drawingWidth, 3 );
}

void drawCircle ()
{
    drawNGon ( 0, 0, drawingWidth, drawingWidth, 12 );
}

void drawPentagon ()
{
    drawNGon ( 0, 0, drawingWidth, drawingWidth, 5 );
}

void drawNGon ( float x, float y, float w, float h, int s )
{
    float[] points = calcNGon(x,y,w,h,s);
    
    rotate( -HALF_PI ); // -90°, corrects for Processing looking to the right at 0° degrees
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
    
    for ( int a = 0, i = 0; a < 360; a+=step, i+=2 )
    {
        points[i]   = x + cos( radians( a ) ) * w;
        points[i+1] = y + sin( radians( a ) ) * h;
    }
    
    return points;
}
