$(document).ready(function() {
    $("#btn-getWifiList").click(function() {
       var lat = $("#lat").val();
       var lnt = $("#lnt").val();
       console.log("lat: " + lat);
       console.log("lnt: " + lnt);
    
        $.ajax({
            url: "Mission1/history",
            method: "POST",
            data: {lat: lat, lnt: lnt},
            success: function(response){
                alert(response);
            }
        });
   
    });
});