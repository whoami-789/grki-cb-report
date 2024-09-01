<?php
$url = "http://grki-service/grci/resources/cb/";
$response = file_get_contents($url);

if ($response === FALSE) {
    echo "Ошибка при получении данных.";
} else {
    echo $response;
}
?>
