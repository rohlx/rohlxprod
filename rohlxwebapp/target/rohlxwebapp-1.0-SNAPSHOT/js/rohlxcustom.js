function initialize() {
  var mapOptions = {
    zoom: 18,
    center: new google.maps.LatLng(8.148,77.568455),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  var map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
  /** Needed to bring the map into the view **/
  $('#map-canvas')[0].scrollIntoView()
}

function loadScript() {
    
  
  if(!isGoogleAPILoadedAlready())
  {
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' +
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