# This script calculates the GCD and LCM of two numbers using the Euclidean algorithm.

read -p "Enter first number: " a
read -p "Enter second number: " b
x=$a
y=$b

while [ $b -ne 0 ]; do
  temp=$b
  b=$((a % b))
  a=$temp
done

gcd=$a
lcm=$((x * y / gcd))

echo "GCD is $gcd"
echo "LCM is $lcm"