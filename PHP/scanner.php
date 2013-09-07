<?php
if((!empty($_POST['domain'])) && (!empty($_POST['port']))) {    

		$port = $_POST['port'];
		$name = getservbyport($port, "tcp");
        if($pf = @fsockopen($_POST['domain'], $port, $err, $err_string, 1)) 
		{
            echo "<span style=\"color:green\">[+] OPEN</span>: Port $port ($name)<br />";
            fclose($pf);
        } 
		else 
		{
            echo "<span style=\"color:red\">[-] CLOSED</span>: Port $port ($name)<br />";
        }
}
?>