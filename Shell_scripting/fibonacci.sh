# This script generates a Fibonacci series up to the number of terms entered by the user.

read -p "Enter count: " n
a=0
b=1
echo -n "$a $b "
for ((i=2;i<n;i++))
do
  c=$((a + b))
  echo -n "$c "
  a=$b
  b=$c
done
echo
