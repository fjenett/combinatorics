var CombinatoricsBase = (function(){
	
	var CombinatoricsBase = function () {
		
		/* int */ 		 this.elements = arguments[0] || NaN;
		/* int */ 		 this.length = arguments[1] || NaN;
		/* BigInteger */ this.totalResults = NaN;
		/* BigInteger */ this.current = NaN;
		/* int[] */ 	 this.indices = [];
		
	}
	
	/* int */ 
	CombinatoricsBase.prototype.numberOfElements = function () {
		return this.elements.valueOf();
	}
	
	/* float */ 
	CombinatoricsBase.prototype.positionInPercent = function ()
	{
		var precision = arguments[0] || false;
		var percent = this.current / this.totalResults;
		
		if ( precision ) {
			var scale = Math.pow( 10, precision );
			percent = parseInt(percent * scale) / scale;
		}
		
		return percent;
	}
	
	/* int */
	CombinatoricsBase.prototype.position = function ()
	{
		return this.current.valueOf;
	}
	
	CombinatoricsBase.prototype.positionAsInt = CombinatoricsBase.prototype.position;
	CombinatoricsBase.prototype.positionAsLong = CombinatoricsBase.prototype.position;
	
	CombinatoricsBase.prototype.total = function ()
	{
		return this.totalResults;
	}
	
	CombinatoricsBase.prototype.totalAsInt = CombinatoricsBase.prototype.total;
	CombinatoricsBase.prototype.totalAsLong = CombinatoricsBase.prototype.total;
	
	CombinatoricsBase.prototype.rewind = function () {}
	CombinatoricsBase.prototype.hasMore = function () {}
	CombinatoricsBase.prototype.next = function () {}
	
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
		this.current++;
	}
	
	CombinatoricsBase.prototype.decrease = function () {
		this.current--;
	}
	
	return CombinatoricsBase;
})();
