var Variation = (function(){
	
	var Variation = function () {
		
		if ( arguments.length > 0 ) {
			this.elements = parseInt(arguments[0]);
		}
		if ( arguments.length > 1 ) {
			this.length = parseInt(arguments[1]);
		}
		
		this.totalResults = Math.pow( this.elements, this.length );
		
		this.rewind();
		
		this.deviders = new Array( this.length );
		for ( var i = 0; i < this.length; i++ )
			this.deviders[this.length-1-i] = Math.pow(this.elements, i+1) / this.elements;
	}
	Variation.prototype = new CombinatoricsBase();
	Variation.prototype.constructor = Variation;
	
	Variation.prototype.rewind = function ()
	{
		this.current = 0;
		
		this.indices = new Array( this.length );
		
		for ( var k = 0; k < this.length; k++ )
			this.indices[k] = 0;
	}
	
	Variation.prototype.hasMore = function ()
	{
		return this.current < this.totalResults;
	}
	
	Variation.prototype.next = function ()
	{
		if ( this.current == 0 )
		{
			this.increase();
			return this.indices.slice(0); // clone
		}
		
		if ( this.current == this.totalResults )
			return this.indices.slice(0); // clone
		
		for ( var k = this.length-1; k >= 0; k-- )
		{
			this.indices[k] = parseInt( parseInt(this.current / this.deviders[k]) % this.elements );
		}
		
		this.increase();
		
		return this.indices.slice(0); // clone
	}
	
	
	return Variation;
	
})();