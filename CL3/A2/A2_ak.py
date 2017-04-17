import os 
import xml.etree.ElementTree as ET
import unittest
import threading
def takeInput(filename):
	with open(filename) as f:
		tree = ET.parse(filename)
		root= tree.getroot()
		inp=root.text.split()
		inp=[int(x) for x in inp]
		print inp	
		return inp

def Partition(lst,start,last):
	i=start+1
	j=last
	pivot=lst[start]
	done=False
	while not done:
		while(i<=j and lst[i]<=pivot):
			i+=1
		while(lst[j]>pivot and j>=i):
			j-=1
		if(j<i):
			done = True
		else:		
			lst[i],lst[j]=lst[j],lst[i]
	
	lst[start],lst[j]=lst[j],lst[start]
	return j

def QuickSort(lst,start,last):
	if(start<last):
		mid=Partition(lst,start,last)
		t1=threading.Thread(QuickSort(lst,start,mid-1))
		t2=threading.Thread(QuickSort(lst,mid+1,last))	
		t1.start()
		t2.start()
		t1.join()
		t2.join()
		print t1.getName(),t2.getName()
	

class Test(unittest.TestCase):
	def test_postive(self):
		self.assertEquals(takeInput("input.xml"),[12, 43, 143, 65, 1, 56, 235, 11, 53, 64])
	def test_negative(self):
		self.assertRaises(IOError,takeInput,"input.txt")

input_file='input.xml'
inp=takeInput(input_file)
QuickSort(inp,0,len(inp)-1)
print "Sorted Array \n"
print inp
print "--------------------------TESTING---------------------------------\n"
unittest.main()
