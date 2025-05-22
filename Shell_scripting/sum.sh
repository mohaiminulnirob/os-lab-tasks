# This script reads three numbers from the user and calculates their sum.

echo -n "First Number is: "
read num1

echo -n "Second Number is: "
read num2

echo -n "Third Number is: "
read num3

sum=`expr $num1 + $num2 + $num3`

echo "Sum is $sum"
