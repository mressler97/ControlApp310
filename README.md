# EGEN 310 Control Application 


I worked along with four engineer students to design and build a Remote Control Vehicle (RCV) that is capable of navigating the Cat's Conundrum obstacle course for EGEN310. This Android application connects to a HC-05 Bluetooth module running on an Arduino Uno. 

## Installation

Download the source code folder and compile it in Android Studio while a compatibale smartphone is plugged in. 


## Connecting
  - Turn on Bluetooth under settings on device
  - Find 'HC-05' in nearby devices and click connect
 
In Control Application:
  - Tap the 'connect' button


Markdown is a lightweight markup language based on the formatting conventions that people naturally use in email.  As [John Gruber] writes on the [Markdown site][df1]

> The overriding design goal for Markdown's
> formatting syntax is to make it as readable

### Tech
Tech/devices used for this project:
* HTC Desire Eye
     * 
* HP Pavilion Desktop 690-00xx
* HC-05 Bluetooth Module
* Arduino Uno



### Troubleshooting
```sh
cd dillinger
docker build -t joemccann/dillinger:${package.json.version} .
```
This will create the dillinger image and pull in the necessary dependencies. Be sure to swap out `${package.json.version}` with the actual version of Dillinger.

Once done, run the Docker image and map the port to whatever you wish on your host. In this example, we simply map port 8000 of the host to port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart="always" <youruser>/dillinger:${package.json.version}
```


### Works Cited

 - [Google](www.google.com)





