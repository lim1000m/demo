/**
 * 페이지가 오픈 될때 appId로 
 * 이 프로젝트 접속 가능여부를 초기화 시킨다. 
 */
window.fbAsyncInit = function() {
    FB.init({
        appId   : '295287723964220',
        oauth   : true,
        status  : true, // check login status
        cookie     : true,  // enable cookies to allow the server to access 
  	  	xfbml      : true,  // parse social plugins on this page
  	  	version    : 'v2.0' // use version 2.0
    });
  };
 
  /**
   * 자바스크립트를 사용하는 패턴 중 한가지.. 
   * Anonymous function 이라고 하는데 흐음.. 
   * function(?,?,?)의 인자를 아래에서 받음.
   */
  (
  	function(d, s, id) {
  		var js, fjs = d.getElementsByTagName(s)[0];
  		if (d.getElementById(id)) return;
  		js = d.createElement(s); js.id = id;
  		js.src = "//connect.facebook.net/ko_KR/sdk.js";
  		fjs.parentNode.insertBefore(js, fjs);
  	}
  	(document, 'script', 'facebook-jssdk')
  );
  
function fb_login(){
    FB.login(function(response) {
        if (response.authResponse) {
            access_token = response.authResponse.accessToken; //get access token
            user_id = response.authResponse.userID; //get FB UID
            FB.api('/me', function(response) {
                user_email = response.email; //get user email
            });
            myPicture();
        } else {
            console.log('User cancelled login or did not fully authorize.');
        }
    }, {
        scope: 'publish_stream,email'
    });
}

/**
 * 사진첩 연동
 */
var myPicture = function getUserPicture() {
	FB.api(
			"/me/photos/uploaded", function (response) {
				console.log(response.data);
		      if (response && !response.error) {
		    	  if($.isArray(response.data)) {
		    		  $.each(response.data, function(i) {
		    			 $('#photoArea').append("<span id=picArea_"+i+">");
		    			 $('#photoArea').append("<img src="+response.data[i].picture+" id=img_"+i+" draggable='true' ondragstart='drag(this, event)' />");
		    			 $('#photoArea').append("</span>");
		    		  });
		    	  }
	  	    }
		}
	);		
}
