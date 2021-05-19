<?php

require_once('dbconnect.php');



 $nic=$_POST['nic'];
 $can_id=$_POST['can_id'];


$sql = "INSERT INTO `votes` (`nic`, `can_id`)
 VALUES ('$nic','$can_id')";

 
$result = mysqli_query($con, $sql);

if($result)
{
	 $response = array("code" => '200', "message" => 'Update Complete');
    print_r(json_encode($response));
}

else
{
	 $response = array("code" => '400', "message" => 'Update Not Complete');
    print_r(json_encode($response));
}


?>