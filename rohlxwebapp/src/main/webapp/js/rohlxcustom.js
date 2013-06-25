/** Custom javascript for rohlx **/


/** Function to add the marker for rohlx **/
function addMarker(map,latlong,title,content)
{
  var markerOptions = {
    position: latlong,
    map:map,
    title : title,
    clickable : true
  };
  var marker = new google.maps.Marker(markerOptions);
}

/** Loads and Initializes sript when the address link is clicked **/
function initialize() {
  var googleLatandLogRohlx = new google.maps.LatLng(8.148,77.568455);	
  var mapOptions = {
    zoom: 18,
    center: googleLatandLogRohlx,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  var map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
  
  
  /** Needed to bring the map into the view **/
  $('#map-canvas')[0].scrollIntoView();
  
  addMarker(map,googleLatandLogRohlx,"Rohlx","Rohlx1"); 
 
}

/** Call to load google maps script **/
function loadScript() {
    
  if(!isGoogleAPILoadedAlready())
  {
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&' +
      'callback=initialize';
  document.body.appendChild(script);
  }
  
} 

/** Script to add animation to load the sections for the menus **/
function animate(){
$('.scroller').click(function(){
var section = $($(this).data("section"));
var top = section.offset().top-82;
$("html, body").animate({ scrollTop: top }, 700);
return false;
});
}

/** This function checks if the javascript to load the google api is already loaded or not **/
function isGoogleAPILoadedAlready(){
    if($('script[src*="maps.googleapis.com"]').val() === undefined)
    {
        return false;
    }
    return true;
}


/** Other things to be loaded **/
$("#openmaps").unbind().click(loadScript);
$(window).load(animate);