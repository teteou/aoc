#get input

f = open("day01/input.txt", "r")
input = f.read()
f.close()

#solution

input = input.splitlines()

values = []

for x in input:
    values.append(int(x))

counter = 0

for i in range(len(values)-3):
    a = values[i] + values[i+1] + values[i+2]
    b = values[i+1] + values[i+2] + values[i+3]
    if(b>a):
        counter += 1

print(counter)