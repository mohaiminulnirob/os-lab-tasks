# This script checks if the file provided as the first argument exists and is a regular file.

if [ -f $1 ]
then 
  echo "Exist"
else 
  echo "NO"
fi
