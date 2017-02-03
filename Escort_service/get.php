<?php
   $dbhost = 'omega.uta.edu';
   $dbuser = 'sxd6090';
   $dbpass = 'Goodday55';
   
   $conn = mysql_connect($dbhost, $dbuser, $dbpass);
   
   if(! $conn ) {
      die('Could not connect: ' . mysql_error());
   }
   echo "start";
   $sql = 'SELECT A, B FROM demo';
   mysql_select_db('sxd6090');
   $retval = mysql_query( $sql, $conn );
   
   if(! $retval ) {
      die('Could not get data: ' . mysql_error());
   }
   
   while($row = mysql_fetch_array($retval, MYSQL_ASSOC)) {
      echo "A :{$row['A']}  <br> ".
         "B : {$row['B']} <br> ".
         "--------------------------------<br>";
   }
   
   echo "Fetched data successfully\n";
   
   mysql_close($conn);
?>