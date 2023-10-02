let historyObject = {
        
        init: function() {
             let _this = this;
            
            $("#btn-getList").on("click", () => {
                _this.getWifiList();
            })
        },
        
        getWifiList: function() {
            alert("와이파이 정보 요청됨");
        }
}

historyObject.init();