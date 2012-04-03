
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
    
    Transformation ( int _type, float[] _values )
    {
        type = _type; values = _values;
    }
    
    // apply the transformation to drawing context
    void apply ()
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
