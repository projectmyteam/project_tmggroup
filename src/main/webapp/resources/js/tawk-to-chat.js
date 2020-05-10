$(document).ready(function(){ 
	<!--Start of Tawk.to -->

	var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
	var name = $("#namechat").val();
	var email = $("#emailchat").val();
	if(name != null){
		console.log(email);
		Tawk_API.visitor = {

				name : name,
				email : email
		};
	}
	(function(){
		var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
		s1.async=true;
		s1.src='https://embed.tawk.to/5eb7bd858ee2956d739fb41a/default';
		s1.charset='UTF-8';
		s1.setAttribute('crossorigin','*');
		s0.parentNode.insertBefore(s1,s0);
	})();
	<!--End of Tawk.to -->
});