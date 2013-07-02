/** Custom javascript for rohlx **/
var rohlx = rohlx || {};

(function($, mod, undefined) {

	mod.init = function() {
		$("#openmaps").unbind().click(mod._loadScript);
		$(window).load(mod._animate);
	};

	/** Function to add the marker for rohlx **/

	mod._addMarker = function(map, latlong, title, content) {
		var markerOptions = {
			position : latlong,
			map : map,
			title : title,
			clickable : true
		};
		var marker = new google.maps.Marker(markerOptions);
	};

	/** Loads and Initializes sript when the address link is clicked **/
	mod.initialize = function() {
		var googleLatandLogRohlx = new google.maps.LatLng(8.148, 77.568455);
		var mapOptions = {
			zoom : 18,
			center : googleLatandLogRohlx,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		var map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);

		/** Needed to bring the map into the view **/
		$('#map-canvas')[0].scrollIntoView();

		mod._addMarker(map, googleLatandLogRohlx, "Rohlx", "Rohlx1");

	};

	/** Call to load google maps script **/
	mod._loadScript = function() {
		if (!mod._isGoogleAPILoadedAlready()) {
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&'
					+ 'callback=rohlx.initialize';
			document.body.appendChild(script);
		}

	};

	/** Script to add animation to load the sections for the menus **/
	mod._animate = function() {
		$('.scroller').click(function() {
			var section = $($(this).data("section"));
			var top = section.offset().top - 82;
			$("html, body").animate({
				scrollTop : top
			}, 700);
			return false;
		});
	};

	/** This function checks if the javascript to load the google api is already loaded or not **/
	mod._isGoogleAPILoadedAlready = function() {
		if ($('script[src*="maps.googleapis.com"]').val() === undefined) {
			return false;
		}
		return true;
	};
        
        /** public function to check for validation errors and display next to tag **/
        
        mod.displayErrorMessage = function(){
            
        }

})($, rohlx);

/** invoke first method **/
$(rohlx.init);
