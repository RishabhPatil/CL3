import os
import unittest

def BinarySearch(numbers,last,first,key):
	top=last
	bottom=first
	mid=(top+bottom)/2
	while(key!=numbers[mid] and bottom<=top):
		if(key>numbers[mid]):
			bottom=mid+1
		else:
			top=mid-1

		mid=(top+bottom)/2

	if(numbers[mid]!=key):
		return 999
	else:
		return mid

def readfile(textfile):
	arr=[]
	filename=open(textfile,'r')
	for i in filename:
		if i.strip():
			arr.append(int(i))
	
	arr.sort()
	return arr

class Test(unittest.TestCase):
	def test_positive(self):
		self.assertEqual(BinarySearch([0,1,2,3,4,5],5,0,3),3)
	def test_negative(self):
		self.assertEqual(BinarySearch([0,1,2,3,4,5],5,0,10),999)

inputarray=readfile("input.txt")
print("Sorted input array is:")
print inputarray
print("Enter key to be searched")
y=int(raw_input())
index=BinarySearch(inputarray,len(inputarray)-1,0,y)
if(index==999):
	print("Element not found")
else:
	print index

unittest.main()
