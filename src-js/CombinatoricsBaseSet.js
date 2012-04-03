var CombinatoricsBaseSet = (function(){

	/* int elements, int from, int to */
	var CombinatoricsBaseSet = function () {
		
		/* int */ 				this.from = arguments[1] || 0;
		/* int */				this.to = arguments[2] || arguments[0];
		/* int */ 				this.currentSet = NaN;
		/* CombinatoricsBase */ this.generator = undefined;
		
		this.elements = arguments[0];
		this.length = this.from;
		
		this.rewind();
	}
	CombinatoricsBaseSet.prototype = new CombinatoricsBase();
	CombinatoricsBaseSet.prototype.constructor = CombinatoricsBaseSet;
	
	CombinatoricsBaseSet.prototype.hasMore = function () {
		return this.hasMoreInSet();
	}
	
	/* boolean */
	CombinatoricsBaseSet.prototype.hasMoreInSet = function () {
		if ( this.currentSet < this.to )
			return true;
		else
			return this.generator.hasMore();
	}
	
	/* int[] */
	CombinatoricsBaseSet.prototype.next = function () {
		var tmp = this.generator.next();
		if ( !this.generator.hasMore() && this.hasMoreInSet() )
		{
			this.currentSet++;
			this.length = this.from + this.currentSet; // needed to nextAndStep()
			this.generator = this.nextGenerator();
		}
		return tmp;
	}
	
	/* CombinatoricsBase */
	CombinatoricsBaseSet.prototype.nextGenerator = function () {}
	
	return CombinatoricsBaseSet;
})();