# This script checks for a file, creates it if it doesn't exist, ensures it's executable, and shows its metadata.

read -p "Enter file name: " name

if [ -f "$name" ]; then
  echo "File exists"
else 
  touch "$name"
  echo "File created"
fi

if [ -x "$name" ]; then
  echo "Executable"
else
  chmod +x "$name"
  echo "Made executable"
fi

echo "File metadata:"
ls -l "$name"
