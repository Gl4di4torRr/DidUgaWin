$( function( ) {
	$("#ugaWon").hide();
	$("#ugaLost").hide();
	$.ajax( {
		type: "GET",
		url: "/uga/wins",
		success: function( data ) {
			console.log(data);
			if(data.didUgaWin === "W"){
				$("#ugaWon").show();
			}
			else if(data.didUgaWin === "L"){
				$("#ugaLost").show();
			}
			
		},
		error: function( data ) {
			alert("error = " + data);
		},
		failure: function( data ) {
			alert("failure = " + data);
		}
	});
	
});