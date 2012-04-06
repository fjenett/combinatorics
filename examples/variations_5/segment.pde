
class Segment
{
    float points[]; // [x,y, x2,y2, x3,y3, ...]

    Segment ( float pnts[] )
    {
        points = pnts;
    }
    
    void draw ()
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
