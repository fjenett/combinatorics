/**
 *    A really basic non-visual example "showing" how to use the Combinatorics lib.
 *    Press run and watch that console down there!
 *
 *    fjenett 20090305
 */

import de.bezier.math.combinatorics.*;


println();
println( "----------------------------------" );
println( "Variation a.k.a. bikelock picking." );
println( "----------------------------------" );
println();

// Hack your neighbbors locker or borrow his bike with class "Variation"

int rings = 3;
char onTheRing[] = "0123456789".toCharArray();

Variation variation = new Variation( onTheRing.length, rings );

println( "Try one of these .." );
println( "-------------");
while ( variation.hasMore() )
{
    int[] v = variation.next();
    print( "| " );
    for ( int j = 0; j < v.length; j++ )
    {
        print( onTheRing[ v[j] ] );
        if ( j < v.length-1 )
            print( " | " );
    }   
    println(" |");
}
println( "-------------");
println( ".. i mentioned you'd have to try each by hand, right?" );


println();
println( "--------------------------------------------------------" );
println( "CombinationSet a.k.a. just can't decide what wear today." );
println( "--------------------------------------------------------" );
println();

// Help yourself dress up with "combinations"

String things[] = new String[] { "pants", "t-shirt", "socks", "hat" };

CombinationSet combis = new CombinationSet( things.length );

while ( combis.hasMore() )
{
    int c[] = combis.next();
    
    if ( c.length == 0 ) // first result is empty!
    {
        println( "nothing could be an option too ... or?" );
        continue;
    }
    
    if ( c.length == 1 )
        print( "only " );
    
    print( "the " );
    for ( int j = 0; j < c.length; j++ )
    {
        print( things[c[j]] );
        
        if ( j < c.length-1 )
            if ( j >= 1 )
                print( " and the " );
            else
                print( " with the " );
    }
    if ( !combis.hasMore() )
        println( " ... nah, that'd be overdressed." );
    else
        println( "?" );
}


println();
println( "----------------------------------------" );
println( "Permutation a.k.a. we have to rearrange." );
println( "----------------------------------------" );
println();

// Permutate some letters to generate sparkles with class "Permutation"

char chars[] = ".;\"*".toCharArray(); // Me is lazy today: convert string to char[]

Permutation permutation = new Permutation( chars.length );

while ( permutation.hasMore() )
{
    int[] p = permutation.next();
    for ( int j = 0; j < p.length; j++ )
    {
        print( chars[p[j]] );
        if ( j < p.length-1 )
            print( " " );
    }   
    println();
}


exit();
