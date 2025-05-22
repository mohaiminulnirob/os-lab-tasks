# This script prints all prime numbers from 2 up to a given number N.

read -p "Enter N: " n
for ((j=2; j<=n; j++)); do
  is_prime=1
  for ((i=2; i<=j/2; i++)); do
    if (( j % i == 0 )); then
      is_prime=0
      break
    fi
  done
  if (( is_prime == 1 )); then
    echo -n "$j "
  fi
done
echo