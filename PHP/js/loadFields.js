function sendRequest(domain, startport, endport) {

	var ajaxRequest = new XMLHttpRequest();

	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4) {
			$(".results").append(ajaxRequest.responseText);
			startport++;
			if(startport<=endport) {
					ajaxRequest.open("POST", "scanner.php", true);
					ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					ajaxRequest.send("domain=" + domain + "&port=" + startport);
			} else {
				$(".results").append("<br /><br />[!] DONE");
			}
		}
	};
	ajaxRequest.open("POST", "scanner.php", true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxRequest.send("domain=" + domain + "&port=" + startport);

}