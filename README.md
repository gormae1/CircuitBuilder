# CircuitBuilder

CircuitBuilder is a graphical drag-and-drop builder and simulator of digital logic circuits. 

![<Example screenshot of usage>](https://github.com/gormae1/CircuitBuilder/blob/main/usage_screenshot.png)

Currently, the following logic gate types are supported:
- OR
- AND
- XOR
- XNOR
- NOT
- NOR
- NAND
- Half Adder
- Full Adder

# Installation
The program can be launched by:
- Running the CircuitBuilder JAR as an executable.
- Manually running the JAR:
```
java -jar CircuitBuilder.jar
```
- Manually running the class files within the JAR:

```
java -cp <path to JAR>/CircuitBuilder.jar circuitbuilder.CircuitBuilder 
```
- Extracting the JAR, and then compiling and  running the source files within extracted folders:
```
javac <path to circuitbuilder folder>/circuitbuilder/*.java
java -cp . circuitbuilder.CircuitBuilder 
```

# Usage
(Also viewable from the help section of the software)

### How To Place Gates
First select the desired gate using the buttons on the left of the window. After having clicked on the desired button, click anywhere on the empty "canvas" part of the window, to the right. Doing this will place your gate on the canvas, and you can now drag the gate to any position within the canvas.


### How To Make Connections
To make connections from input to output, first select the wire button from the list of buttons to the right of the window. Next, click (not drag) on the first gate's input or output and then on the second gate's input or output. The connection will be made as soon as you click.


### How to use Switches
To turn a switch on or off, simply click anywhere on the switch (except on its output).


### Removing Gates and Connections
To remove a gate from the canvas, right-click on it. This will remove the gate along with any connections it has. To remove connection, right-click on the input or output associated with the specific connection. If multiple connections are made from an single output,right-clicking on it will cause all connections connected to the output will be removed. 

# Documentation
Full documentation of the source code (in JavaDoc format) is available in the ```\documentary\``` folder within the JAR.
