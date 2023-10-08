$(document).ready(function() {
    $("#btn-getWifiList").click(function() {
       var lat = $("#lat").val();
       var lnt = $("#lnt").val();
       console.log("lat: " + lat);
       console.log("lnt: " + lnt);
    
        $.ajax({
            url: "history",
            method: "POST",
            data: {lat: lat, lnt: lnt},
            
        });
   
    });
});