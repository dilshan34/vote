<?php

require_once('dbconnect.php');

$password = $_POST["password"];


$sql = "SELECT * FROM votes WHERE  nic like '$password'";

$result = mysqli_query($con,$sql);
	$response = array();	
	if (mysqli_num_rows($result)>0) 
		{
			$row=mysqli_fetch_row($result);
			$name=$row[0];
			$code="true";
			array_push($response,array("code"=>$code,"nic"=>$name));
			print_r(json_encode($response));
		}
	else
		{
			$code = "false";
			$message = "User not Found Try Again...";
			array_push($response,array("code"=>$code,"message"=>$message));
			print_r(json_encode($response));
		}
		mysqli_close($con);



?>