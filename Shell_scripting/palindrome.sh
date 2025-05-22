# This script checks whether a given number is a palindrome.

read -p "Enter a number: " num
temp=$num
rev=0
while (( num > 0 )); do
  rem=$((num % 10))
  rev=$((rev * 10 + rem))
  num=$((num / 10))
done
if [ $temp -eq $rev ]; then
  echo "Palindrome"
else
  echo "Not Palindrome"
fi