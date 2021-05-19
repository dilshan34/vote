<?php

require_once('dbconnect.php');


 $updatedatetime = date('Y-m-d h:i:s');
 $name =$_POST['name'];
 $nic=$_POST['nic'];


$sql = "INSERT INTO `users` (`name`, `nic`, `created_date`)
 VALUES ('$name','$nic','$updatedatetime')";

 
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