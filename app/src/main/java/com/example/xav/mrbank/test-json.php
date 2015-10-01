<?php

/**
 * id        (int unsigned)
 * status    (small int)
 * time_left (int) seconds left
 * money     (int)
 */

$data = array();

for ($i = 1; $i < 20; $i++)
{
	$data[] = array(
		'id' => rand(15400, 1251015),
		'status' => rand(0, 4),
		'timeLeft' => rand(0, 120),
		'money' => rand(1, 500),
	);
}

header('Content-Type: application/json');
//var_dump($data);
echo json_encode($data);
exit;
