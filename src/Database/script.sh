#!/bin/bash

while read p; do
	IFS=',' read -r -a array <<< "$p"
	text='this.insertCard('"${array[0]}"','"${array[1]}"', "'"${array[2]}"'", "'"${array[3]}"'", "'"${array[4]}"'", "'"${array[5]}"'");'
	echo $text;
done < cardsWithNone.txt
