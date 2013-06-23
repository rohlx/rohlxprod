


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

function animate(){
$('.scroller').click(function(){
var section = $($(this).data("section"));
var top = section.offset().top-82;
$("html, body").animate({ scrollTop: top }, 700);
return false;
});
}


function isGoogleAPILoadedAlready(){
    
    if($('script[src*="maps.googleapis.com"]').val() === undefined)
    {
        return false;
    }
    return true;
}


$("#openmaps").unbind().click(loadScript);
$(window).load(animate);