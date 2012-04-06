var Variation = (function(){
	
	var Variation = function () {
		
		this.elements = arguments[0];// || throw "Variation.elements can not be null";
		this.length = arguments[1];// || throw "Variation.length can not be null";
		
		this.bigElements = BigInteger( this.elements );
		this.totalResults = this.bigElements.pow( this.length );
		
		this.rewind();
		
		this.deviders = new Array( this.length );
		for ( var i = 0, k = this.length; i < k; i++ ) {
			this.deviders[this.length-1-i] = this.bigElements.pow(i+1).divide(this.bigElements);
		}
	}
	Variation.prototype = new CombinatoricsBase();
	Variation.prototype.constructor = Variation;
	
	Variation.prototype.rewind = function ()
	{
		this.current = BigInteger( BigInteger.ZERO );
		
		this.indices = new Array( this.length );
		
		for ( var k = 0, n = this.length; k < n; k++ )
			this.indices[k] = 0;
	}
	
	Variation.prototype.hasMore = function ()
	{
		var i = this.current.compare( this.totalResults );
		return i < 0;
	}
	
	Variation.prototype.next = function ()
	{
		if ( this.current.compare(BigInteger.ZERO) == 0 )
		{
			this.increase();
			return this.indices.slice(0); // clone
		}
		
		if ( this.current.compare(this.totalResults) == 0 )
			return this.indices.slice(0); // clone
		
		for ( var k = this.length-1; k >= 0; k-- )
		{
			this.indices[k] = parseInt( this.current.divide( this.deviders[k] ).remainder( this.bigElements ).valueOf() );
		}
		
		this.increase();
		
		return this.indices.slice(0); // clone
	}
	
	
	return Variation;
	
})();