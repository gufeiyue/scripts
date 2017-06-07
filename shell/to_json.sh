#!/bin/sh
json=$1

echo $json | python -m json.tool
