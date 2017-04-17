from flask import Flask , request, render_template

app = Flask(__name__)

@app.route('/')
def fun():
    return render_template('index.html',msg="")
    
@app.route('/check/',methods=['POST','GET'])
def check():
    a = checker(request.form['string'])
    return render_template('index.html',msg=a)

def checker(str1):
    file_data=""
    with open('data.txt','rt') as f:
        for line in f:
            file_data = file_data + line
            
    a = file_data.split('.')
    print a
    b = str1.split('.')
    print b
    count = 0
    for i in a:
        for j in b:
            if i==j:
                count=count+1
                
    print "count = ",count
    percentage = str(float(count)/len(b)*100.0) + "%"                
    return percentage

if __name__=="__main__":
    app.run()

'''OUTPUT


[akmyths@localhost B2]$ python server.py 
 * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
127.0.0.1 - - [06/Apr/2017 00:13:53] "GET / HTTP/1.1" 200 -
['A video game is an electronic game that involves interaction with a user interface to generate visual feedback on a video device such as a TV screen or computer monitor', ' The word video in video game traditionally referred to a raster display device,it implies any type of display device that can produce two- or three-dimensional images', ' Some theorists categorize video games as an art form, but this designation is controversial', 'The electronic systems used to play video games are known as platforms; examples of these are personal computers and video game consoles', ' These platforms range from large mainframe computers to small handheld computing devices', ' Specialized video games such as arcade games, in which the video game components are housed in a large, typically coin-operated chassis, while common in the 1980s in video arcades, have gradually declined due to the widespread availability of affordable home video game consoles (e', 'g', ', PlayStation 4, Xbox One and Nintendo Wii U) and video games on desktop and laptop computers and smartphones', '\n']
[u'A video game is an electronic game that involves interaction with a user interface to generate visual feedback on a video device such as a TV screen or computer monitor', u' ']
count =  1
127.0.0.1 - - [06/Apr/2017 00:14:03] "POST /check/ HTTP/1.1" 200 -
'''













