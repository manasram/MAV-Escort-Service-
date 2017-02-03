<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "google_maps";
$Latitude = $_REQUEST ['Latitude'];
    $Longitude = $_REQUEST ['Longitude'];
	$Type = 1 ;
$link = mysqli_connect($servername, $username, $password, $dbname);
if($link){
	echo "succcesful coonection";
	
	
	

	#$Longitude = $_POST['Longitude'];
     #$Latitude = $_POST['Latitude'];
	# $Longitude = 5;
	 #$Latitude =6;
   #$query = "insert into maps values ('$Latitude', '$Longitude',$Type);";
		
		$query = "UPDATE maps SET Latitude = '$Latitude', Longitude = '$Longitude' WHERE Type = 1 ;";
		 $result = mysqli_query($link,$query);
		 
	
}
else{
	echo "unsucccesful coonection";
}
 ?>