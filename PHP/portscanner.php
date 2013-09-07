<html>
<head>
<title>PortScanner</title>
<script src="js/jquery-2.0.2.min.js" type="text/javascript"></script>
<script src="js/loadFields.js" type="text/javascript" ></script>
</head>
<body>
<form method="post" >
    <label for="domain">Domain/IP:</label><br />
    <input type="text" name="domain" /><br />
	<label for="startport">Start Port:</label><br />
    <input type="text" name="startport" /><br />
	<label for="endport">End Port:</label><br />
    <input type="text" name="endport" /><br /><br />
    <input type="submit" value="Scan" />
</form>
<br />
<hr />
<br />

<div class="results">
<?php
if((!empty($_POST['domain'])) && (!empty($_POST['startport'])) && (!empty($_POST['endport']))) {    
	
	$domain = $_POST['domain'];
	
	if($_POST['startport'] > $_POST['endport'])
	{
		$startport = $_POST['endport'];
		$endport = $_POST['startport'];
	}
	else
	{
		$startport = $_POST['startport'];
		$endport = $_POST['endport'];
	}
	
	echo 'Scan started for: <b><u>' . $domain . '</u></b> from Port: <b><u>' . $startport . '</u></b> to Port: <b><u>' . $endport . '</u></b><br /><br />';
	echo '<script type="text/javascript">sendRequest("' . $domain . '", ' . $startport . ', ' . $endport . ');</script>';
	
}
else
{
	echo "Please fill in all fields!";
}
?>
</div>

</body>
</html>