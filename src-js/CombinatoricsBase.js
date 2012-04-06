var CombinatoricsBase = (function(){
	
	var CombinatoricsBase = function () {
		
		/* int */ 		 this.elements = arguments[0] || NaN;
		/* int */ 		 this.length = arguments[1] || NaN;
		/* BigInteger */ this.totalResults = undefined;
		/* BigInteger */ this.current = undefined;
		/* int[] */ 	 this.indices = [];
		
	}
	
	/* int */ 
	CombinatoricsBase.prototype.numberOfElements = function () {
		return this.elements;
	}
	
	/* float */ 
	CombinatoricsBase.prototype.positionInPercent = function ()
	{
		var precision = arguments[0] || false;
		var percent = this.current.valueOf() / this.totalResults.valueOf();
		
		// TODO
		// if ( precision ) {
		// 	var scale = Math.pow( 10, precision );
		// 	percent = percent.multiply( scale ) / scale;
		// }
		
		return percent * 100.0;
	}
	
	/* int */
	CombinatoricsBase.prototype.position = function ()
	{
		return this.current.valueOf();
	}
	
	CombinatoricsBase.prototype.positionAsInt = CombinatoricsBase.prototype.position;
	CombinatoricsBase.prototype.positionAsLong = CombinatoricsBase.prototype.position;
	
	CombinatoricsBase.prototype.total = function ()
	{
		return BigInteger( this.totalResults );
	}
	
	CombinatoricsBase.prototype.totalAsInt = function () {
		return this.total().valueOf();
	}
	CombinatoricsBase.prototype.totalAsLong = CombinatoricsBase.prototype.totalAsInt;
	
	/* abstract */
	CombinatoricsBase.prototype.rewind = function () {}
	CombinatoricsBase.prototype.hasMore = function () {}
	CombinatoricsBase.prototype.next = function () {}
	
	/* int[] */
	CombinatoricsBase.prototype.nextAndStep = function ( step ) {
		
		var nextResult = this.next();

		var i = 0;
		while (i < step-1)
		{
			this.next();
			i++;
		}

		return nextResult;
	}
	
	CombinatoricsBase.prototype.increase = function () {
		this.current = this.current.add( BigInteger.ONE );
	}
	
	CombinatoricsBase.prototype.decrease = function () {
		this.current = this.current.subtract( BigInteger.ONE );
	}
	
	/* BigInteger */
	CombinatoricsBase.prototype.factorial = function ( n )
	{
		var r = BigInteger.ONE;
		var ii = BigInteger.ONE;
		for ( var i = 1; i <= n; i++ )
		{
			r = r.multiply( ii );
			ii = ii.add( BigInteger.ONE );
		}
		return r;
	}
	
	return CombinatoricsBase;
})();
