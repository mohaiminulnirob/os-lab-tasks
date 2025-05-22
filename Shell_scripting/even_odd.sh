# This script reads a number from the user and checks whether it is even or odd.

read -p "Enter a number: " num
if (( num % 2 == 0 )); then
  echo "Even"
else
  echo "Odd"
fi
