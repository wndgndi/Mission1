var currentPosition = document.getElementById('btn-getCurPosition');
currentPosition.addEventListener('click', function() {
    navigator.geolocation.getCurrentPosition(function(position) {
        var curLat = position.coords.latitude;
        var curLnt = position.coords.longitude;

        document.getElementById('lat').value = curLat;
        document.getElementById('lnt').value = curLnt;
    });
});
