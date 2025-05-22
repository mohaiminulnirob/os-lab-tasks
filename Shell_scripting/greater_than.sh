# This script checks if the first command-line argument is greater than or equal to 10 and prints "YES" or "NO".

if(($1 >= 10))
then 
  echo "YES"
else 
  echo "NO"
fi
