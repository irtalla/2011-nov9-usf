<?php

echo 'asdasdasd';
echo '<h1>asdadsasd</h1>';

$filename=$_FILES['file']['name'];

$location="uploads/" .$filename;

if(move_uploaded_file($_FILES['file']['tmp_name'], $location)){
    echo 'succes';
}else{
    echo 'nopoo';
}
?>
