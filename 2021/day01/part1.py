f = open("day01/input.txt", "r")
input = f.read()
f.close()

input = input.splitlines()
values = []

for x in input:
    values.append(int(x))

counter = 0

for i in range(len(values)-1):
    if(values[i+1] > values[i]):
        counter += 1

print(counter)