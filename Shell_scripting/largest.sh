# This script reads 10 numbers from the user and finds the largest among them.

echo "Enter 10 numbers: "
for((i=0;i<10;i++))
do
  read nums[i]
done

largest=${nums[0]}

for num in "${nums[@]}"
do
  if [ $num -gt $largest ]
  then
    largest=$num
  fi
done

echo "Largest number is $largest"
