# This script checks whether a given number is prime or not.

read -p "Enter a number: " num

if ! [[ "$num" =~ ^[0-9]+$ ]]; then
  echo "Invalid input"
  exit 1
fi

if [ "$num" -le 1 ]; then
  echo "Not Prime"
  exit
fi

for ((i=2; i*i<=num; i++)); do
  if (( num % i == 0 )); then
    echo "Not Prime"
    exit
  fi
done

echo "Prime"