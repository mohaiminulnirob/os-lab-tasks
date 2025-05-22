# This script reads two values from the user and swaps them.

echo -n "Enter the value of A: "
read a
echo -n "Enter the value of B: "
read b

# Swapping the values
t=$a
a=$b
b=$t

echo "Values after swapping:"
echo "A value is $a"
echo "B value is $b"
