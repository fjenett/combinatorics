var CombinationSet = (function(){
	
	var CombinationSet = function () {
		CombinatoricsBaseSet.apply( this, arguments );
	}
	CombinationSet.prototype = new CombinatoricsBaseSet();
	CombinationSet.prototype.constructor = CombinationSet;
	
	CombinationSet.prototype.rewind = function () {
		
		this.current = BigInteger( BigInteger.ZERO );
		this.totalResults = BigInteger( BigInteger.ZERO );
		
		var c = new Array( this.to-this.from+1 );
		for ( var i = this.from, k = this.to; i <= k; i++ )
		{
			c[i] = new Combination( this.elements, i );
			this.totalResults = 
				this.totalResults.add( c[i].total() );
		}
		
		this.generator = new Combination( this.elements, this.from );
		
		this.current = BigInteger( BigInteger.ZERO );
		this.currentSet = this.from;
	}
	
	/* CombinatoricsBase */
	CombinationSet.prototype.nextGenerator = function () {
		
		this.generator = new Combination( this.elements, this.currentSet );
		return this.generator;
	}
	
	return CombinationSet;
})();