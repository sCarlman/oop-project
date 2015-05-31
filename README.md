# oop-project

To run the application on a device you have to do following:

1: connect your device through the USB
2: Open the terminal and go to your SDK location and go to the /platform-tools/
 for example: C:\Users\Filips\AppData\Local\Android\sdk\platform-tools for windows

3:in platform-tools type adb -d install path/to/your/app.apk
  which in our case is Projectlocation/Dat367_Grupp10/app/app-release.apk

3.1: (on unix if you haven't defined the adb path you have to write 
     ./adb -d install path/to/your/app.apk)

for more information or to run on an emulator see http://developer.android.com/tools/building/building-cmdline.html