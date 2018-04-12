(function(w) {
	/*动态兼容 -沉浸式状态栏 -ourchem type*/
	document.addEventListener('plusready', function() {
		console.log("Immersed-UserAgent: " + navigator.userAgent);
	}, false);

	var immersed = 0;
	var ms = (/Html5Plus\/.+\s\(.*(Immersed\/(\d+\.?\d*).*)\)/gi).exec(navigator.userAgent);
	if (ms && ms.length >= 3) {
		immersed = parseFloat(ms[2]);
	}
	w.immersed = immersed;

	if (!immersed) {
		return;
	}

	var defaultHeaderHeight = 44;
	var t = document.getElementById('header');
	t && (t.style.height = defaultHeaderHeight + immersed+ 'px' , t.style.paddingTop = immersed + 'px', t.style.background = '-webkit-linear-gradient(top,rgba(46,153,223,1),rgba(46,158,223,1))', t.style.color = '#FFF');
	
	/*首页特殊处理 -各个app根据页面样式进行处理*/
	t = document.getElementById('homeHeader');
	t && (t.style.height = defaultHeaderHeight + immersed+ 'px' , t.style.paddingTop = immersed + 'px', t.style.background = '-webkit-linear-gradient(top,rgba(46,153,223,1),rgba(46,158,223,1))', t.style.color = '#FFF');
	if(t){
		document.getElementById("mask").style.top = defaultHeaderHeight + immersed+ 'px';
	}
	
	t = document.querySelector('.mui-content');
	t && (t.style.marginTop = immersed + 'px');

	t = document.querySelector('.mui-content-padded');
	t && (t.style.marginTop = immersed + 'px');

	t = document.querySelector('.mui-slider.mui-fullscreen');
	t && (t.style.marginTop = immersed + 'px');

	t = document.getElementById('segmentedControl');//我的收藏
	t && (t.style.marginTop = immersed + 'px');

	t = document.querySelector('.mui-view .mui-pages');//单页div模式 -setting页
	t && (t.style.marginTop = immersed + 'px');
	
	t = document.querySelector('.mui-view .mui-navbar');//单页div模式 -header-setting页
	if (t) {
		var inners = document.querySelectorAll('.mui-navbar-inner');
		if(inners && inners.length > -1) {
			t.style.height = defaultHeaderHeight + immersed+ 'px';
			for (var i = 0; i < inners.length; i++) {
				inners[i].style.height = defaultHeaderHeight + immersed+ 'px' ;
			    inners[i].style.paddingTop = immersed + 'px';
			    inners[i].style.background = '-webkit-linear-gradient(top,rgba(46,153,223,1),rgba(46,158,223,1))';
			    inners[i].style.color = '#FFF';
			}
		}
	}
	// t = document.getElementById('dcontent');
	// t && (t.style.marginTop = immersed + 'px');
})(window);