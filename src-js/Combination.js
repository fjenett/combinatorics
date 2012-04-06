var Combination = (function(){
	
	var Combination = function () {
		
		this.elements = arguments[0];// || throw "Combination.elements can not be null";
		this.length = arguments[1] || this.elements; //alert( 'Combination.length can not be null' );
		
		if ( this.length == 0 )
			this.totalResults = BigInteger( BigInteger.ZERO );
		else if ( this.length == 1 )
			this.totalResults = BigInteger( this.elements );
		else
			this.totalResults = this.factorial( this.elements ).
									divide( this.factorial( this.length ).
										multiply( this.factorial(this.elements-this.length) ) );
		
		this.rewind();
	}
	
	Combination.prototype = new CombinatoricsBase();
	Combination.prototype.constructor = Combination;
	
	Combination.prototype.rewind = function () {
		
		this.current = BigInteger( BigInteger.ZERO );
		
		this.indices = new Array( this.length );
		for ( var i = 0; i < this.indices.length; i++ )
			this.indices[i] = i;
	}
	
	/* boolean */
	Combination.prototype.hasMore = function () {
		
		return this.current.compare(this.totalResults) < 0;
	}
	
	/* int[] */
	Combination.prototype.next = function () {
		
		if ( this.current.compare(BigInteger.ZERO) == 0 ) {
			
			this.increase();
			return this.indices.slice(0);
		}
		
		if ( this.current.compare( this.totalResults ) == 0 )
			return this.indices.slice(0);
		
		for ( var n = this.indices.length-1; n >= 0; n-- ) {
			
			if ( n == this.indices.length-1 || this.indices[n+1] == 0 )
			{
				this.indices[n]++;
				
				for ( var k = n+1; k < this.indices.length; k++ )
				{
					this.indices[k] = this.indices[k-1] + 1;
				}
			}
			
			this.indices[n] = this.indices[n] % (this.elements - (this.indices.length-1 - n));
		}
		
		this.increase();
		
		return this.indices.slice(0);
	}

	return Combination;

})();