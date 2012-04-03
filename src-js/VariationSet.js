var VariationSet = (function(){
	
	var VariationSet = function () {
		CombinatoricsBaseSet.apply( this, arguments );
	}
	VariationSet.prototype = new CombinatoricsBaseSet();
	VariationSet.prototype.constructor = VariationSet;
	
	VariationSet.prototype.rewind = function () {
		
		this.current = 0;
		this.totalResults = 0;
		var v = new Array( this.to-this.from+1 );
		
		for ( var i = 0, k = this.to-this.from; i <= k; i++ )
		{
			v[i] = new Variation( this.elements, i );
			this.totalResults = this.totalResults + v[i].total();
		}
		
		this.generator = new Variation( this.elements, this.from );
		this.current = 0;
		this.currentSet = this.from;
	}
	
	/* CombinatoricsBase */
	VariationSet.prototype.nextGenerator = function () {
		
		this.generator = new Variation( this.elements, this.currentSet );
		return this.generator;
	}
	
	return VariationSet;
	
})();