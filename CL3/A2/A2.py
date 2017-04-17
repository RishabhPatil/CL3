import os
import unittest
import threading
import xml.etree.ElementTree as ET

def readinput(filename):
	tree=ET.parse(filename)
	root=tree.getroot()
	root=root.text.split(',')
	l=len(root)-1
	inputarray=[]
	for i in range(l):
		inputarray.append(root[i])
	return inputarray

def partition(inputarray,start,last):
	i=start+1
	j=last
	pivot=inputarray[start]
	done=False

	while not done:
		while(i<=j and inputarray[i]<=pivot):
			i+=1
		while(inputarray[j]>pivot and j>=i):
			j-=1
		if(i>j):
			done=True
		else:
			inputarray[i],inputarray[j]=inputarray[j],inputarray[i]

	inputarray[start],inputarray[j]=inputarray[j],inputarray[start]

	return j

def Quicksort(inputarray,start,last):
	if(start<last):
		mid=partition(inputarray,start,last)
		t1=threading.Thread(Quicksort(inputarray,start,mid-1))
		t2=threading.Thread(Quicksort(inputarray,mid+1,last))
		t1.start()
		t2.start()
		t1.join()
		t2.join()
		print t1.getName(),t2.getName()

inputarray=readinput("A2input.xml")
Quicksort(inputarray,0,len(inputarray)-1)
print inputarray


	
	

