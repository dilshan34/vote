<?php

require_once('dbconnect.php');

$sql = "SELECT * FROM `candidates`";

$res=mysqli_query($con,$sql);
$result = array();

while ($row = mysqli_fetch_array($res)) {
	
	array_push($result,array("task"=>$row['name'],"id" =>$row['votescount']  ));
	
}

print(json_encode($result));
mysqli_close($con);

?>