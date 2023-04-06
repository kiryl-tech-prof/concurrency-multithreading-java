# Welcome! This repository contains the code which is used in *Java Concurrency & Multithreading: beginner to intermediate* course

After cloning this repository, you can open the folder with your choice of IDE.
Please do not forget to mark *main/src* folder as Java source folder.

## Command-line instructions

You can also run examples from command-line.
Instructions listed below cover Mac users, but they should be analogous for users of any other OS (though commands may differ).
For example, to run *MainSingleThread.java*, you need to:

 - Have Java installed. To check whether you have it, type in your command line
```
java -version
javac -version
```

and you should get something like
```
$ java -version
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)

$ javac -version
javac 1.8.0_181
```

Versions might be different though. If you have valid Java installed, regardless of version, you are good to go.

 - Go into source directory; from the root of this project, just

```
cd main/src/
```

 - Run the program! Do not forget to change the name of the class you are running

```
javac MainSingleThread.java; java MainSingleThread
```